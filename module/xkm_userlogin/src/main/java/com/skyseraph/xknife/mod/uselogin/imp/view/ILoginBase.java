package com.skyseraph.xknife.mod.uselogin.imp.view;


import com.skyseraph.xknife.lib.utils.android.mvp.IBaseView;
import com.skyseraph.xknife.mod.uselogin.imp.model.LoginStatus;

/**
 * Created by SkySeraph on 2016/10/23.
 */
public interface ILoginBase extends IBaseView {

    /**
     * Login failed.
     *
     * @param status the status
     */
    void loginFailed(LoginStatus.FailedStatus status);

    /**
     * Login successed.
     */
    void loginSuccessed();
}
