package com.skyseraph.xknife.mod.uselogin.imp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.skyseraph.xknife.lib.utils.android.base.BaseActivity;
import com.skyseraph.xknife.lib.module.task.HandlerHepler;
import com.skyseraph.xknife.mod.uselogin.R;
import com.skyseraph.xknife.mod.uselogin.imp.model.AccountRegister;
import com.skyseraph.xknife.mod.uselogin.imp.model.LoginStatus;
import com.skyseraph.xknife.mod.uselogin.imp.presenter.RegisterPresenter;


/**
 * Created by SkySeraph on 2016/10/23.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener, IRegisterView {

    private ImageView logoImageView;
    private EditText phoneET;
    private EditText passwordET;
    private EditText verifyCodeET;
    private EditText nicknameET;
    private Button getVerifiCodeButton;
    private RegisterPresenter registerPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        logoImageView = getViewById(R.id.iv_logo);
        phoneET = getViewById(R.id.et_phone);
        passwordET = getViewById(R.id.et_password);
        verifyCodeET = getViewById(R.id.et_verificode);
        nicknameET = getViewById(R.id.et_nickname);
        getVerifiCodeButton = getViewById(R.id.btn_send_verifi_code);
        getVerifiCodeButton.setOnClickListener(this);
        registerPresenter = new RegisterPresenter();
        registerPresenter.attachView(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (registerPresenter != null) {
            registerPresenter.detachView();
        }
    }

    @Override
    public void onClick(View v) {
        if (registerPresenter == null) {
            return;
        }
        int i = v.getId();
        if (i == R.id.btn_create_account) {
            AccountRegister account = new AccountRegister(phoneET.getText().toString(), passwordET.getText().toString(), nicknameET.getText().toString(), verifyCodeET.getText().toString());
            registerPresenter.createAccount(account);

        } else if (i == R.id.btn_send_verifi_code) {
            registerPresenter.getVerifyCode(phoneET.getText().toString());

        } else {
        }
    }

    @Override
    public void loginFailed(LoginStatus.FailedStatus status) {

    }

    @Override
    public void loginSuccessed() {

    }

    @Override
    public void setVerifiCodeStatus(final boolean off, final int len) {

        HandlerHepler.getMainHandler().post(new Runnable() { // TODO: 2016/10/23 Optimeze
            @Override
            public void run() {
                if (off) {
                    getVerifiCodeButton.setText(String.valueOf(len) + "秒");
                    getVerifiCodeButton.setClickable(false);
                    getVerifiCodeButton.setTextColor(getResources().getColor(R.color.verifiCodeButtonOff));
                    getVerifiCodeButton.setBackgroundColor(getResources().getColor(R.color.verifiCodeButtonBgOff));
                    registerPresenter.updateVerifiCode();
                } else {
                    getVerifiCodeButton.setText("重新发送");
                    getVerifiCodeButton.setTextColor(getResources().getColor(R.color.verifiCodeButtonOn));
                    getVerifiCodeButton.setBackgroundColor(getResources().getColor(R.color.verifiCodeButtonBgOn));
                    registerPresenter.resetVerifiCode();
                    getVerifiCodeButton.setClickable(true);
                }
            }
        });

    }
}
