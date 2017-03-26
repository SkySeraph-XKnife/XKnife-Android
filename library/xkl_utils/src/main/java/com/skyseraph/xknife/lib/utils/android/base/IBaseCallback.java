package com.skyseraph.xknife.lib.utils.android.base;

import android.os.Bundle;

/**
 * Created by SkySeraph on 2015/12/17.
 */
public interface IBaseCallback {

    /**
     * Gets layout id.
     *
     * @return the layout id
     */
    int getLayoutId();

    /**
     * Init data.
     *
     * @param savedInstanceState the saved instance state
     */
    void initData(Bundle savedInstanceState);

    /**
     * Init view.
     */
    void initView();
}
