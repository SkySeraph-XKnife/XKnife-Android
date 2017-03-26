package com.skyseraph.xknife.bridge.core;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
 * Created by SkySeraph on 2016/3/15.
 */
public class BridgeProxy implements Serializable {

    /**
     * New proxy t.
     *
     * @param <T> the type parameter
     * @param t   the t
     * @param o   the o
     * @return the t
     */
    public static <T> T newProxy(Class<T> t, T o) {
        MyHandle handle = new MyHandle(o.toString());
        T callBack = (T) Proxy.newProxyInstance(o.getClass().getClassLoader(), new Class[]{t},
                handle);
        ProxyStore.callBack.put(o.toString(), new WeakReference<Object>(o));
        return callBack;
    }

    private static class MyHandle implements InvocationHandler, Serializable {
        /**
         * The Generator.
         */
        String generator;

        /**
         * Instantiates a new My handle.
         *
         * @param generator the generator
         */
        MyHandle(String generator) {
            this.generator = generator;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, args);
            }
            if (ProxyStore.callBack.get(generator).get() == null) {
                return null;
            }
            return method.invoke(ProxyStore.callBack.get(generator).get(), args);
        }
    }


    private static class ProxyStore {
        private static HashMap<String, WeakReference<Object>> callBack = new HashMap<>();
    }
}
