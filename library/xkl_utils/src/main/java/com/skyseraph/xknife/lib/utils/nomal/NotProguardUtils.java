package com.skyseraph.xknife.lib.utils.nomal;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;

import com.skyseraph.xknife.lib.utils.android.base.BaseApplication;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by SkySeraph on 2015/4/26.
 */
public class NotProguardUtils implements NotProguardInterface {

    private static Integer invokeMethod(AppOpsManager manager, String methodName, int op,
                                        int callingUid, String packageName) {
        Class<AppOpsManager> c = AppOpsManager.class;
        try {
            Method method = c.getMethod(methodName, int.class, int.class, String.class);
            if (method != null) {
                method.setAccessible(true);
                Object object = method.invoke(manager, op, callingUid, packageName);
                return (Integer) object;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Check op boolean.
     *
     * @param context the context
     * @param op      the op
     * @return the boolean
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean checkOp(Context context, int op) {
        final int version = Build.VERSION.SDK_INT;

        if (version >= 19) {
            try {
                AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
                if (AppOpsManager.MODE_ALLOWED == invokeMethod(manager, "checkOp", op,
                        Binder.getCallingUid(), context.getPackageName())) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
            } catch (NoClassDefFoundError e) {
            }
        } else {
        }
        return false;
    }

    /**
     * 获取系统属性
     *
     * @param key 键
     * @return 值 system property
     */
    public static String getSystemProperty(String key) {
        try {
            Class<?> clsSystemProperties = Class.forName("android.os.SystemProperties");
            Method methodGet = clsSystemProperties.getDeclaredMethod("get", String.class);
            Object result = methodGet.invoke(clsSystemProperties, key);
            return result == null ? null : result.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 应用程序是否打开了显示浮窗的开关（部分rom试用，如小米）
     *
     * @param context 当前应用程序的上下文
     * @return boolean boolean
     */
    public static boolean floatingWindowHasOpened(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return true;
        }
        Class<? extends ApplicationInfo> clazz = applicationInfo.getClass();
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            if (f.getName().equals("FLAG_SHOW_FLOATING_WINDOW")) {
                try {
                    int i = f.getInt(context.getApplicationInfo());
                    int flags = context.getApplicationInfo().flags;
                    if ((flags & i) == i) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (IllegalArgumentException e) {
                } catch (IllegalAccessException e) {
                } catch (Exception e) {
                }
            }
        }
        return true;
    }

    /**
     * 调用系统InstalledAppDetails界面显示已安装应用程序的详细信息。
     * 对于Android 2.3（Api Level 9）以上，使用SDK提供的接口; 2.3以下，使用非公开的接口（查看InstalledAppDetails源码）。
     *
     * @param context     上下文
     * @param packageName 应用程序的包名
     */
    public static void showInstalledAppDetails(Context context, String packageName) {
        final String SCHEME = "package";
        /**
         * 调用系统InstalledAppDetails界面所需的Extra名称(用于Android 2.1及之前版本)
         */
        final String APP_PKG_NAME_21 = "com.android.settings.ApplicationPkgName";
        /**
         * 调用系统InstalledAppDetails界面所需的Extra名称(用于Android 2.2)
         */
        final String APP_PKG_NAME_22 = "pkg";
        /**
         * InstalledAppDetails所在包名
         */
        final String APP_DETAILS_PACKAGE_NAME = "com.android.settings";
        /**
         * InstalledAppDetails类名
         */
        final String APP_DETAILS_CLASS_NAME = "com.android.settings.InstalledAppDetails";

        Intent intent = new Intent();
        final int apiLevel = Build.VERSION.SDK_INT;
        if (apiLevel >= 9) { // 2.3（ApiLevel 9）以上，使用SDK提供的接口
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts(SCHEME, packageName, null);
            intent.setData(uri);
        } else { // 2.3以下，使用非公开的接口（查看InstalledAppDetails源码）
            // 2.2和2.1中，InstalledAppDetails使用的APP_PKG_NAME不同
            final String appPkgName = (apiLevel == 8 ? APP_PKG_NAME_22
                    : APP_PKG_NAME_21);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName(APP_DETAILS_PACKAGE_NAME,
                    APP_DETAILS_CLASS_NAME);
            intent.putExtra(appPkgName, packageName);
        }
        try {
            if (context instanceof BaseApplication) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }
}
