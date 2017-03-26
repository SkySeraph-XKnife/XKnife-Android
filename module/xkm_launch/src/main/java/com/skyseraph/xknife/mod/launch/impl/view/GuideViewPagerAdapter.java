package com.skyseraph.xknife.mod.launch.impl.view;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by SkySeraph on 2016/10/10.
 */
public class GuideViewPagerAdapter extends PagerAdapter {

    private List<View> views;

    /**
     * Instantiates a new Guide view pager adapter.
     *
     * @param views the views
     */
    public GuideViewPagerAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views != null ? views.size() : 0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position), 0);
        return views.get(position);
    }
}
