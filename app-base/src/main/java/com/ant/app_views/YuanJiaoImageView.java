package com.ant.app_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.ant.app_base.R;


/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2018/2/9
 * describe：
 */
public class YuanJiaoImageView extends androidx.appcompat.widget.AppCompatImageView {
    /**
     * 圆形模式
     */
    private static final int MODE_TOP = 1;

    /**
     * 圆角模式
     */
    private static final int MODE_BOTTOM = 2;
    //圆角弧度
    // private float[] rids = {30.0f, 30.0f, 30.0f, 30.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    private int currMode = 1;
    /**
     * 圆角半径
     */
    private int currRound = dp2px(10);


    public YuanJiaoImageView(Context context) {
        super(context);

    }

    public YuanJiaoImageView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }

    public YuanJiaoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttrs(context, attrs, defStyleAttr);
    }

    private void obtainStyledAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.YuanJiaoImageView, defStyleAttr, 0);
        currMode = a.hasValue(R.styleable.YuanJiaoImageView_round_type) ? a.getInt(R.styleable.YuanJiaoImageView_round_type, MODE_TOP) : MODE_TOP;
        currRound = a.hasValue(R.styleable.YuanJiaoImageView_round_radius) ? a.getDimensionPixelSize(R.styleable.YuanJiaoImageView_round_radius, currRound) : currRound;
        a.recycle();
    }

    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = this.getWidth();
        int h = this.getHeight();

        if (currMode == MODE_TOP) {
            float[] rids = {currRound, currRound, currRound, currRound, 0.0f, 0.0f, 0.0f, 0.0f};
            //绘制圆角imageview
            path.addRoundRect(new RectF(0, 0, w, h), rids, Path.Direction.CW);
            canvas.clipPath(path);
            super.onDraw(canvas);
       //     LogUtil.e("onDraw111111111111111111111111=========" + currMode);
        } else {
            float[] rids = {0.0f, 0.0f, 0.0f, 0.0f, currRound, currRound, currRound, currRound,};
            //绘制圆角imageview
            path.addRoundRect(new RectF(0, 0, w, h), rids, Path.Direction.CW);
            canvas.clipPath(path);
        //    LogUtil.e("onDraw22222222222222222===========" + currMode);
            super.onDraw(canvas);
        }

    }

    private int dp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }
}