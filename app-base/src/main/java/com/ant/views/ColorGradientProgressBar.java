package com.ant.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;


/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2018/2/2
 * describe： 自定义颜色渐变进度条, 原理:画三层圆角矩形(1.描边层;2.背景层;3.进度层)
 */

public class ColorGradientProgressBar extends View {

    /**
     * 渐变颜色组
     */
    private int[] GRADIENT_COLORS = {Color.parseColor("#ffe628"), Color.parseColor("#ffc600")};
    /**
     * 最大进度
     */
    private float max = 100;
    /**
     * 当前进度
     */
    private float progress;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 外描边的宽度
     */
    private float BORDER_STROCK;
    /**
     * 进度条进度矩形与控件边界的距离,≥BORDER_STROCK
     */
    private float PROGRESS_STROCK;
    //进度条的宽高
    private int mWidth, mHeight;
    /**
     * 画进度条的矩形
     */
    private RectF mRectF;

    public ColorGradientProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public ColorGradientProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorGradientProgressBar(Context context) {
        this(context, null);
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mRectF = new RectF();
        //  BORDER_STROCK = getResources().getDimension(60);
        BORDER_STROCK = 3;
        PROGRESS_STROCK = 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int round = mHeight / 2;//弧度为高度的一半
        mRectF.set(0, 0, mWidth, mHeight);//第一层矩形(描边层)
        mPaint.setColor(Color.parseColor("#ffffff"));//第一层矩形颜色(进度条描边的颜色)
        canvas.drawRoundRect(mRectF, round, round, mPaint);//画第一层圆角矩形
        mPaint.setColor(Color.parseColor("#ffffff"));//第二层矩形颜色(背景层颜色)
        mRectF.set(BORDER_STROCK, BORDER_STROCK, mWidth - BORDER_STROCK, mHeight - BORDER_STROCK);//第二层矩形(背景层)
        canvas.drawRoundRect(mRectF, round, round, mPaint);//画背景层圆角矩形(盖在描边层之上)
        if (progress == 0)//进度为 0不画进度
            return;
        float section = progress / max;
        //第三层矩形(进度层)
        mRectF.set(PROGRESS_STROCK, PROGRESS_STROCK, (mWidth - PROGRESS_STROCK) * section, mHeight - PROGRESS_STROCK);
        //创建线性颜色渐变器
        LinearGradient shader = new LinearGradient(PROGRESS_STROCK, PROGRESS_STROCK,
                (mWidth - PROGRESS_STROCK) * section, mHeight - PROGRESS_STROCK, GRADIENT_COLORS, null, Shader.TileMode.MIRROR);
        mPaint.setShader(shader);//第三层矩形颜色(进度渐变色)
        canvas.drawRoundRect(mRectF, round, round, mPaint);//画第三层(进度层)圆角矩形(盖在背景层之上)
        mPaint.setShader(null);//清除之前传递的shader
    }

    /***
     * 设置最大进度
     *
     * @param maxCount
     */
    public void setMax(float maxCount) {
        this.max = maxCount;
    }

    /***
     * 设置当前进度
     *
     * @param currentCount
     */
    public void setProgress(float currentCount) {
        this.progress = currentCount > max ? max : currentCount;
        invalidate();
    }

    public float getMax() {
        return max;
    }


    /**
     * 测量得到进度条的宽高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.EXACTLY || widthSpecMode == MeasureSpec.AT_MOST) {
            mWidth = widthSpecSize;
        } else {
            mWidth = 0;
        }
        if (heightSpecMode == MeasureSpec.AT_MOST || heightSpecMode == MeasureSpec.UNSPECIFIED) {
            // mHeight = (int) getResources().getDimension(R.dimen.x40);
            mHeight = 40;
        } else {
            mHeight = heightSpecSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }
}
