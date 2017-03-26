package com.skyseraph.xknife.mod.launch.impl.presenter;


import com.skyseraph.xknife.lib.utils.android.mvp.IBasePresenter;
import com.skyseraph.xknife.mod.launch.impl.view.IGuideView;

/**
 * Created by SkySeraph on 2016/10/10.
 */
public class GuidePresenter implements IBasePresenter<IGuideView> {

    private IGuideView guideView;

    @Override
    public void attachView(IGuideView view) {
        this.guideView = view;
    }

    @Override
    public void detachView() {
        this.guideView = null;
    }

    /**
     * Init data.
     */
    public void initData() {
        if (guideView != null) {
            guideView.bindAdapterData(guideView.getGuideViews());
        }
    }

    /**
     * Out click.
     */
    public void outClick() {
        if (guideView != null) {
            guideView.outLaunch();
        }
    }
}
