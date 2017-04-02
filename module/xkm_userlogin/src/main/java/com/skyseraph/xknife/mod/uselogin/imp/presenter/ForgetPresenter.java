package com.skyseraph.xknife.mod.uselogin.imp.presenter;


import com.skyseraph.xknife.lib.utils.android.mvp.IBaseView;
import com.skyseraph.xknife.lib.utils.task.TimerHelper;
import com.skyseraph.xknife.mod.uselogin.imp.model.AccountRegister;
import com.skyseraph.xknife.mod.uselogin.imp.model.LoginStatus;
import com.skyseraph.xknife.mod.uselogin.imp.util.LoginConstant;
import com.skyseraph.xknife.mod.uselogin.imp.view.IForgetView;

/**
 * Created by SkySeraph on 2016/10/23.
 */
public class ForgetPresenter extends BaseLoginPresenter {

    private IForgetView forgetView;
    private int verifyCodeLen = LoginConstant.VERIFICODE_LEN;
    private TimerHelper timer;

    @Override
    public void attachView(IBaseView view) {
        if (view == null) {
            return;
        }
        this.forgetView = (IForgetView) view;
        this.timer = new TimerHelper() {
            @Override
            public void run() {
                if (forgetView != null) {
                    forgetView.setVerifiCodeStatus(true, verifyCodeLen);
                    if (verifyCodeLen < 1) {
                        forgetView.setVerifiCodeStatus(false, verifyCodeLen);
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
        this.forgetView = null;
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
        if (forgetView == null) {
            return;
        }
        if (!checkLoginPara(forgetView.getContext(), LoginStatus.CheckType.PHONE, true, phone)) {
            forgetView.loginFailed(LoginStatus.FailedStatus.ILLEGALITY_ACCOUNT);
            return;
        }

        // TODO: 2016/3/23


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
     * Update account.
     *
     * @param account the account
     */
    public void updateAccount(AccountRegister account) {

    }
}
