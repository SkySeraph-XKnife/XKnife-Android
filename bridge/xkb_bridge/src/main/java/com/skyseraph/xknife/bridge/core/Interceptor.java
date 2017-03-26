package com.skyseraph.xknife.bridge.core;

/**
 * Created by SkySeraph on 2016/3/15.
 */
public interface Interceptor {
    /**
     * return true to intercept executor
     *
     * @param intentWrapper the intent wrapper
     * @return boolean
     */
    boolean intercept(IntentWrapper intentWrapper);
}

