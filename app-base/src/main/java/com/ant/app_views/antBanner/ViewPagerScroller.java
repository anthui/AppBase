package com.ant.app_views.antBanner;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2018/1/31
 * describe：重写viewpager滚动时间
 */
class ViewPagerScroller extends Scroller {

    private int mPagerChangeDuration = 800;

    ViewPagerScroller(Context context) {
        super(context);
    }

    public ViewPagerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public ViewPagerScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }


    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mPagerChangeDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mPagerChangeDuration);
    }

    void changScrollDuration(ViewPager viewPager, int duration) {
        mPagerChangeDuration = duration;

        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            mScroller.set(viewPager, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
