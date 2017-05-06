package com.skyseraph.xknife;

import com.skyseraph.xknife.lib.module.log.L;
import com.skyseraph.xknife.lib.utils.android.base.BaseApplication;
import com.skyseraph.xknife.lib.xnet.RxHttp;

/**
 * Created by SkySeraph on 2016/4/2.
 */
public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initLog();
        initRxHttp();
    }

    private void initLog() {
        L.init(BuildConfig.DEBUG);
    }

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
