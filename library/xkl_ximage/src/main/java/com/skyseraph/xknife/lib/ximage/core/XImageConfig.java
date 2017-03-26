package com.skyseraph.xknife.lib.ximage.core;

/**
 * Created by SkySeraph on 2016/8/8.
 */
public class XImageConfig {

    /**
     * The constant USE_PLACEHOLDER.
     * 使用 placeHolder
     */
    public static final boolean USE_PLACEHOLDER = true;
    /**
     * The constant USE_ERRORHOLDER.
     * 使用 errorHolder
     */
    public static final boolean USE_ERRORHOLDER = true;

    /**
     * The constant LOAD_STRATEGY_NORMAL.
     */
    public static final int LOAD_STRATEGY_NORMAL = 0;
    /**
     * The constant LOAD_STRATEGY_ONLY_WIFI.
     */
    public static final int LOAD_STRATEGY_ONLY_WIFI = 1;


    /**
     * The constant NOMAL_PLACEHOLDER.
     */
    public static final int NOMAL_PLACEHOLDER = android.R.drawable.picture_frame;

    /**
     * The constant IMAGE_GLIDE.
     */
    public static final int IMAGE_GLIDE = 100;
    /**
     * The constant IMAGE_FRESCO.
     */
    public static final int IMAGE_FRESCO = 101;
    /**
     * The constant IMAGE_PICASSO.
     */
    public static final int IMAGE_PICASSO = 102;

    /**
     * The constant USE_THUMBNAIL.
     * 使用缩略图
     */
    public static final boolean USE_THUMBNAIL = true;
    /**
     * The constant SIZE_MULTIPLIER.
     * 显示原始图像的10%的大小為缩略图
     */
    public static final float SIZE_MULTIPLIER = 0.1f;

    /**
     * The constant LOAD_ANIMATE.
     */
    public static final int LOAD_ANIMATE = 0;

}
