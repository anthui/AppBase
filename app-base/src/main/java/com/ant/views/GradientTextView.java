package com.ant.views;

/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2018/3/20
 * describe：
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;

import com.ant.base.R;


/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2018/3/20
 * describe：
 */


public class GradientTextView extends android.support.v7.widget.AppCompatTextView {

    private LinearGradient mLinearGradient;
    private Paint mPaint;
    private int mViewWidth = 0;//文字的宽度
    private int mViewHeight = 0;//文字的高度
    private Rect mTextBound = new Rect();
    private int[] mColorList;//存放颜色的数组
    private boolean isVertrial = false;//默认是横向

    Context context;

    public GradientTextView(Context context) {
        this(context, null);
    }

    public GradientTextView(Context context,
                            AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //设置默认的颜色
        //   mColorList = new int[]{getResources().getColor(R.color.font_oriange), getResources().getColor(R.color.color_red)};
    }


    @Override
    protected void onDraw(Canvas canvas) {

        mPaint = getPaint();
        String mTipText = getText().toString();
        mPaint.getTextBounds(mTipText, 0, mTipText.length(), mTextBound);

        //前面4个参数分别表示渐变的开始x轴,开始y轴,结束的x轴,结束的y轴,mcolorList表示渐变的颜色数组


        mLinearGradient = new LinearGradient(0, 0, mTextBound.width(), 0, getResources().getColor(R.color.btn_left_default), getResources().getColor(R.color.btn_right_default), Shader.TileMode.MIRROR);


        mPaint.setShader(mLinearGradient);
        //画出文字
        // canvas.drawText(mTipText, 0, getMeasuredHeight() / 2 + mTextBound.height() / 2, mPaint);
        canvas.drawText(mTipText, 0, mTextBound.height(), mPaint);
    }

    /**
     * true表示纵向渐变,false变身横向渐变
     *
     * @param vertrial
     */
    public void setVertrial(boolean vertrial) {
        isVertrial = vertrial;
    }

    /**
     * 设置渐变的颜色
     *
     * @param mColorList
     */
    public void setmColorList(int[] mColorList) {
        if (mColorList != null && mColorList.length < 2) {
            throw new RuntimeException("mClorList'shake length must be > 2");
        } else {

            this.mColorList = mColorList;
        }
    }
}
//
//
//public class GradientTextView extends android.support.v7.widget.AppCompatTextView {
//
//    private LinearGradient mLinearGradient;
//    private Paint mPaint;
//    private int mViewWidth = 0;
//    private Rect mTextBound = new Rect();
//
//    public GradientTextView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        mViewWidth = getMeasuredWidth();
//        mPaint = getPaint();
//        String mTipText = getText().toString();
//        mPaint.getTextBounds(mTipText, 0, mTipText.length(), mTextBound);
//        mLinearGradient = new LinearGradient(0, 0, 0, getMeasuredHeight(),
//                new int[]{getResources().getColor(R.color.color_white), getResources().getColor(R.color.color_black)},
//                null, Shader.TileMode.CLAMP);
//        mPaint.setShader(mLinearGradient);
//        canvas.drawText(mTipText, getMeasuredWidth() / 2 - mTextBound.width() / 2, getMeasuredHeight() / 2 + mTextBound.height() / 2, mPaint);
//    }
//
//}
//
//public class GradientTextView extends android.support.v7.widget.AppCompatTextView {
//    public GradientTextView(Context context) {
//        super(context);
//    }
//
//    public GradientTextView(Context context,
//                            AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public GradientTextView(Context context,
//                            AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        getPaint().setShader(new LinearGradient(
//                0, 0, 0, getHeight(),
//                Color.BLACK, Color.RED,
//                Shader.TileMode.MIRROR));
//    }
//}