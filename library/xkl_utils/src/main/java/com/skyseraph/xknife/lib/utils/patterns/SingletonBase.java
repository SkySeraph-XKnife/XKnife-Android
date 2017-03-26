package com.skyseraph.xknife.lib.utils.patterns;

/**
 * Created by SkySeraph on 2016/2/28 06:45
 *
 * @param <T> the type parameter
 */
public abstract class SingletonBase<T> {

    private T instance;

    /**
     * New instance t.
     *
     * @return the t
     */
    protected abstract T newInstance();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public final T getInstance() {
        if (instance == null) {
            synchronized (SingletonBase.class) {
                if (instance == null) {
                    instance = newInstance();
                }
            }
        }
        return instance;
    }
}
