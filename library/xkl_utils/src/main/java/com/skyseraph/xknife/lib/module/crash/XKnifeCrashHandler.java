package com.skyseraph.xknife.lib.module.crash;

import android.content.Context;

import com.skyseraph.xknife.lib.utils.android.DeviceUtils;

/**
 * Created by SkySeraph on 2016/2/2.
 */
public class XKnifeCrashHandler implements Thread.UncaughtExceptionHandler {

    /**
     * 系统默认UncaughtException类
     */
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    private static XKnifeCrashHandler INSTANCE = new XKnifeCrashHandler();
    private Context mContext;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static XKnifeCrashHandler getInstance() {
        return INSTANCE;
    }

    private XKnifeCrashHandler() {
    }

    /**
     * Init.
     *
     * @param ctx the ctx
     */
    public void init(Context ctx) {
        mContext = ctx;
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (!handleException(throwable) && defaultUncaughtExceptionHandler != null) {
            defaultUncaughtExceptionHandler.uncaughtException(thread, throwable);
        }
    }

    private boolean handleException(final Throwable throwable) {
        if (throwable == null) {
            return false;
        }
        if (mContext == null) {
            return false;
        }

        throwable.printStackTrace();

        StringBuffer sb = new StringBuffer();
        sb.append("基本信息: \n");
        sb.append("APP Version: " + DeviceUtils.getAppVersionCode(mContext) + "\n");
        sb.append("API Level: " + DeviceUtils.getSDKVersion() + "\n");
        sb.append("Android: " + DeviceUtils.getSystemVersion() + " (" + DeviceUtils.getModel() + ")\n\n");

        sb.append("异常信息: \n");
        sb.append("Exception: " + throwable.getMessage() + "\n\n");

        sb.append("堆栈信息: \n");
        StackTraceElement[] elements = throwable.getStackTrace();
        for (int i = 0; i < elements.length; i++) {
            sb.append(elements[i].toString() + "\n");
        }

        // TODO: 2015/3/13
//        Intent intent = new Intent(mContext, CrashActivity.class);
//        intent.putExtra("exception", sb.toString());
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        mContext.startActivity(intent);

        android.os.Process.killProcess(android.os.Process.myPid());
        return true;
    }


}
