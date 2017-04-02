package com.skyseraph.xknife.lib.utils.android.base;

import android.app.Application;

import com.skyseraph.xknife.lib.utils.android.crash.XKnifeCrashHandler;
import com.skyseraph.xknife.lib.utils.task.task.TaskExecutor;

/**
 * Created by SkySeraph on 2015/4/2.
 */
public class BaseApplication extends Application {

    private static BaseApplication sInstance = null;

    public static BaseApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        /* 初始化线程池 */
        TaskExecutor.init(3, 5, 2);

        /* 初始化奔溃和Log输出 */
        initCrashLog();

        initComponentsLib();
    }

    public void initComponentsLib() {
    }

    public void initCrashLog() {
        XKnifeCrashHandler.getInstance().init(this);
    }
}
