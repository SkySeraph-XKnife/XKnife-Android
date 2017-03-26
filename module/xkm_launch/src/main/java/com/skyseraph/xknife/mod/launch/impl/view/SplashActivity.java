package com.skyseraph.xknife.mod.launch.impl.view;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;

import com.skyseraph.xknife.lib.utils.android.ActivityUtils;
import com.skyseraph.xknife.lib.utils.android.base.BaseActivity;
import com.skyseraph.xknife.mod.launch.R;
import com.skyseraph.xknife.mod.launch.impl.presenter.SplashPresenter;
import com.skyseraph.xknife.mod.launch.test.LaunchUtils;


/**
 * Created by SkySeraph on 2016/10/10.
 */
public class SplashActivity extends BaseActivity implements ISplashView {

    private SplashPresenter splashPresenter;

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void enterGuide() {
        ActivityUtils.startActivity(this, GuideActivity.class);
        this.finish();
    }

    @Override
    public void outLaunch() {
        setResult(RESULT_OK);
        this.finish();
        LaunchUtils.goToMain(this);
    }

    /**
     * 屏蔽物理返回按钮
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        splashPresenter = new SplashPresenter();
        splashPresenter.attachView(this);
        splashPresenter.startSplash();
    }
}
