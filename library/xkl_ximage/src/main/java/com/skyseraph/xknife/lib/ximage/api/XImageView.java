package com.skyseraph.xknife.lib.ximage.api;

import android.widget.ImageView;

import com.skyseraph.xknife.lib.ximage.core.option.AnimateOption;
import com.skyseraph.xknife.lib.ximage.core.option.HolderOption;
import com.skyseraph.xknife.lib.ximage.core.option.SizeOption;
import com.skyseraph.xknife.lib.ximage.core.option.ThumbnailOption;

/**
 * Created by SkySeraph on 2016/8/8.
 *
 * @param <T> the type parameter
 */
public class XImageView<T> {

    /**
     * T = ?
     * 本地路径  Uri
     * 网络路径 String
     * 文件 File
     * 资源Id Integer
     */
    private T url;

    private ImageView imageView;

    private SizeOption imageSize;

    private HolderOption holderOption;

    private AnimateOption animateOption; // 加载动画

    private ThumbnailOption thumbnailOption;

    private XImageView(Builder builder) {
        this.url = (T) builder.url;
        this.imageView = builder.imageView;
        this.imageSize = builder.imageSize;
        this.holderOption = builder.holderOption;
        this.animateOption = builder.animateOption;
        this.thumbnailOption = builder.thumbnailOption;
    }

    /**
     * Gets animate option.
     *
     * @return the animate option
     */
    public AnimateOption getAnimateOption() {
        return animateOption;
    }

    /**
     * Gets holder option.
     *
     * @return the holder option
     */
    public HolderOption getHolderOption() {
        return holderOption;
    }

    /**
     * Gets image size.
     *
     * @return the image size
     */
    public SizeOption getImageSize() {
        return imageSize;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public T getUrl() {
        return url;
    }

    /**
     * Gets thumbnail option.
     *
     * @return the thumbnail option
     */
    public ThumbnailOption getThumbnailOption() {
        return thumbnailOption;
    }

    /**
     * Gets image view.
     *
     * @return the image view
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * The type Builder.
     *
     * @param <T> the type parameter
     */
    public static final class Builder<T> {
        private T url;
        private ImageView imageView;
        private SizeOption imageSize;
        private HolderOption holderOption;
        private AnimateOption animateOption;
        private ThumbnailOption thumbnailOption;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            this.url = null;
            this.imageView = null;
            this.holderOption = new HolderOption();
            this.imageSize = new SizeOption(this.imageView);
            this.animateOption = new AnimateOption();
            this.thumbnailOption = new ThumbnailOption();
        }

        /**
         * Url builder.
         *
         * @param url the url
         * @return the builder
         */
        public Builder url(T url) {
            this.url = url;
            return this;
        }

        /**
         * Holder option builder.
         *
         * @param holderOption the holder option
         * @return the builder
         */
        public Builder holderOption(HolderOption holderOption) {
            this.holderOption = holderOption;
            return this;
        }

        /**
         * Image view builder.
         *
         * @param imageView the image view
         * @return the builder
         */
        public Builder imageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        /**
         * Image size builder.
         *
         * @param imageSize the image size
         * @return the builder
         */
        public Builder imageSize(SizeOption imageSize) {
            this.imageSize = imageSize;
            return this;
        }

        /**
         * Thumbnail option builder.
         *
         * @param thumbnailOption the thumbnail option
         * @return the builder
         */
        public Builder thumbnailOption(ThumbnailOption thumbnailOption) {
            this.thumbnailOption = thumbnailOption;
            return this;
        }

        /**
         * Animate option builder.
         *
         * @param animateOption the animate option
         * @return the builder
         */
        public Builder animateOption(AnimateOption animateOption) {
            this.animateOption = animateOption;
            return this;
        }

        /**
         * Build x image view.
         *
         * @return the x image view
         */
        public XImageView build() {
            return new XImageView(this);
        }
    }
}
