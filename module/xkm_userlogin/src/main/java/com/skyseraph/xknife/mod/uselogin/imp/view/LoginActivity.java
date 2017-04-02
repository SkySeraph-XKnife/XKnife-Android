package com.skyseraph.xknife.mod.uselogin.imp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.skyseraph.xknife.lib.utils.android.base.BaseActivity;
import com.skyseraph.xknife.mod.uselogin.R;
import com.skyseraph.xknife.mod.uselogin.imp.model.AccountRegister;
import com.skyseraph.xknife.mod.uselogin.imp.model.LoginStatus;
import com.skyseraph.xknife.mod.uselogin.imp.presenter.LoginPresenter;

/**
 *  Created by SkySeraph on 2016/10/23.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginView {

    private View thirdLoginView;
    private ImageView logoImageView;
    private TextInputLayout usenameTIL;
    private TextInputLayout passwordTIL;
    private EditText usenameET;
    private EditText passwordET;
    private LoginPresenter loginPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        thirdLoginView = getViewById(R.id.ll_view_third_login_btn);
        logoImageView = getViewById(R.id.iv_logo);
        usenameTIL = getViewById(R.id.til_username);
        passwordTIL = getViewById(R.id.til_password);
        usenameET = getViewById(R.id.et_username);
        passwordET = getViewById(R.id.et_password);
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginPresenter != null) {
            loginPresenter.detachView();
        }
    }

    @Override
    public void onClick(View v) {
        if (loginPresenter == null) {
            return;
        }
        int i = v.getId();
        if (i == R.id.iv_cancel) {
            loginPresenter.doExitLogin();

        } else if (i == R.id.btn_login) {
            loginPresenter.doLogin(usenameET.getText().toString(), passwordET.getText().toString());

        } else if (i == R.id.tv_create_account) {
            loginPresenter.goRegisterActivity();

        } else if (i == R.id.tv_forget_password) {
            loginPresenter.goForgetActivity();

        } else if (i == R.id.iv_wechat) {
            loginPresenter.doThirdWechatLogin();

        } else if (i == R.id.iv_qq) {
            loginPresenter.doThirdQQLogin();

        } else if (i == R.id.iv_sina) {
            loginPresenter.doThirdSinaLogin();

        } else {
        }
    }

    @Override
    public void loginFailed(LoginStatus.FailedStatus status) {
        switch (status) {
            case ILLEGALITY_ACCOUNT:
                break;
            case ILLEGALITY_PASSWD:
                break;
            default:
                break;
        }
    }

    @Override
    public void loginSuccessed() {
        // TODO: 2017/3/23 goto Main 
    }

    @Override
    public void finishLoginView() {
        this.finish();
    }
}
