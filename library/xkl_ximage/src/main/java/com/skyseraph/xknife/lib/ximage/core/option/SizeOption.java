package com.skyseraph.xknife.lib.ximage.core.option;

import android.widget.ImageView;

/**
 * Created by SkySeraph on 2016/8/8.
 */
public class SizeOption {

    /**
     * The Width.
     */
    public int width;

    /**
     * The Height.
     */
    public int height;

    /**
     * Instantiates a new Size option.
     *
     * @param imageView the image view
     */
    public SizeOption(ImageView imageView) {
        this.width = (imageView != null ? imageView.getWidth() : 0);
        this.height = (imageView != null ? imageView.getHeight() : 0);
    }

    /**
     * Instantiates a new Size option.
     *
     * @param width  the width
     * @param height the height
     */
    public SizeOption(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
