package com.skyseraph.xknife.lib.ximage.test;

import android.content.Context;
import android.widget.ImageView;

import com.skyseraph.xknife.lib.ximage.api.XImageLoader;
import com.skyseraph.xknife.lib.ximage.api.XImageView;
import com.skyseraph.xknife.lib.ximage.core.option.SizeOption;

/**
 * Created by SkySeraph on 2016/8/8.
 */
public class Test {

    /**
     * Load image.
     *
     * @param context the context
     * @param image   the image
     * @param url     the url
     */
    void loadImage(Context context, ImageView image, String url) {
        XImageView imageView = new XImageView.Builder().url(url).imageView(image).build();
        XImageLoader.getInstance().loadImage(context, imageView);
    }

    /**
     * Load image.
     *
     * @param context       the context
     * @param image         the image
     * @param resDrawableId the res drawable id
     */
    void loadImage(Context context, ImageView image, int resDrawableId) {
        XImageView imageView = new XImageView.Builder().url(resDrawableId).imageView(image).build();
        XImageLoader.getInstance().loadImage(context, imageView);
    }

    /**
     * Load image with thumbnail.
     *
     * @param context the context
     * @param image   the image
     * @param url     the url
     */
    void loadImageWithThumbnail(Context context, ImageView image, String url) {
        XImageView imageView = new XImageView.Builder().url(url).imageView(image).imageSize(new SizeOption(100, 100)).build();
        XImageLoader.getInstance().loadImageSize(context, imageView);
    }

    /**
     * Load image size.
     *
     * @param context the context
     * @param image   the image
     * @param url     the url
     */
    void loadImageSize(Context context, ImageView image, String url) {
        XImageView imageView = new XImageView.Builder().url(url).imageView(image).imageSize(new SizeOption(100, 100)).build();
        XImageLoader.getInstance().loadImageSize(context, imageView);
    }
}
