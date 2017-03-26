package com.skyseraph.xknife.bridge.core;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import com.skyseraph.xknife.bridge.annotation.ClassName;
import com.skyseraph.xknife.bridge.annotation.ParaKey;
import com.skyseraph.xknife.bridge.annotation.RequestCode;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;

/**
 * Created by SkySeraph on 2016/3/15.
 */
public class IntentWrapper {

    private int flags;
    private Context context;
    private Bundle bundle;
    private String className;
    private int requestCode = -1;
    private Intent intent;

    /**
     * Instantiates a new Intent wrapper.
     *
     * @param context     the context
     * @param className   the class name
     * @param bundle      the bundle
     * @param flags       the flags
     * @param requestCode the request code
     */
    IntentWrapper(Context context, String className, Bundle bundle, int flags, int requestCode) {
        this.flags = flags;
        this.context = context;
        this.bundle = bundle;
        this.className = className;
        this.requestCode = requestCode;
        initIntent();
    }

    private void initIntent() {
        intent = new Intent();
        intent.setClassName(context, className);
        intent.putExtras(bundle);
        intent.addFlags(flags);
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public Context getContext() {
        return context;
    }

    /**
     * Gets bundle.
     *
     * @return the bundle
     */
    public Bundle getBundle() {
        return bundle;
    }

    /**
     * Gets class name.
     *
     * @return the class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets request code.
     *
     * @return the request code
     */
    public int getRequestCode() {
        return requestCode;
    }

    /**
     * Gets intent.
     *
     * @return the intent
     */
    public Intent getIntent() {
        return intent;
    }

    /**
     * Sets flags.
     *
     * @param flags the flags
     */
    public void setFlags(int flags) {
        this.flags = flags;
    }

    /**
     * Sets context.
     *
     * @param context the context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Sets bundle.
     *
     * @param bundle the bundle
     */
    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    /**
     * Sets request code.
     *
     * @param requestCode the request code
     */
    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    /**
     * Sets intent.
     *
     * @param intent the intent
     */
    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    /**
     * Sets class name.
     *
     * @param className the class name
     */
    public void setClassName(@NonNull String className) {
        intent.setClassName(context, className);
    }

    /**
     * Add flags.
     *
     * @param flags the flags
     */
    public void addFlags(int flags) {
        intent.addFlags(flags);
    }

    /**
     * Start.
     */
    public void start() {
        if (requestCode == -1) {
            startActivity();
        } else {
            startActivityForResult(requestCode);
        }
    }

    /**
     * Start activity.
     */
    public void startActivity() {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    /**
     * Start activity for result.
     *
     * @param requestCode the request code
     */
    public void startActivityForResult(int requestCode) {
        if (!(context instanceof Activity)) {
            throw new RuntimeException("startActivityForResult only works for activity context");
        }
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    /**
     * The type Builder.
     */
    public static final class Builder {

        private int flags;
        private Context context;

        /**
         * The M method.
         */
        Method mMethod;
        /**
         * The M args.
         */
        Object[] mArgs;
        /**
         * The Class name.
         */
        String className;
        /**
         * The Request code.
         */
        int requestCode;

        /**
         * Instantiates a new Builder.
         *
         * @param context the context
         * @param method  the method
         * @param args    the args
         */
        public Builder(Context context, Method method, Object... args) {
            this.context = context;
            this.mMethod = method;
            this.mArgs = args;
        }

        /**
         * Add flags builder.
         *
         * @param flags the flags
         * @return the builder
         */
        public Builder addFlags(int flags) {
            this.flags |= flags;
            return this;
        }

        /**
         * Build intent wrapper.
         *
         * @return the intent wrapper
         */
        public IntentWrapper build() {

            // 解析方法注解
            Annotation[] methodAnnotations = mMethod.getAnnotations();
            for (Annotation annotation : methodAnnotations) {
                parseMethodAnnotation(annotation);
            }
            if (TextUtils.isEmpty(className)) {
                throw new RuntimeException("ClassName annotation is required.");
            }
            // 参数类型
            Type[] types = mMethod.getGenericParameterTypes();
            // 参数名称
            Annotation[][] parameterAnnotationsArray = mMethod.getParameterAnnotations();

            Bundle bundleExtra = new Bundle();
            for (int i = 0; i < types.length; i++) {
                // key
                String key = null;
                Annotation[] parameterAnnotations = parameterAnnotationsArray[i];
                for (Annotation annotation : parameterAnnotations) {
                    if (annotation instanceof ParaKey) {
                        key = ((ParaKey) annotation).value();
                        break;
                    }
                }
                parseParameter(bundleExtra, types[i], key, mArgs[i]);
            }
            return new IntentWrapper(context, className, bundleExtra, flags,
                    mMethod.isAnnotationPresent(RequestCode.class) ? requestCode : -1);
        }

        /**
         * 解析参数存储到Bundle中
         *
         * @param bundleExtra 存储的Bundle
         * @param type        参数类型
         * @param key         参数名称
         * @param arg         参数值
         */
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        void parseParameter(Bundle bundleExtra, Type type, String key, Object arg) {
            Class<?> rawParameterType = getRawType(type);

            if (rawParameterType == String.class) {
                bundleExtra.putString(key, arg.toString());
            } else if (rawParameterType == String[].class) {
                bundleExtra.putStringArray(key, (String[]) arg);
            } else if (rawParameterType == int.class || rawParameterType == Integer.class) {
                bundleExtra.putInt(key, Integer.parseInt(arg.toString()));
            } else if (rawParameterType == int[].class || rawParameterType == Integer[].class) {
                bundleExtra.putIntArray(key, (int[]) arg);
            } else if (rawParameterType == short.class || rawParameterType == Short.class) {
                bundleExtra.putShort(key, Short.parseShort(arg.toString()));
            } else if (rawParameterType == short[].class || rawParameterType == Short[].class) {
                bundleExtra.putShortArray(key, (short[]) arg);
            } else if (rawParameterType == long.class || rawParameterType == Long.class) {
                bundleExtra.putLong(key, Long.parseLong(arg.toString()));
            } else if (rawParameterType == long[].class || rawParameterType == Long[].class) {
                bundleExtra.putLongArray(key, (long[]) arg);
            } else if (rawParameterType == char.class) {
                bundleExtra.putChar(key, arg.toString().toCharArray()[0]);
            } else if (rawParameterType == char[].class) {
                bundleExtra.putCharArray(key, arg.toString().toCharArray());
            } else if (rawParameterType == double.class || rawParameterType == Double.class) {
                bundleExtra.putDouble(key, Double.parseDouble(arg.toString()));
            } else if (rawParameterType == double[].class || rawParameterType == Double[].class) {
                bundleExtra.putDoubleArray(key, (double[]) arg);
            } else if (rawParameterType == float.class || rawParameterType == Float.class) {
                bundleExtra.putFloat(key, Float.parseFloat(arg.toString()));
            } else if (rawParameterType == float[].class || rawParameterType == Float[].class) {
                bundleExtra.putFloatArray(key, (float[]) arg);
            } else if (rawParameterType == byte.class || rawParameterType == Byte.class) {
                bundleExtra.putByte(key, Byte.parseByte(arg.toString()));
            } else if (rawParameterType == byte[].class || rawParameterType == Byte[].class) {
                bundleExtra.putByteArray(key, (byte[]) arg);
            } else if (rawParameterType == boolean.class || rawParameterType == Boolean.class) {
                bundleExtra.putBoolean(key, Boolean.parseBoolean(arg.toString()));
            } else if (rawParameterType == boolean[].class || rawParameterType == Boolean[].class) {
                bundleExtra.putBooleanArray(key, (boolean[]) arg);
            } else if (rawParameterType == Bundle.class) {
                if (TextUtils.isEmpty(key)) {
                    bundleExtra.putAll((Bundle) arg);
                } else {
                    bundleExtra.putBundle(key, (Bundle) arg);
                }
            } else if (rawParameterType == PersistableBundle.class) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) // 5.0 & 5.0+
                {
                    bundleExtra.putAll((PersistableBundle) arg);
                } else {
                    throw new RuntimeException("Call requires API level 21 (current min is 14): android.os.BaseBundle#putAll");
                }
            } else if (rawParameterType == Size.class) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) // 5.0 & 5.0+
                {
                    bundleExtra.putSize(key, (Size) arg);
                } else {
                    throw new RuntimeException("Call requires API level 21 (current min is 14): android.os.Bundle#putSize");
                }
            } else if (rawParameterType == SizeF.class) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) // 5.0 & 5.0+
                {
                    bundleExtra.putSizeF(key, (SizeF) arg);
                } else {
                    throw new RuntimeException("Call requires API level 21 (current min is 14): android.os.Bundle#putSizeF");
                }
            } else if (rawParameterType == SparseArray.class) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    Type actualTypeArgument = actualTypeArguments[0];

                    if (actualTypeArgument instanceof Class) {
                        Class<?>[] interfaces = ((Class) actualTypeArgument).getInterfaces();
                        for (Class<?> interfaceClass : interfaces) {
                            if (interfaceClass == Parcelable.class) {
                                bundleExtra.putSparseParcelableArray(key, (SparseArray<Parcelable>) arg);
                                return;
                            }
                        }
                        throw new RuntimeException("SparseArray的泛型必须实现Parcelable接口");
                    }
                } else {
                    throw new RuntimeException("SparseArray的泛型必须实现Parcelable接口");
                }
            } else if (rawParameterType == ArrayList.class) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments(); // 泛型类型数组
                    if (actualTypeArguments == null || actualTypeArguments.length != 1) {
                        throw new RuntimeException("ArrayList的泛型必须实现Parcelable接口");
                    }

                    Type actualTypeArgument = actualTypeArguments[0]; // 获取第一个泛型类型
                    if (actualTypeArgument == String.class) {
                        bundleExtra.putStringArrayList(key, (ArrayList<String>) arg);
                    } else if (actualTypeArgument == Integer.class) {
                        bundleExtra.putIntegerArrayList(key, (ArrayList<Integer>) arg);
                    } else if (actualTypeArgument == CharSequence.class) {
                        bundleExtra.putCharSequenceArrayList(key, (ArrayList<CharSequence>) arg);
                    } else if (actualTypeArgument instanceof Class) {
                        Class<?>[] interfaces = ((Class) actualTypeArgument).getInterfaces();
                        for (Class<?> interfaceClass : interfaces) {
                            if (interfaceClass == Parcelable.class) {
                                bundleExtra.putParcelableArrayList(key, (ArrayList<Parcelable>) arg);
                                return;
                            }
                        }
                        throw new RuntimeException("ArrayList的泛型必须实现Parcelable接口");
                    }
                } else {
                    throw new RuntimeException("ArrayList的泛型必须实现Parcelable接口");
                }
            } else {
                if (rawParameterType.isArray()) // Parcelable[]
                {
                    Class<?>[] interfaces = rawParameterType.getComponentType().getInterfaces();
                    for (Class<?> interfaceClass : interfaces) {
                        if (interfaceClass == Parcelable.class) {
                            bundleExtra.putParcelableArray(key, (Parcelable[]) arg);
                            return;
                        }
                    }
                    throw new RuntimeException("Object[]数组中的对象必须全部实现了Parcelable接口");
                } else // 其他接口
                {
                    Class<?>[] interfaces = rawParameterType.getInterfaces();
                    for (Class<?> interfaceClass : interfaces) {
                        if (interfaceClass == Serializable.class) {
                            bundleExtra.putSerializable(key, (Serializable) arg);
                        } else if (interfaceClass == Parcelable.class) {
                            bundleExtra.putParcelable(key, (Parcelable) arg);
                        } else if (interfaceClass == IBinder.class) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) // 4.3+
                            {
                                bundleExtra.putBinder(key, (IBinder) arg);
                            } else {
                                throw new RuntimeException("Call requires API level 18 (current min is 14): android.os.Bundle#putBinder");
                            }
                        } else {
                            throw new RuntimeException("Bundle不支持的类型, 参数: " + key);
                        }
                    }
                }
            }
        }

        /**
         * parse method annotation
         *
         * @param annotation the annotation
         * @return
         */
        void parseMethodAnnotation(Annotation annotation) {
            if (annotation instanceof ClassName) {
                className = ((ClassName) annotation).value();
            } else if (annotation instanceof RequestCode) {
                requestCode = ((RequestCode) annotation).value();
            }
        }

        /**
         * return the raw type of type
         *
         * @param type the type
         * @return raw type
         */
        Class<?> getRawType(Type type) {
            if (type == null) throw new NullPointerException("type == null");

            if (type instanceof Class<?>) {
                // Type is a normal class.
                return (Class<?>) type;
            }
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;

                // I'm not exactly sure why getRawType() returns Type instead of Class. Neal isn't either but
                // suspects some pathological case related to nested classes exists.
                Type rawType = parameterizedType.getRawType();
                if (!(rawType instanceof Class)) throw new IllegalArgumentException();
                return (Class<?>) rawType;
            }
            if (type instanceof GenericArrayType) {
                Type componentType = ((GenericArrayType) type).getGenericComponentType();
                return Array.newInstance(getRawType(componentType), 0).getClass();
            }
            if (type instanceof TypeVariable) {
                // We could use the variable's bounds, but that won't work if there are multiple. Having a raw
                // type that's more general than necessary is okay.
                return Object.class;
            }
            if (type instanceof WildcardType) {
                return getRawType(((WildcardType) type).getUpperBounds()[0]);
            }

            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or "
                    + "GenericArrayType, but <" + type + "> is of type " + type.getClass().getName());
        }
    }
}
