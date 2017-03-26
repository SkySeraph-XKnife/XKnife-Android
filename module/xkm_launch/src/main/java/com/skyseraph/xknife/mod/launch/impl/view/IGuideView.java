package com.skyseraph.xknife.mod.launch.impl.view;


import com.skyseraph.xknife.lib.utils.android.mvp.IBaseView;

import java.util.List;


/**
 * Created by SkySeraph on 2016/10/10.
 *
 * @param <T> the type parameter
 */
public interface IGuideView<T> extends IBaseView {

    /**
     * Bind adapter data.
     *
     * @param data the data
     */
    void bindAdapterData(List<T> data);

    /**
     * Gets guide views.
     *
     * @return the guide views
     */
    List<T> getGuideViews();

    /**
     * Out launch.
     */
    void outLaunch();
}
