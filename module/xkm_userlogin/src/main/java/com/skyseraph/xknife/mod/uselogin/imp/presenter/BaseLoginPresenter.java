package com.skyseraph.xknife.mod.uselogin.imp.presenter;

import android.content.Context;

import com.skyseraph.xknife.lib.utils.android.mvp.IBasePresenter;
import com.skyseraph.xknife.mod.uselogin.imp.model.LoginStatus;
import com.skyseraph.xknife.mod.uselogin.imp.util.verify.UserVerifyBase;
import com.skyseraph.xknife.mod.uselogin.imp.util.verify.UserVerifyImpl;


/**
 * Created by SkySeraph on 2016/10/23.
 */
public abstract class BaseLoginPresenter implements IBasePresenter {

    /**
     * Check login para boolean.
     *
     * @param context   the context
     * @param type      the type
     * @param showToast the show toast
     * @param para      the para
     * @return the boolean
     */
    protected boolean checkLoginPara(Context context, LoginStatus.CheckType type, boolean showToast, String para) {
        UserVerifyBase verifyUser = new UserVerifyImpl(context, showToast);
        switch (type) {
            case ACCOUNT:
                return verifyUser.verifyUserName(para);
            case PASSWD:
                return verifyUser.verifyPassword(para);
            case CAPTCHA:
                return verifyUser.verifyCaptcha(para);
            case PHONE:
                return verifyUser.verifyPhone(para);
        }

        return false;
    }
}
