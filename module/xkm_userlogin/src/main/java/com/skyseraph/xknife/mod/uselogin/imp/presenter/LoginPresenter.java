package com.skyseraph.xknife.mod.uselogin.imp.presenter;


import com.skyseraph.xknife.lib.utils.android.ActivityUtils;
import com.skyseraph.xknife.lib.utils.android.mvp.IBaseView;
import com.skyseraph.xknife.mod.uselogin.imp.model.LoginStatus;
import com.skyseraph.xknife.mod.uselogin.imp.view.ForgetActivity;
import com.skyseraph.xknife.mod.uselogin.imp.view.ILoginView;
import com.skyseraph.xknife.mod.uselogin.imp.view.RegisterActivity;

/**
 * Created by SkySeraph on 2016/10/23.
 */
public class LoginPresenter extends BaseLoginPresenter {

    private ILoginView loginView;


    @Override
    public void attachView(IBaseView view) {
        if (view == null) {
            return;
        }
        this.loginView = (ILoginView) view;
    }

    @Override
    public void detachView() {
        this.loginView = null;
    }

    /**
     * Do exit login.
     */
    public void doExitLogin() {

    }

    /**
     * Do login.
     *
     * @param account  the account
     * @param password the password
     */
    public void doLogin(String account, String password) {
        if (loginView == null || account == null || password == null) {
            return;
        }
        if (!checkLoginPara(loginView.getContext(), LoginStatus.CheckType.ACCOUNT, true, account)) {
            loginView.loginFailed(LoginStatus.FailedStatus.ILLEGALITY_ACCOUNT);
        }
        if (!checkLoginPara(loginView.getContext(), LoginStatus.CheckType.PASSWD, true, password)) {
            loginView.loginFailed(LoginStatus.FailedStatus.ILLEGALITY_PASSWD);
        }

        // TODO: 2016/3/23 login
    }

    /**
     * Do third qq login.
     */
    public void doThirdQQLogin() {

        // TODO: 2016/3/23
    }

    /**
     * Do third wechat login.
     */
    public void doThirdWechatLogin() {

        // TODO: 2016/3/23
    }

    /**
     * Do third sina login.
     */
    public void doThirdSinaLogin() {

        // TODO: 2016/3/23
    }

    /**
     * Go forget activity.
     */
    public void goForgetActivity() {
        if (loginView != null) {
            ActivityUtils.startActivity(loginView.getContext(), ForgetActivity.class);
            loginView.finishLoginView();
        }
    }

    /**
     * Go register activity.
     */
    public void goRegisterActivity() {
        if (loginView != null) {
            ActivityUtils.startActivity(loginView.getContext(), RegisterActivity.class);
            loginView.finishLoginView();
        }
    }
}
