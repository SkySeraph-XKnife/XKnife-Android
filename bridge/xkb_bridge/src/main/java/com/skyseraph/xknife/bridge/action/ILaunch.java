package com.skyseraph.xknife.bridge.action;


import com.skyseraph.xknife.bridge.annotation.ClassName;
import com.skyseraph.xknife.bridge.annotation.ParaKey;
import com.skyseraph.xknife.bridge.annotation.RequestCode;
import com.skyseraph.xknife.bridge.constant.ReqCodeConstant;
import com.skyseraph.xknife.bridge.core.IntentWrapper;

/**
 * Created by SkySeraph on 2016/3/11.
 */
public interface ILaunch {

    /**
     * The constant startLaunchPara.
     */
    String startLaunchPara = "para";

    /**
     * Start launch.
     *
     * @param test the test
     */
    @ClassName("game.mtf.huawei.com.launch.impl.view.SplashActivity")
    @RequestCode(ReqCodeConstant.LAUNCH_REQUEST_CODE)
    void startLaunch(@ParaKey(startLaunchPara) String test);

    /**
     * Start launch ret intent wrapper.
     *
     * @param test the test
     * @return the intent wrapper
     */
    @ClassName("game.mtf.huawei.com.launch.impl.view.SplashActivity")
    IntentWrapper startLaunchRet(@ParaKey(startLaunchPara) String test);

    /**
     * To main activity.
     */
    @ClassName("game.mtf.huawei.com.launch.test.MainActivity")
    void toMainActivity();
}
