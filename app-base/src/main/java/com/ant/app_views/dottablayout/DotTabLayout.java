package com.ant.app_views.dottablayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import com.ant.app_base.R;
import com.ant.app_utils.SizeUtil;
import com.ant.app_views.dottablayout.indicators.AnimatedIndicatorInterface;
import com.ant.app_views.dottablayout.indicators.AnimatedIndicatorType;
import com.ant.app_views.dottablayout.indicators.DachshundIndicator;
import com.ant.app_views.dottablayout.indicators.LineFadeIndicator;
import com.ant.app_views.dottablayout.indicators.LineMoveIndicator;
import com.ant.app_views.dottablayout.indicators.PointFadeIndicator;
import com.ant.app_views.dottablayout.indicators.PointMoveIndicator;
import com.google.android.material.tabs.TabLayout;

/**
 * copyright：
 * author：
 * Version：1.0
 * creation date：2018/5/4
 * describe： 头部导航栏
 * upDate:2019/8/13
 * 头部导航栏 去掉重复参数
 */

public class DotTabLayout extends TabLayout implements ViewPager.OnPageChangeListener {


    //   指示器默认高度，系统默认高度也是6
    private static final int DEFAULT_HEIGHT_DP = 4;

    //指示器颜色
    private int indicatorColor;
    //指示器高度
    private int indicatorHeight;
    //当前位置
    private int currentPosition;

    private boolean centerAlign;

    //    重写父类的 viewPage
    private ViewPager currentViewPager = null;
    //指示器布局
    private LinearLayout tabStrip;

    private AnimatedIndicatorType animatedIndicatorType;

    private AnimatedIndicatorInterface animatedIndicator;


    int startXLeft, endXLeft, startXCenter, endXCenter, startXRight, endXRight;

    public DotTabLayout(Context context) {
        this(context, null);
    }

    public DotTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //两种方式 忽略原始的指示器
//        super.setSelectedTabIndicatorHeight(0);
        super.setSelectedTabIndicatorColor(Color.TRANSPARENT);
        tabStrip = (LinearLayout) super.getChildAt(0);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DotTabLayout);

        //位置
        this.centerAlign = a.getBoolean(R.styleable.DotTabLayout_ddCenterAlign, false);
        //指示器样式
        this.animatedIndicatorType = AnimatedIndicatorType.values()[a.getInt(R.styleable.DotTabLayout_ddAnimatedIndicator, 0)];

        a.recycle();


        TypedArray b = context.obtainStyledAttributes(attrs, R.styleable.TabLayout);
        //是指其高度
        this.indicatorHeight = b.getDimensionPixelSize(R.styleable.TabLayout_tabIndicatorHeight, SizeUtil.dipToPx(context, DEFAULT_HEIGHT_DP));
        //指示器颜色
        this.indicatorColor = b.getColor(R.styleable.TabLayout_tabIndicatorColor, Color.WHITE);
        b.recycle();


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (centerAlign) {
            View firstTab = ((ViewGroup) getChildAt(0)).getChildAt(0);
            View lastTab = ((ViewGroup) getChildAt(0)).getChildAt(((ViewGroup) getChildAt(0)).getChildCount() - 1);
            ViewCompat.setPaddingRelative(getChildAt(0), (getWidth() / 2) - (firstTab.getWidth() / 2), 0, (getWidth() / 2) - (lastTab.getWidth() / 2), 0);
        }

