package com.skyseraph.xknife.lib.xnet.subscriber;

import com.skyseraph.xknife.lib.xnet.exception.HttpException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Subscriber;

/**
 * Created by SkySeraph on 2016/5/4.
 * 异常处理
 *
 * @param <T> the type parameter
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        String msg;
        if (e instanceof HttpException) {
            msg = e.getMessage();
        } else if (e instanceof UnknownHostException) {
            msg = "没有网络...";
        } else if (e instanceof SocketTimeoutException) {
            msg = "超时...";
        } else {
            msg = "请求失败,异常错误,请稍后重试..." + e.getMessage();
        }
        _onError(msg);
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onStart() {

    }

    /**
     * On next.
     *
     * @param t the t
     */
    public abstract void _onNext(T t);

    /**
     * On error.
     *
     * @param msg the msg
     */
    public abstract void _onError(String msg);
}