package com.skyseraph.xknife.bridge.core;

import android.content.Context;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by SkySeraph on 2016/3/15.
 */
public class XBridge {

    private Interceptor interceptor;

    private XBridge(Builder builder) {
        this.interceptor = builder.interceptor;
    }

    /**
     * The type Builder.
     */
    public static final class Builder {
        private Interceptor interceptor;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            this.interceptor = null;
        }

        /**
         * Interceptor builder.
         *
         * @param interceptor the interceptor
         * @return the builder
         */
        public Builder interceptor(Interceptor interceptor) {
            this.interceptor = interceptor;
            return this;
        }

        /**
         * Build x bridge.
         *
         * @return the x bridge
         */
        public XBridge build() {
            return new XBridge(this);
        }
    }

    /**
     * create router class service
     *
     * @param <T>     the type parameter
     * @param service router class
     * @param context from context
     * @return t
     */
    public <T> T create(final Class<T> service, final Context context) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object... args)
                            throws Throwable {
                        IntentWrapper intentWrapper = loadIntentWrapper(context, method, args);

                        Class returnTYpe = method.getReturnType();
                        if (returnTYpe == void.class) {
                            if (interceptor == null || !interceptor.intercept(intentWrapper)) {
                                intentWrapper.start();
                            }
                            return null;
                        } else if (returnTYpe == IntentWrapper.class) {
                            return intentWrapper;
                        }
                        throw new RuntimeException("method return type only support 'void' or 'IntentWrapper'");
                    }
                });
    }

    /**
     * Load intent wrapper intent wrapper.
     *
     * @param context the context
     * @param method  the method
     * @param args    the args
     * @return the intent wrapper
     */
    IntentWrapper loadIntentWrapper(Context context, Method method, Object... args) {
        return new IntentWrapper.Builder(context, method, args).build();
    }
}
