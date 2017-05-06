package com.skyseraph.xknife.lib.xnet;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by SkySeraph on 2016/5/4.
 */
public class RxHttpConfig {
    private String baseUrl;
    private long connectTimeoutMilliseconds;
    private List<Interceptor> interceptors;
    private List<Converter.Factory> converterFactories;
    private List<CallAdapter.Factory> adapterFactories;

    private static final long DEFAULT_CONNECT_TIMEOUT = 15 * 1000;

    /**
     * 默认配置
     *
     * @param baseUrl the base url
     * @param logger  the logger
     * @return the rx http config
     */
    public static RxHttpConfig createDefault(String baseUrl, boolean logger) {
        Builder builder = new Builder();
        if (logger) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC));
        }
        return builder.baseUrl(baseUrl)
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    /**
     * Instantiates a new Rx http config.
     *
     * @param builder the builder
     */
    RxHttpConfig(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.connectTimeoutMilliseconds = builder.connectTimeoutMilliseconds;
        this.interceptors = new ArrayList<>(builder.interceptors);
        this.converterFactories = new ArrayList<>(builder.converterFactories);
        this.adapterFactories = new ArrayList<>(builder.adapterFactories);
    }

    /**
     * Gets base url.
     *
     * @return the base url
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * Gets connect timeout milliseconds.
     *
     * @return the connect timeout milliseconds
     */
    public long getConnectTimeoutMilliseconds() {
        return connectTimeoutMilliseconds;
    }

    /**
     * Gets interceptors.
     *
     * @return the interceptors
     */
    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    /**
     * Gets converter factories.
     *
     * @return the converter factories
     */
    public List<Converter.Factory> getConverterFactories() {
        return converterFactories;
    }

    /**
     * Gets adapter factories.
     *
     * @return the adapter factories
     */
    public List<CallAdapter.Factory> getAdapterFactories() {
        return adapterFactories;
    }

    /**
     * The type Builder.
     */
    public static final class Builder {
        /**
         * The Base url.
         */
        String baseUrl;
        /**
         * The Connect timeout milliseconds.
         */
        long connectTimeoutMilliseconds;
        /**
         * The Interceptors.
         */
        List<Interceptor> interceptors = new ArrayList<>();
        /**
         * The Converter factories.
         */
        List<Converter.Factory> converterFactories = new ArrayList<>();
        /**
         * The Adapter factories.
         */
        List<CallAdapter.Factory> adapterFactories = new ArrayList<>();

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
        }

        /**
         * Base url builder.
         *
         * @param baseUrl the base url
         * @return the builder
         */
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * Connect timeout builder.
         *
         * @param timeoutMilliseconds the timeout milliseconds
         * @return the builder
         */
        public Builder connectTimeout(long timeoutMilliseconds) {
            this.connectTimeoutMilliseconds = timeoutMilliseconds;
            return this;
        }

        /**
         * Add interceptor builder.
         *
         * @param interceptor the interceptor
         * @return the builder
         */
        public Builder addInterceptor(Interceptor interceptor) {
            this.interceptors.add(interceptor);
            return this;
        }

        /**
         * Add converter factory builder.
         *
         * @param factory the factory
         * @return the builder
         */
        public Builder addConverterFactory(Converter.Factory factory) {
            this.converterFactories.add(factory);
            return this;
        }

        /**
         * Add call adapter factory builder.
         *
         * @param factory the factory
         * @return the builder
         */
        public Builder addCallAdapterFactory(CallAdapter.Factory factory) {
            this.adapterFactories.add(factory);
            return this;
        }

        /**
         * Build rx http config.
         *
         * @return the rx http config
         */
        public RxHttpConfig build() {
            return new RxHttpConfig(this);
        }
    }
}
