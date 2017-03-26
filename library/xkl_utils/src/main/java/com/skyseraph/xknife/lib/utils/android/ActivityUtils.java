package com.skyseraph.xknife.lib.utils.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by SkySeraph on 2015/3/11.
 */
public class ActivityUtils {

    private ActivityUtils() {
        throw new AssertionError();
    }

    /**
     * Is activity exists boolean.
     *
     * @param context     the context
     * @param packageName the package name
     * @param className   the class name
     * @return the boolean
     */
    public static boolean isActivityExists(Context context, String packageName, String className) {
        Intent intent = new Intent();
        intent.setClassName(packageName, className);
        return !(context.getPackageManager().resolveActivity(intent, 0) == null ||
                intent.resolveActivity(context.getPackageManager()) == null ||
                context.getPackageManager().queryIntentActivities(intent, 0).size() == 0);
    }

    /**
     * Start activity.
     *
     * @param context     the context
     * @param packageName the package name
     * @param className   the class name
     */
    public static void startActivity(Context context, String packageName, String className) {
        startActivity(context, packageName, className, null);
    }

    /**
     * 打开Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   全类名
     * @param bundle      bundle
     */
    public static void startActivity(Context context, String packageName, String className, Bundle bundle) {
        context.startActivity(IntentUtils.getComponentIntent(packageName, className, bundle));
    }

    /**
     * 启动Activity
     *
     * @param context  原启动上下文
     * @param dstClass 目标Activity
     */
    public static void startActivity(Context context, Class dstClass) {
        startActivity(context, new Intent(context, dstClass));
    }

    /**
     * 启动Activity
     *
     * @param context 原启动上下文
     * @param intent  目标意图
     */
    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }
}
