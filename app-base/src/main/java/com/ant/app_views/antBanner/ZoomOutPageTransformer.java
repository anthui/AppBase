package com.ant.app_views.antBanner;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2018/5/31
 * describe： viewPage滑动 效果-----------item之间切换的效果
 * 原理：将item(view)进行 缩放效果  这里只适配等比例缩放、缩放y两种方式，其他正常多余
 */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    //默认缩放的值 0-1之间
    private final float MIN_SCALE = 0.7f;
    //默认不进行缩放
    private float MIN_SCALE_Y = MIN_SCALE;
    private float MIN_ALPHA = 0.5f;


    //是否需要缩放 x方向 默认不需要
    boolean shouldScaleX = false;

    public ZoomOutPageTransformer() {

    }

    public ZoomOutPageTransformer(boolean shouldX, float MIN_SCALE_Y, float minAlpha) {

        if (MIN_SCALE_Y > 1 || MIN_SCALE_Y <= 0) {
            this.MIN_SCALE_Y = MIN_SCALE;
        } else {
            this.MIN_SCALE_Y = MIN_SCALE_Y;
        }

        if (minAlpha > 1 || minAlpha < 0) {
            this.MIN_ALPHA = 1;
        } else {
            this.MIN_ALPHA = minAlpha;

        }
    }

    /**
     * @param page     viewPage中的Item
     * @param position 该Item的基准点 position=0 表示在该位置，[0,1]表示下个位置 [-1,0]上一位位置的item
     */
    @Override
    public void transformPage(View page, float position) {

//        LogUtil.e("position===" + position);
        if (position < -1 || position > 1) {
            page.setAlpha(MIN_ALPHA);

            if (shouldScaleX) {
                page.setScaleX(MIN_SCALE_Y);
            }
            page.setScaleY(MIN_SCALE_Y);
        } else { // [-1,1]
            float scaleFactor = Math.max(MIN_SCALE_Y, 1 - Math.abs(position));

            if (position < 0) {
                float scaleX = 1 + (1 - MIN_SCALE_Y) * position;
                //Log.d("google_lenve_fb", "transformPage: scaleX:" + scaleX);
                if (shouldScaleX) {

                    page.setScaleX(scaleX);
                }
                page.setScaleY(scaleX);
            } else {
                float scaleX = 1 - (1 - MIN_SCALE_Y) * position;
                if (shouldScaleX) {
                    page.setScaleX(scaleX);
                }
                page.setScaleY(scaleX);
            }
            if (MIN_ALPHA != 1) {
                page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE_Y) / (1 - MIN_SCALE_Y) * (1 - MIN_ALPHA));
            }
        }
    }
}