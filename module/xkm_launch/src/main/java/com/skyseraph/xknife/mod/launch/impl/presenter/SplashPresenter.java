package com.skyseraph.xknife.mod.launch.impl.presenter;

import android.os.Handler;

import com.skyseraph.xknife.lib.utils.android.mvp.IBasePresenter;
import com.skyseraph.xknife.mod.launch.impl.model.GuideInfo;
import com.skyseraph.xknife.mod.launch.impl.util.LaunchConstant;
import com.skyseraph.xknife.mod.launch.impl.view.ISplashView;

/**
 * Created by SkySeraph on 2016/10/10.
 */
public class SplashPresenter implements IBasePresenter<ISplashView> {

    private ISplashView splashView;

    @Override
    public void attachView(ISplashView view) {
        this.splashView = view;
    }

    @Override
    public void detachView() {
        this.splashView = null;
    }

    /**
     * Start splash.
     */
    public void startSplash() {
        if (splashView == null) {
            return;
        }

        // TODO: 2016/10/10 简单粗暴处理方法, 一般建议用定时器，同时可能加入网络请求相关逻辑
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                if (GuideInfo.isGuideShow(splashView.getContext())) {
                    splashView.outLaunch();
                } else {
                    splashView.enterGuide();
                    GuideInfo.setGuideShow(splashView.getContext(), true);
                }
            }
        }, LaunchConstant.SPLASH_WAIT_TIME);
    }
}
