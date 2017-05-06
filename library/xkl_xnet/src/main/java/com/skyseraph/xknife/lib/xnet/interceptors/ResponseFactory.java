package com.skyseraph.xknife.lib.xnet.interceptors;

import android.util.Log;

import com.google.gson.Gson;
import com.skyseraph.xknife.lib.xnet.beam.HttpResult;
import com.skyseraph.xknife.lib.xnet.exception.HttpException;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by SkySeraph on 2016/5/4.
 */
public class ResponseFactory extends Converter.Factory {

    /**
     * Create response factory.
     *
     * @return the response factory
     */
    public static ResponseFactory create() {
        return create(new Gson());
    }


    /**
     * Create response factory.
     *
     * @param gson the gson
     * @return the response factory
     */
    public static ResponseFactory create(Gson gson) {
        return new ResponseFactory(gson);
    }

    private final Gson gson;

    private ResponseFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        return new GsonBodyConverter<>(gson, type);
    }

    /**
     * The type Gson body converter.
     *
     * @param <T> the type parameter
     */
    class GsonBodyConverter<T> implements Converter<ResponseBody, T> {
        private final Gson gson;
        private final Type type;

        /**
         * Instantiates a new Gson body converter.
         *
         * @param gson the gson
         * @param type the type
         */
        GsonBodyConverter(Gson gson, Type type) {
            this.gson = gson;
            this.type = type;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            String response = value.string();
            Log.d("ResponseFactory", "response = " + response);
            //httpResult 只解析result字段
            HttpResult httpResult = gson.fromJson(response, HttpResult.class);
            //
            if (httpResult.getResultCode() == 0) {
                throw new HttpException(1005);
            }
            return gson.fromJson(response, type);
        }
    }

}
