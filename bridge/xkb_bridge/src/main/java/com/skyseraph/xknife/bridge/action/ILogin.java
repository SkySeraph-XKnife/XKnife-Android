package com.skyseraph.xknife.bridge.action;


import com.skyseraph.xknife.bridge.annotation.ClassName;
import com.skyseraph.xknife.bridge.annotation.ParaKey;
import com.skyseraph.xknife.bridge.annotation.RequestCode;
import com.skyseraph.xknife.bridge.constant.ClassNames;
import com.skyseraph.xknife.bridge.constant.ReqCodeConstant;
import com.skyseraph.xknife.bridge.core.IntentWrapper;

/**
 * Created by SkySeraph on 2016/3/11.
 */
public interface ILogin {

    /**
     * The constant loginPara.
     */
    String loginPara = "para";

    /**
     * Start login.
     *
     * @param test the test
     */
    @ClassName(ClassNames.LOGIN_ACTIVITY)
    @RequestCode(ReqCodeConstant.LAUNCH_REQUEST_CODE)
    void startLogin(@ParaKey(loginPara) String test);

    /**
     * Start login ret intent wrapper.
     *
     * @param test the test
     * @return the intent wrapper
     */
    @ClassName(ClassNames.LOGIN_ACTIVITY)
    IntentWrapper startLoginRet(@ParaKey(loginPara) String test);

    /**
     * Goto main.
     */
    @ClassName(ClassNames.MAIN_ACTIVITY)
    void gotoMain();
}
