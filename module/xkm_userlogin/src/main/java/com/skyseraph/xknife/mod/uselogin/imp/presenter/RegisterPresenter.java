package com.skyseraph.xknife.mod.uselogin.imp.presenter;


import com.skyseraph.xknife.lib.utils.android.mvp.IBaseView;
import com.skyseraph.xknife.lib.utils.task.TimerHelper;
import com.skyseraph.xknife.mod.uselogin.imp.model.AccountRegister;
import com.skyseraph.xknife.mod.uselogin.imp.model.LoginStatus;
import com.skyseraph.xknife.mod.uselogin.imp.util.LoginConstant;
import com.skyseraph.xknife.mod.uselogin.imp.view.IRegisterView;

/**
 * Created by SkySeraph on 2016/10/23.
 */
public class RegisterPresenter extends BaseLoginPresenter {

    private IRegisterView registerView;
    private int verifyCodeLen = LoginConstant.VERIFICODE_LEN;
    private TimerHelper timer;

    @Override
    public void attachView(IBaseView view) {
        if (view == null) {
            return;
        }
        this.registerView = (IRegisterView) view;
        this.timer = new TimerHelper() {
            @Override
            public void run() {
                if (registerView != null) {
                    registerView.setVerifiCodeStatus(true, verifyCodeLen);
                    if (verifyCodeLen < 1) {
                        registerView.setVerifiCodeStatus(false, verifyCodeLen);
                        timer.stop();
                    }
                } else {
                    timer.stop();
                }
            }
        };
    }

    @Override
    public void detachView() {
        this.registerView = null;
        if (timer != null) {
            timer.stop();
        }
    }

    /**
     * Gets verify code.
     *
     * @param phone the phone
     */
    public void getVerifyCode(String phone) {
        if (registerView == null) {
            return;
        }
        if (!checkLoginPara(registerView.getContext(), LoginStatus.CheckType.PHONE, true, phone)) {
            registerView.loginFailed(LoginStatus.FailedStatus.ILLEGALITY_ACCOUNT);
            return;
        }

        // TODO: 2017/3/23


        if (timer != null) {
            timer.start(0, LoginConstant.VERIFICODE_PERIOD);
        }
    }

    /**
     * Update verifi code.
     */
    public void updateVerifiCode() {
        verifyCodeLen--;
    }

    /**
     * Reset verifi code.
     */
    public void resetVerifiCode() {
        verifyCodeLen = LoginConstant.VERIFICODE_LEN;
    }

    /**
     * Create account.
     *
     * @param account the account
     */
    public void createAccount(AccountRegister account) {

    }
}