//        LogUtil.e("======================================");
        setupAnimatedIndicator();

    }

    private void setupAnimatedIndicator() {
        switch (animatedIndicatorType) {
            case DACHSHUND:
                setAnimatedIndicator(new DachshundIndicator(this));
                break;
            case POINT_MOVE:
                setAnimatedIndicator(new PointMoveIndicator(this));
                break;
            case LINE_MOVE:
                setAnimatedIndicator(new LineMoveIndicator(this));
                break;
            case POINT_FADE:
                setAnimatedIndicator(new PointFadeIndicator(this));
                break;
            case LINE_FADE:
                setAnimatedIndicator(new LineFadeIndicator(this));
                break;
        }
    }

    public void setAnimatedIndicator(AnimatedIndicatorInterface animatedIndicator) {
        this.animatedIndicator = animatedIndicator;

        animatedIndicator.setSelectedTabIndicatorColor(indicatorColor);
        animatedIndicator.setSelectedTabIndicatorHeight(indicatorHeight);


        invalidate();
    }

    /**
     * 设置指示器颜色
     */
    @Override
    public void setSelectedTabIndicatorColor(@ColorInt int color) {
        this.indicatorColor = color;
        if (animatedIndicator != null) {
            animatedIndicator.setSelectedTabIndicatorColor(color);

            invalidate();
        }
    }


    /**
     * 系统指示器 背景已经设置为透明，所以这里高度可以忽略
     */
    @Override
    public void setSelectedTabIndicatorHeight(int height) {
        this.indicatorHeight = height;
        if (animatedIndicator != null) {
            animatedIndicator.setSelectedTabIndicatorHeight(height);

            invalidate();
        }

    }

    public void setCenterAlign(boolean centerAlign) {
        this.centerAlign = centerAlign;

        requestLayout();
    }

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        this.setupWithViewPager(viewPager, true);
    }

    @Override
    public void setupWithViewPager(@Nullable final ViewPager viewPager, boolean autoRefresh) {
        super.setupWithViewPager(viewPager, autoRefresh);


        //TODO
        if (viewPager != null) {
            if (viewPager != this.currentViewPager) {
                //移除之前ViewPage的监听
                if (currentViewPager != null) {
                    currentViewPager.removeOnPageChangeListener(this);
                }

                currentViewPager = viewPager;
                //重新添加监听,这里移除应该多余操作
                viewPager.removeOnPageChangeListener(this);
                viewPager.addOnPageChangeListener(this);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (animatedIndicator != null)
            animatedIndicator.draw(canvas);

    }


    @Override
    public void onPageScrollStateChanged(final int state) {
    }

    @Override
    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

        if ((position > currentPosition) || (position + 1 < currentPosition)) {
            currentPosition = position;
        }

        if (position != currentPosition) {

            startXLeft = (int) getChildXLeft(currentPosition);
            startXCenter = (int) getChildXCenter(currentPosition);
            startXRight = (int) getChildXRight(currentPosition);

            endXLeft = (int) getChildXLeft(position);
            endXRight = (int) getChildXRight(position);
            endXCenter = (int) getChildXCenter(position);

            if (animatedIndicator != null) {
                animatedIndicator.setIntValues(startXLeft, endXLeft,
                        startXCenter, endXCenter,
                        startXRight, endXRight);
                animatedIndicator.setCurrentPlayTime((long) ((1 - positionOffset) * (int) animatedIndicator.getDuration()));
            }

        } else {

            startXLeft = (int) getChildXLeft(currentPosition);
            startXCenter = (int) getChildXCenter(currentPosition);
            startXRight = (int) getChildXRight(currentPosition);

            if (tabStrip.getChildAt(position + 1) != null) {

                endXLeft = (int) getChildXLeft(position + 1);
                endXCenter = (int) getChildXCenter(position + 1);
                endXRight = (int) getChildXRight(position + 1);

            } else {
                endXLeft = (int) getChildXLeft(position);
                endXCenter = (int) getChildXCenter(position);
                endXRight = (int) getChildXRight(position);
            }

            if (animatedIndicator != null) {
                animatedIndicator.setIntValues(startXLeft, endXLeft,
                        startXCenter, endXCenter,
                        startXRight, endXRight);
                animatedIndicator.setCurrentPlayTime((long) (positionOffset * (int) animatedIndicator.getDuration()));
            }

        }

        if (positionOffset == 0) {
            currentPosition = position;
        }

    }

    @Override
    public void onPageSelected(final int position) {
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public float getChildXLeft(int position) {
        if (tabStrip.getChildAt(position) != null)
            return (tabStrip.getChildAt(position).getX());
        else
            return 0;
    }

    public float getChildXCenter(int position) {
        if (tabStrip.getChildAt(position) != null)
            return (tabStrip.getChildAt(position).getX() + tabStrip.getChildAt(position).getWidth() / 2);
        else
            return 0;
    }

    public float getChildXRight(int position) {
        if (tabStrip.getChildAt(position) != null)
            return (tabStrip.getChildAt(position).getX() + tabStrip.getChildAt(position).getWidth());
        else
            return 0;
    }

    public AnimatedIndicatorInterface getAnimatedIndicator() {
        return animatedIndicator;
    }


}
