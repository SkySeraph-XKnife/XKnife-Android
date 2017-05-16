package com.skyseraph.xknife;

import android.content.Context;

import com.skyseraph.xknife.lib.module.log.L;
import com.skyseraph.xknife.lib.utils.android.base.BaseApplication;
import com.skyseraph.xknife.lib.xnet.RxHttp;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by SkySeraph on 2016/4/2.
 */
public class AppApplication extends BaseApplication {

    private RefWatcher refWatcher;

    /**
     * Gets ref watcher.
     *
     * @param context the context
     * @return the ref watcher
     */
    public static RefWatcher getRefWatcher(Context context) {
        AppApplication application = (AppApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initLog();
        initLeakCanary();
        initRxHttp();
    }

    /**
     * Init log.
     */
    private void initLog() {
        L.init(BuildConfig.DEBUG);
    }

    /**
     * Init leak canary.
     * 返回一个预定义的 RefWatcher,用于自动监控调用 Activity.onDestroy() 之后泄露的 activity
     */
    private void initLeakCanary() {
        refWatcher = LeakCanary.install(this);
    }

    /**
     * Init rx http.
     */
    private void initRxHttp() {
        // 默认配置
        RxHttp.init(BuildConfig.API_URL, BuildConfig.DEBUG);

        // 自定义配置
//        RxHttpConfig config = new RxHttpConfig.Builder().connectTimeout(12 * 1000)
//                .baseUrl(BuildConfig.API_URL)
//                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
//                .addConverterFactory(ResponseFactory.create())
//                .addCallAdapterFactory()
//                .build();
//        RxHttp.init(config);
    }
}
