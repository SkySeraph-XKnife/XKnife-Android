package com.skyseraph.xknife.lib.ximage.core.option;


import com.skyseraph.xknife.lib.ximage.core.XImageConfig;

/**
 * Created by SkySeraph on 2016/8/8.
 */
public class AnimateOption {

    private int animate = XImageConfig.LOAD_ANIMATE;

    /**
     * Instantiates a new Animate option.
     */
    public AnimateOption() {
    }

    /**
     * Instantiates a new Animate option.
     *
     * @param animate the animate
     */
    public AnimateOption(int animate) {
        this.animate = animate;
    }

    /**
     * Gets animate.
     *
     * @return the animate
     */
    public int getAnimate() {
        return animate;
    }
}
