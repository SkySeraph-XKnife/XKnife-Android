package com.skyseraph.xknife.lib.xnet.utils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by SkySeraph on 2016/5/4.
 */
public class RxHelper {

    /**
     * Thread io main observable . transformer.
     *
     * @param <T> the type parameter
     * @return the observable . transformer
     */
    public static <T> Observable.Transformer<T, T> threadIOMain() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * To subscribe.
     *
     * @param <T> the type parameter
     * @param o   the o
     * @param s   the s
     */
    public static <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * Create data observable.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @return the observable
     */
    public static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }
}
