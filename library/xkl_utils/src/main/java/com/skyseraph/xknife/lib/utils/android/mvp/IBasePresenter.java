package com.skyseraph.xknife.lib.utils.android.mvp;

/**
 * Created by SkySeraph on 2016/3/10.
 *
 * @param <T> the type parameter
 */
public interface IBasePresenter<T extends IBaseView> {

    /**
     * Attach view.
     *
     * @param view the view
     */
    void attachView(T view);

    /**
     * Detach view.
     */
    void detachView();
}