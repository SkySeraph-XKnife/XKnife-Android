package com.skyseraph.xknife.lib.utils.android.base;

import android.app.Application;
import android.content.Context;

import com.skyseraph.xknife.lib.module.crash.XKnifeCrashHandler;
import com.skyseraph.xknife.lib.module.task.task.TaskExecutor;

/**
 * Created by SkySeraph on 2015/4/2.
 */
public class BaseApplication extends Application {

    private static Context sContext;

    private static BaseApplication sInstance = null;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static BaseApplication getInstance() {
        return sInstance;
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sContext = getApplicationContext();

        /* 初始化线程池 */
        TaskExecutor.init(3, 5, 2);

        /* 初始化奔溃和Log输出 */
        initCrashLog();

        initComponentsLib();
    }

    /**
     * Init components lib.
     */
    public void initComponentsLib() {
    }

    /**
     * Init crash log.
     */
    public void initCrashLog() {
        XKnifeCrashHandler.getInstance().init(this);
    }
}
