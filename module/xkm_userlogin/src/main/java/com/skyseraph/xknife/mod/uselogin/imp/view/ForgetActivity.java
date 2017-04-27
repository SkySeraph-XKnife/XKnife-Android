package com.skyseraph.xknife.mod.uselogin.imp.view;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.skyseraph.xknife.lib.utils.android.base.BaseActivity;
import com.skyseraph.xknife.lib.module.task.HandlerHepler;
import com.skyseraph.xknife.mod.uselogin.R;
import com.skyseraph.xknife.mod.uselogin.imp.model.AccountRegister;
import com.skyseraph.xknife.mod.uselogin.imp.model.LoginStatus;
import com.skyseraph.xknife.mod.uselogin.imp.presenter.ForgetPresenter;


/**
 * Created by SkySeraph on 2016/10/23.
 */
public class ForgetActivity extends BaseActivity implements View.OnClickListener, IForgetView {

    private ImageView logoImageView;
    private EditText phoneET;
    private EditText passwordET;
    private EditText verifyCodeET;
    private Button getVerifiCodeButton;
    private ForgetPresenter forgetPresenter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_forget;
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
        getVerifiCodeButton = getViewById(R.id.btn_send_verifi_code);
        getVerifiCodeButton.setOnClickListener(this);
        forgetPresenter = new ForgetPresenter();
        forgetPresenter.attachView(this);
        phoneET.addTextChangedListener(new PhoneTextChange());
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (forgetPresenter != null) {
            forgetPresenter.detachView();
        }
    }

    @Override
    public void onClick(View v) {
        if (forgetPresenter == null) {
            return;
        }
        int id = v.getId();
        if (R.id.btn_create_account == id) {
            AccountRegister account = new AccountRegister(phoneET.getText().toString(), passwordET.getText().toString(), "", verifyCodeET.getText().toString());
            forgetPresenter.updateAccount(account);
        } else if (R.id.btn_send_verifi_code == id) {
            forgetPresenter.getVerifyCode(phoneET.getText().toString());
        }
    }

    @Override
    public void setVerifiCodeStatus(final boolean off, final int len) {

        HandlerHepler.getMainHandler().post(new Runnable() { // TODO: 2016/10/23 Optimeze
            @Override
            public void run() {
                if (off) {
                    getVerifiCodeButton.setText(String.valueOf(len) + "秒");
                    getVerifiCodeButton.setEnabled(false);
                    getVerifiCodeButton.setTextColor(getResources().getColor(R.color.verifiCodeButtonOff));
                    getVerifiCodeButton.setBackgroundColor(getResources().getColor(R.color.verifiCodeButtonBgOff));
                    forgetPresenter.updateVerifiCode();
                } else {
                    getVerifiCodeButton.setText("重新发送");
                    getVerifiCodeButton.setTextColor(getResources().getColor(R.color.verifiCodeButtonOn));
                    getVerifiCodeButton.setBackgroundColor(getResources().getColor(R.color.verifiCodeButtonBgOn));
                    forgetPresenter.resetVerifiCode();
                    getVerifiCodeButton.setEnabled(true);
                }
            }
        });

    }

    @Override
    public void loginFailed(LoginStatus.FailedStatus status) {

    }

    @Override
    public void loginSuccessed() {

    }

    /**
     * The type Phone text change.
     */
    class PhoneTextChange implements TextWatcher {

        @Override
        public void afterTextChanged(Editable arg0) {

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {

        }

        @Override
        public void onTextChanged(CharSequence cs, int start, int before,
                                  int count) {
            String phone = phoneET.getText().toString();
            if (phone.length() == 11) {
                getVerifiCodeButton.setTextColor(getResources().getColor(R.color.verifiCodeButtonOn));
                getVerifiCodeButton.setBackgroundColor(getResources().getColor(R.color.verifiCodeButtonBgOn));
                getVerifiCodeButton.setEnabled(true);
            } else {
                getVerifiCodeButton.setTextColor(getResources().getColor(R.color.verifiCodeButtonOff));
                getVerifiCodeButton.setBackgroundColor(getResources().getColor(R.color.verifiCodeButtonBgOff));
                getVerifiCodeButton.setEnabled(false);
            }
        }
    }
}
