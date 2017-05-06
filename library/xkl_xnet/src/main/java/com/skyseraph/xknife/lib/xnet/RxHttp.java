package com.skyseraph.xknife.lib.xnet;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by SkySeraph on 2016/5/4.
 */
public class RxHttp {

    private static RxHttp instance;
    private static WeakReference<Context> context;
    private Retrofit retrofit;
    private RxHttpConfig config;

    private RxHttp(RxHttpConfig config) {
        this.config = config;
        this.retrofit = newRetrofit();
    }

    /**
     * Init.
     *
     * @param baseUrl the base url
     * @param logger  the logger
     */
    public static void init(String baseUrl, boolean logger) {
        init(RxHttpConfig.createDefault(baseUrl, logger));
    }

    /**
     * Init.
     *
     * @param config the config
     */
    public static void init(RxHttpConfig config) {
        if (instance == null) {
            instance = new RxHttp(config);
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static RxHttp getInstance() {
        return instance;
    }

    /**
     * Create t.
     *
     * @param <T>     the type parameter
     * @param service the service
     * @return the t
     */
    public static <T> T create(final Class<T> service) {
        if (instance == null) {
            throw new NullPointerException("RxHttp not init~");
        }
        return instance.retrofit.create(service);
    }


    private Retrofit newRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(config.getBaseUrl());

        // 设置转换器
        List<Converter.Factory> converterFactories = config.getConverterFactories();
        if (converterFactories != null && !converterFactories.isEmpty()) {
            for (Converter.Factory factory : converterFactories) {
                builder.addConverterFactory(factory);
            }
        }

        // 设置适配器
        List<CallAdapter.Factory> adapterFactories = config.getAdapterFactories();
        if (adapterFactories != null && !adapterFactories.isEmpty()) {
            for (CallAdapter.Factory factory : adapterFactories) {
                builder.addCallAdapterFactory(factory);
            }
        }

        //设置okhttp
        OkHttpClient httpClient = newOkHttpClient();
        builder.client(httpClient).build();

        return builder.build();
    }

    private OkHttpClient newOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(config.getConnectTimeoutMilliseconds(), TimeUnit.MILLISECONDS);

        //设置拦截器
        List<Interceptor> interceptors = config.getInterceptors();
        if (interceptors != null && !interceptors.isEmpty()) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        return builder.build();
    }
}
