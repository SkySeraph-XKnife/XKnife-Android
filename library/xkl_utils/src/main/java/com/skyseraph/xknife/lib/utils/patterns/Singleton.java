package com.skyseraph.xknife.lib.utils.patterns;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by SkySeraph on 2016/2/28 06:42
 *
 * @param <T> the type parameter
 */
public class Singleton<T> {

    private static final ConcurrentMap<Class, Object> INSTANCES_MAP = new ConcurrentHashMap<>();

    private Singleton() {

    }

    /**
     * Gets singleton.
     *
     * @param <T>  the type parameter
     * @param type the type
     * @return the singleton
     */
    public static <T> T getSingleton(Class<T> type) {
        Object ob = INSTANCES_MAP.get(type);
        try {
            if (ob == null) {
                synchronized (INSTANCES_MAP) {
                    ob = type.newInstance();
                    INSTANCES_MAP.put(type, ob);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) ob;
    }

    /**
     * Remove singleton.
     *
     * @param <T>  the type parameter
     * @param type the type
     */
    public static <T> void removeSingleton(Class<T> type) {
        INSTANCES_MAP.remove(type);
    }
}
