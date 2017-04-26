package com.skyseraph.xknife.c2_2;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by SkySeraph on 2016/4/26.
 */

class WeakProxy implements InvocationHandler {
    private WeakReference<Runnable> weakReference;

    public WeakProxy(Runnable runnable) {
        weakReference = new WeakReference<>(runnable);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object proxied = weakReference.get();
        return proxied == null ? null : method.invoke(proxied, args);
    }

    private static <T> T wrap(T object) {
        if (object == null) {
            return null;
        }
        return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(), new WeakProxy((Runnable) object));
    }

    public static <T> T wrap(IWeakHost host, T object) {
        if (host != null && object != null) {
            host.referObject(object);
        }
        return wrap(object);
    }

    public interface IWeakHost {
        void referObject(Object obj);
    }
}
