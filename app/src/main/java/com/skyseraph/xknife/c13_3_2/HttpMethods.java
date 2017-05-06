package com.skyseraph.xknife.c13_3_2;


import com.skyseraph.xknife.lib.xnet.RxHttp;
import com.skyseraph.xknife.lib.xnet.utils.RxHelper;

import rx.Subscriber;

/**
 * Created by SkySeraph on 2016/5/4.
 */
public class HttpMethods {

    private static final int DEFAULT_TIMEOUT = 6;

    private static HttpMethods INSTANCE;

    private RxHttp rxHttp;

    private HttpMethods() {
        rxHttp = RxHttp.getInstance();
    }

    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Gets top movie.
     *
     * @param subscriber the subscriber
     * @param start      the start
     * @param count      the count
     */
    public void getTopMovie(Subscriber<DataModel> subscriber, int start, int count) {
        if (rxHttp == null) {
            rxHttp = RxHttp.getInstance();
        }
        RxHelper.toSubscribe(rxHttp.create(IDataApi.class).getTopMovie(start, count), subscriber);
    }

    /**
     * Gets hot movie.
     *
     * @param subscriber the subscriber
     */
    public void getHotMovie(Subscriber<DataModel> subscriber) {
        if (rxHttp == null) {
            rxHttp = RxHttp.getInstance();
        }
        RxHelper.toSubscribe(rxHttp.create(IDataApi.class).getHotMovie(), subscriber);
    }
}
