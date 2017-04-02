package com.skyseraph.xknife.mod.uselogin.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.skyseraph.xknife.bridge.action.ILogin;
import com.skyseraph.xknife.bridge.core.IntentWrapper;
import com.skyseraph.xknife.bridge.core.Interceptor;
import com.skyseraph.xknife.bridge.core.XBridge;
import com.skyseraph.xknife.lib.utils.android.base.BaseActivity;
import com.skyseraph.xknife.mod.uselogin.R;

/**
 * Created by SkySeraph on 2016/10/23.
 */
public class LoginTest extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_test;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        XBridge bridge = new XBridge.Builder().interceptor(new Interceptor() {
            @Override
            public boolean intercept(IntentWrapper intentWrapper) {
                return false;  // 不做拦截
            }
        }).build();

        final ILogin launch = bridge.create(ILogin.class, this);

        // Method 1
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // By Bridge
                launch.startLogin("Hi");
                // Static
//                ActivityUtils.startActivity(LaunchTest.this, SplashActivity.class);
            }
        });

    }

    @Override
    public void initView() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("#requestCode: ", String.valueOf(requestCode));
        Log.d("#resultCode: ", String.valueOf(resultCode));
    }
}
