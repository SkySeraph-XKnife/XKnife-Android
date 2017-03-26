package com.skyseraph.xknife.mod.launch.test;


import android.content.Context;

import com.skyseraph.xknife.bridge.action.ILaunch;
import com.skyseraph.xknife.bridge.core.IntentWrapper;
import com.skyseraph.xknife.bridge.core.Interceptor;
import com.skyseraph.xknife.bridge.core.XBridge;


/**
 * Created by SkySeraph on 2016/10/10.
 */
public class LaunchUtils {

    /**
     * Go to main.
     *
     * @param context the context
     */
    public static void goToMain(Context context) {
        XBridge bridge = new XBridge.Builder().interceptor(new Interceptor() {
            @Override
            public boolean intercept(IntentWrapper intentWrapper) {
                return false;
            }
        }).build();

        final ILaunch launch = bridge.create(ILaunch.class, context);
        launch.toMainActivity();
    }
}
