package com.skyseraph.xknife.mod.launch.impl.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.skyseraph.xknife.lib.utils.android.base.BaseActivity;
import com.skyseraph.xknife.mod.launch.R;
import com.skyseraph.xknife.mod.launch.impl.presenter.GuidePresenter;
import com.skyseraph.xknife.mod.launch.test.LaunchUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SkySeraph on 2016/10/10.
 */
public class GuideActivity extends BaseActivity implements IGuideView, View.OnClickListener {

    private GuidePresenter guildPresenter;
    private ViewPager guideViewPager;
    private Button enterBtn;

    // 引导页图片资源
    private static final int[] guidePics = {R.layout.view_guide_1, R.layout.view_guide_2, R.layout.view_guide_3};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        guideViewPager = (ViewPager) findViewById(R.id.vp_guide);

        guildPresenter = new GuidePresenter();
        guildPresenter.attachView(this);
        guildPresenter.initData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (guildPresenter != null) {
            guildPresenter.detachView();
        }
    }

    @Override
    public void bindAdapterData(List data) {

        if (guideViewPager != null && data != null) {
            guideViewPager.setAdapter(new GuideViewPagerAdapter(data));
//            guideViewPager.setOnPageChangeListener(new PageChangeListener());
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public List getGuideViews() {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < guidePics.length; i++) {
            View view = LayoutInflater.from(this).inflate(guidePics[i], null);

            if (i == guidePics.length - 1) {
                enterBtn = (Button) view.findViewById(R.id.btn_login);
                if (enterBtn != null) {

                }
                enterBtn.setOnClickListener(this);
            }
            views.add(view);
        }
        return views;
    }

    @Override
    public void outLaunch() {
        setResult(RESULT_OK);
        this.finish();
        LaunchUtils.goToMain(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            if (guildPresenter != null) {
                guildPresenter.outClick();
            }
        }
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
        return 0;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

    }
}
