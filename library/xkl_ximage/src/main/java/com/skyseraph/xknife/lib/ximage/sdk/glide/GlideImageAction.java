package com.skyseraph.xknife.lib.ximage.sdk.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.skyseraph.xknife.lib.ximage.api.XImageLoadListener;
import com.skyseraph.xknife.lib.ximage.api.XImageView;
import com.skyseraph.xknife.lib.ximage.core.XImageActionBase;
import com.skyseraph.xknife.lib.ximage.core.XImageConfig;
import com.skyseraph.xknife.lib.ximage.core.option.SizeOption;
import com.skyseraph.xknife.lib.ximage.sdk.glide.transformation.BlurTransformation;
import com.skyseraph.xknife.lib.ximage.sdk.glide.transformation.CircleTransform;

/**
 * Created by SkySeraph on 2016/8/8.
 */
public class GlideImageAction extends XImageActionBase {

    @Override
    public void loadImage(Context context, XImageView imageView) {
        Glide.with(context)
                .load(imageView.getUrl())
                .into(imageView.getImageView());
    }

    @Override
    public void loadImageWithHolder(Context context, XImageView imageView) {
        Glide.with(context)
                .load(imageView.getUrl())
                .error(imageView.getHolderOption().getErrorHolder())
                .placeholder(imageView.getHolderOption().getPlaceHolder())
                .into(imageView.getImageView());
    }

    @Override
    public void loadImageWithThumbnail(Context context, XImageView imageView) {
        String url = imageView.getThumbnailOption().getUrl();
        if (url == null) {
            Glide.with(context)
                    .load(imageView.getUrl())
                    .thumbnail(XImageConfig.SIZE_MULTIPLIER)
                    .into(imageView.getImageView());
        } else {
            DrawableRequestBuilder<String> thumbnailRequest = Glide
                    .with(context)
                    .load(url);
            Glide.with(context)
                    .load(imageView.getUrl())
                    .thumbnail(thumbnailRequest)
                    .into(imageView.getImageView());
        }
    }

    @Override
    public void loadImageWithSize(Context context, XImageView imageView) {
        SizeOption size = imageView.getImageSize();
        int width = (size != null ? size.getWidth() : 0);
        int height = (size != null ? size.getHeight() : 0);
        if (width == 0 || height == 0) {
            Glide.with(context)
                    .load(imageView.getUrl())
                    .into(imageView.getImageView());
        } else {
            Glide.with(context)
                    .load(imageView.getUrl())
                    .override(width, height)
                    .into(imageView.getImageView());
        }
    }

    @Override
    public void loadImageWithAnim(Context context, XImageView imageView) {
        Glide.with(context)
                .load(imageView.getUrl())
                .animate(imageView.getAnimateOption().getAnimate())
                .into(imageView.getImageView());
    }

    @Override
    /**
     * all:缓存源资源和转换后的资源
     * none:不作任何磁盘缓存
     * source:缓存源资源
     * result：缓存转换后的资源
     */
    public void loadImageWithCache(Context context, XImageView imageView) {
        Glide.with(context)
                .load(imageView.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL) // TODO: 2016/3/10  抽离
                .skipMemoryCache(true) // 跳过内存缓存
                .into(imageView.getImageView());
    }

    @Override
    public void loadCircleImage(Context context, XImageView imageView) {
        Glide.with(context)
                .load(imageView.getUrl())
                .dontAnimate()
                .transform(new CircleTransform(context))
                .into(imageView.getImageView());
    }


    @Override
    public void loadBlurImage(Context context, XImageView imageView) {
        Glide.with(context)
                .load(imageView.getUrl())
                .dontAnimate()
                .transform(new BlurTransformation(context))
                .into(imageView.getImageView());
    }


    @Override
    public void loadGifImage(Context context, XImageView imageView) {
        Glide.with(context)
                .load(imageView.getUrl())
                .asGif()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView.getImageView());
    }

    @Override
    public void loadImageListener(Context context, XImageView imageView, XImageLoadListener listener) {
        Glide.with(context)
                .load(imageView.getUrl())
                .error(imageView.getHolderOption().getErrorHolder())
                .placeholder(imageView.getHolderOption().getPlaceHolder())
                .listener(new GlideListener<>(listener, imageView.getUrl()))
                .into(imageView.getImageView());
    }

    /**
     * 设置动态转换
     * centerCrop():均衡缩放头像,保持图像原始比例，图像位于视图中心
     * fitCenter():缩放头像，在视图中使图像居中
     *
     * @param mContext   the m context
     * @param path       the path
     * @param mImageView the m image view
     */
    public void loadImageViewCrop(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).centerCrop().into(mImageView);
    }

    /**
     * Load image view fit.
     *
     * @param mContext   the m context
     * @param path       the path
     * @param mImageView the m image view
     */
    public void loadImageViewFit(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).fitCenter().into(mImageView);
    }

    @Override
    protected void cancelAllTasks(Context context) {
        Glide.with(context).pauseRequests();
    }

    @Override
    protected void resumeAllTasks(Context context) {
        Glide.with(context).resumeRequests();
    }

    @Override
    protected void clearAllCache(Context context) {
        clearDiskCache(context);
        clearMemory(context);
    }

    /**
     * 图片加载监听类
     *
     * @param <T> 图片链接 的类型
     * @param <K> 图片资源返回类型
     * @param <Z> 返回的图片url
     */
    private class GlideListener<T, K, Z> implements RequestListener<T, K> {

        private XImageLoadListener<Z, K> imageLoadListener = null;
        private Z url;
        private ImageView imageView = null;

        private GlideListener(XImageLoadListener<Z, K> imageLoadListener, Z url, ImageView view) {
            this.imageLoadListener = imageLoadListener;
            this.url = url;
            this.imageView = view;
        }

        private GlideListener(XImageLoadListener<Z, K> imageLoadListener, Z url) {
            this.imageLoadListener = imageLoadListener;
            this.url = url;
        }

        private GlideListener(Z url) {
            this.url = url;
        }

        @Override
        public boolean onResourceReady(K resource, T model, Target<K> target, boolean isFromMemoryCache, boolean isFirstResource) {
            if (null != imageLoadListener) {
                if (imageView != null) {
                    imageLoadListener.onLoadingComplete(url, imageView, resource);
                } else {
                    imageLoadListener.onLoadingComplete(url, null, resource);
                }
            }

            return false;
        }

        @Override
        public boolean onException(Exception e, T model, Target<K> target, boolean isFirstResource) {

            if (imageLoadListener != null) {
                imageLoadListener.onLoadingError(url, e);
            }
            return false;
        }
    }

    /**
     * 清除磁盘缓存
     */
    private void clearDiskCache(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();
            }
        }).start();
    }

    /**
     * 释放内存
     */
    private void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }
}
