package com.skyseraph.xknife.mod.uselogin.imp.util.verify;

import android.content.Context;
import android.text.TextUtils;

import com.skyseraph.xknife.lib.utils.android.ToastUtils;
import com.skyseraph.xknife.lib.utils.string.RegexUtils;


/**
 * Created by SkySeraph on 2016/10/23.
 */
public class UserVerifyImpl extends UserVerifyBase {

    private Context context;

    /**
     * Instantiates a new User verify.
     *
     * @param context the context
     */
    public UserVerifyImpl(Context context) {
        this.context = context;
        this.showToast = false;
    }

    /**
     * Instantiates a new User verify.
     *
     * @param context   the context
     * @param showToast the show toast
     */
    public UserVerifyImpl(Context context, boolean showToast) {
        this.context = context;
        this.showToast = showToast;
    }

    @Override
    public boolean verifyPhone(String phone) {

        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShortToast(context, "手机号码不能为空");
            return false;
        } else if (phone.length() < 11) {
            ToastUtils.showShortToast(context, "手机号码格式错误");
            return false;
        } else if (!RegexUtils.checkMobile(phone)) {
            ToastUtils.showShortToast(context, "手机号码格式错误");
            return false;
        }
        return true;
    }

    @Override
    public boolean verifyUserName(String phoneOrEmail) {
        boolean flag = UserVerifyUtils.isValidEmail(phoneOrEmail)
                || UserVerifyUtils.isValidPhoneNumber(phoneOrEmail);
        if (!flag && showToast) {
            ToastUtils.showShortToast(context, "账户格式错误");
        }
        return flag;
    }

    @Override
    public boolean verifyPassword(String password) {
        boolean flag = UserVerifyUtils.isValidPassword(password);
        if (!flag && showToast) {
            ToastUtils.showShortToast(context, "密码格式错误");
        }
        return flag;
    }

    @Override
    public boolean verifyCaptcha(String captcha) {
        boolean flag = UserVerifyUtils.isValidCaptcha(captcha);
        if (!flag && showToast) {
            ToastUtils.showShortToast(context, "验证码格式错误");
        }
        return flag;
    }


}
