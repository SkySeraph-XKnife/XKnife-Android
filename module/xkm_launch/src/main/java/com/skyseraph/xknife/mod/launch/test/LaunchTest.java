package com.skyseraph.xknife.mod.launch.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.skyseraph.xknife.bridge.action.ILaunch;
import com.skyseraph.xknife.bridge.core.IntentWrapper;
import com.skyseraph.xknife.bridge.core.Interceptor;
import com.skyseraph.xknife.bridge.core.XBridge;
import com.skyseraph.xknife.lib.utils.android.base.BaseActivity;
import com.skyseraph.xknife.mod.launch.R;

/**
 * Created by SkySeraph on 2016/10/10.
 */
public class LaunchTest extends BaseActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("#requestCode: ", String.valueOf(requestCode));
        Log.d("#resultCode: ", String.valueOf(resultCode));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        XBridge bridge = new XBridge.Builder().interceptor(new Interceptor() {
            @Override
            public boolean intercept(IntentWrapper intentWrapper) {
                return false;  // 不做拦截
            }
        }).build();

        final ILaunch launch = bridge.create(ILaunch.class, this);

        // Method 1
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // By Bridge
                launch.startLaunch("Hi");
                // Static
//                ActivityUtils.startActivity(LaunchTest.this, SplashActivity.class);
            }
        });

        // Method 2
//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                IntentWrapper intentWrapper = launch.startLaunchRet("test2");
//                Intent intent = intentWrapper.getIntent();
//                intent.putExtra("listener", BridgeProxy.newProxy(ILaunchReturn.class, new ILaunchReturn() {
//                    @Override
//                    public void startMain() {
//                        Log.d("#Launch KO, return ", "");
//                        ActivityUtils.startActivity(LaunchTest.this, MainActivity.class);
//                    }
//                }));
//                // intentWrapper.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                intentWrapper.start();
//            }
//        });
    }
}
