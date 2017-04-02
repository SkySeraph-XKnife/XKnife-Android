package com.skyseraph.xknife.lib.utils.android;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/**
 * Created by SkySeraph on 2015/3/13.
 */
public class AnimationUtils {

    // 动画执行标志
    private static boolean animFlag = false;

    /**
     * Anim alpha dismiss view.  渐变隐藏动画
     *
     * @param view    the view
     * @param time_ms the time ms
     */
    public static void animAlphaDismissView(final View view, int time_ms) {
        if (animFlag) return;
        AlphaAnimation animation = new AlphaAnimation(1f, 0f);
        animation.setDuration(time_ms);
        animation.setFillAfter(false);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animFlag = true;
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animFlag = false;
                view.setVisibility(View.GONE);
            }
        });
        view.startAnimation(animation);
    }

    /**
     * Anim alpha show view. 渐变显示动画
     *
     * @param view    the view
     * @param time_ms the time ms
     */
    public static void animAlphaShowView(final View view, int time_ms) {
        if (animFlag) return;
        AlphaAnimation animation = new AlphaAnimation(1f, 0f);
        animation.setDuration(time_ms);
        animation.setFillAfter(false);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animFlag = true;
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animFlag = false;
                view.setVisibility(View.VISIBLE);
            }
        });
        view.startAnimation(animation);
    }

    /**
     * Anim alpha scale show view.
     *
     * @param view    the view
     * @param time_ms the time ms
     */
    public static void animAlphaScaleShowView(final View view, int time_ms) {
        if (animFlag) return;
        AnimationSet set = new AnimationSet(true);
        AlphaAnimation alphaAnim = new AlphaAnimation(0.2f, 1.0f);
        set.addAnimation(alphaAnim);

        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(scaleAnim);
        set.setFillAfter(true);
        set.setDuration(time_ms);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animFlag = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animFlag = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        view.startAnimation(set);
    }

    /**
     * Anim rotate scale show view. 旋转放大动画
     *
     * @param iv the iv
     */
    public static void animRotateScaleShowView(ImageView iv) {
        AnimationSet set = new AnimationSet(true);
        // 旋转动画
        RotateAnimation ra = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(120);
        // 缩放动画
        ScaleAnimation sa = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(120);
        // 向集合中添加动画
        set.addAnimation(ra);
        set.addAnimation(sa);
        iv.startAnimation(set);
    }

    /**
     * Anim rotate scale dismiss view.  旋转缩小动画
     *
     * @param iv the iv
     */
    public static void animRotateScaleDismissView(final ImageView iv) {
        AnimationSet set = new AnimationSet(true);
        // 旋转动画
        RotateAnimation ra = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(100);
        // 缩放动画
        ScaleAnimation sa = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(100);
        // 向集合中添加动画
        set.addAnimation(ra);
        set.addAnimation(sa);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.setVisibility(View.INVISIBLE);
            }
        });
        iv.startAnimation(set);
    }

    /**
     * Anim rotate arrow up. 旋转箭头向上
     *
     * @param iv the iv
     */
    public static void animRotateArrowUp(final ImageView iv) {
        if (iv == null) return;
        RotateAnimation animation = new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(200);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
        iv.startAnimation(animation);
    }

    /**
     * Anim rotate arrow down. 旋转箭头向下
     *
     * @param iv the iv
     */
    public static void animRotateArrowDown(final ImageView iv) {
        if (iv == null) return;
        RotateAnimation animation = new RotateAnimation(180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(200);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
        iv.startAnimation(animation);
    }
}
