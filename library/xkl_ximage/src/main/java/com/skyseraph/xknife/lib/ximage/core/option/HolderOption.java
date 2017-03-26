package com.skyseraph.xknife.lib.ximage.core.option;

import com.skyseraph.xknife.lib.ximage.core.XImageConfig;

/**
 * Created by SkySeraph on 2016/8/8.
 */
public class HolderOption {

    // 加载中图片
    private int placeHolder = XImageConfig.NOMAL_PLACEHOLDER;

    // 加载失败图片
    private int errorHolder = XImageConfig.NOMAL_PLACEHOLDER;

    /**
     * Instantiates a new Holder option.
     */
    public HolderOption() {
    }

    /**
     * Instantiates a new Holder option.
     *
     * @param placeHolder the place holder
     * @param errorHolder the error holder
     */
    public HolderOption(int placeHolder, int errorHolder) {
        this.placeHolder = placeHolder;
        this.errorHolder = errorHolder;
    }

    /**
     * Instantiates a new Holder option.
     *
     * @param placeHolder the place holder
     */
    public HolderOption(int placeHolder) {
        this.placeHolder = placeHolder;
    }

    /**
     * Gets place holder.
     *
     * @return the place holder
     */
    public int getPlaceHolder() {
        return placeHolder;
    }

    /**
     * Gets error holder.
     *
     * @return the error holder
     */
    public int getErrorHolder() {
        return errorHolder;
    }
}
