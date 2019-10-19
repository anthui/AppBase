package com.ant.anttestlibrary.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.core.content.ContextCompat;

import com.ant.anttestlibrary.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2019/10/16
 * describe：
 */
public class DashboardView extends View {

    private int mRadius; // 画布边缘半径（去除padding后的半径）
    private int mStartAngle = 120; // 起始角度，从120度开始回执
    private float mSweepAngle = 270; // 绘制角度 回执270 底部留下 90度

    private int cnt = 30;//刻度个数  默认60+1个，1代表为0
    private int mMin = 0; // 最小值
    private int mMax = 60; // 最大值
    private String mHeaderText = "BETA"; // 表头
    private int mCreditValue = 0; // 当前数值，不能大于max
    private int mSparkleWidth; // 亮点宽度
    private int mProgressWidth; // 进度圆弧宽度
    private float mLength1; // 刻度顶部相对边缘的长度
    private float mLength2; // 信用值指示器顶部相对边缘的长度

    private int mPadding;
    private float mCenterX, mCenterY; // 圆心坐标
    private Paint mPaint;
    private RectF mRectFProgressArc;
    private Rect mRectText;
    private Path mPath;
    private int[] mBgColors;
    private int textColor;

    public DashboardView(Context context) {
        this(context, null);
    }

    public DashboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    float buWidth = 0;

    private void init() {
        textColor = Color.parseColor("#333333");
        mSparkleWidth = dp2px(10);
        mProgressWidth = 4;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        mPaint.setColor(Color.WHITE);
        //初始化时 获取文字步数文字
        mPaint.setTextSize(12);
        buWidth = mPaint.measureText("步");

        mRectFProgressArc = new RectF();
        mRectText = new Rect();
        mPath = new Path();

        mBgColors = new int[]{ContextCompat.getColor(getContext(), R.color.color_red),
                ContextCompat.getColor(getContext(), R.color.color_orange),
                ContextCompat.getColor(getContext(), R.color.color_yellow),
                ContextCompat.getColor(getContext(), R.color.color_green),
                ContextCompat.getColor(getContext(), R.color.color_blue)};
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mPadding = Math.max(
                Math.max(getPaddingLeft(), getPaddingTop()),
                Math.max(getPaddingRight(), getPaddingBottom())
        );
        setPadding(mPadding, mPadding, mPadding, mPadding);

        mLength1 = mPadding + mSparkleWidth / 2f + dp2px(68);
        mLength2 = mLength1 + mProgressWidth + dp2px(4);

        int width = resolveSize(dp2px(220), widthMeasureSpec);
        mRadius = (width - mPadding * 2) / 2;

        setMeasuredDimension(width, width - dp2px(50));

        mCenterX = mCenterY = getMeasuredWidth() / 2f;
        mRectFProgressArc.set(
                mPadding + mSparkleWidth / 2f,
                mPadding + mSparkleWidth / 2f,
                getMeasuredWidth() - mPadding - mSparkleWidth / 2f,
                getMeasuredWidth() - mPadding - mSparkleWidth / 2f
        );

        mPaint.setTextSize(sp2px(10));
        mPaint.getTextBounds("0", 0, "0".length(), mRectText);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(calculateBGColorWithValue(mCreditValue));
        mPaint.setColor(Color.WHITE);

        /**
         * 画进度圆弧背景
         */
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mProgressWidth);

        //degree 每一个刻度的 所需要的 角度
        float degree = mSweepAngle / cnt / 2;
        float current = calculateRelativeAngleWithValue(mCreditValue);
        float b = mSweepAngle / 2f;
        mPaint.setShader(null);
        mPaint.setAlpha(current >= b ? 200 : 80);
        canvas.save();
        //绘制中间刻度
        canvas.drawLine(mCenterX, mPadding + mLength1, mCenterX, mPadding + mLength1 - 70, mPaint);
        mPaint.setColor(Color.RED);

//        float angle = mStartAngle;
        // 逆时针旋转，这边会涉及到 0的问题
        setKeDu(canvas, current, degree, true);

        // 顺时针旋转
        setKeDu(canvas, current, degree, false);

        /**
         * 画实时度数值
         */
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(sp2px(36));
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(textColor);
        String value = String.valueOf(mCreditValue);
        float width = mPaint.measureText(value);

        canvas.drawText(value, mCenterX - buWidth, mCenterY, mPaint);

        mPaint.setTextSize(sp2px(12));
        canvas.drawText("步", mCenterX + width / 2 + buWidth, mCenterY, mPaint);

        /**
         * 画信用描述
         */
        mPaint.setTextSize(sp2px(14));
        canvas.drawText(calculateCreditDescription(), mCenterX, mCenterY - dp2px(40), mPaint);

        /**
         * 画表头
         */
        mPaint.setAlpha(160);
        mPaint.setTextSize(sp2px(10));
        canvas.drawText(mHeaderText, mCenterX, mCenterY - dp2px(65), mPaint);

        /**
         * 画评估时间
         */
        mPaint.setAlpha(160);
        mPaint.setTextSize(sp2px(9));
        canvas.drawText(getFormatTimeStr(), mCenterX, mCenterY + dp2px(25), mPaint);
    }


    /**
     * 设置刻度表盘
     */
    private void setKeDu(Canvas canvas, float current, float degree, boolean isLeft) {
        float b = mSweepAngle / 2f;
        for (int i = 0; i < cnt; i++) {
            if (isLeft) {
                canvas.rotate(-degree, mCenterX, mCenterY);
                b -= degree;
            } else {
                canvas.rotate(degree, mCenterX, mCenterY);
                b += degree;
            }
            mPaint.setAlpha(current >= b ? 200 : 80);
            if (i == 14 || i == cnt - 1) {
                canvas.drawLine(mCenterX, mPadding + mLength1, mCenterX, mPadding + mLength1 - 80, mPaint);
            } else {
                canvas.drawLine(mCenterX, mPadding + mLength1, mCenterX, mPadding + mLength1 - 60, mPaint);
            }

        }

        canvas.restore();
        canvas.save();
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }

    /**
     * 相对起始角度计算信用分所对应的角度大小
     */
    private float calculateRelativeAngleWithValue(int value) {
        return mSweepAngle * value * 1f / mMax;
    }

    /**
     * 信用分对应信用描述
     */
    private String calculateCreditDescription() {
        if (mCreditValue > 700) {
            return "信用极好";
        } else if (mCreditValue > 650) {
            return "信用优秀";
        } else if (mCreditValue > 600) {
            return "信用良好";
        } else if (mCreditValue > 550) {
            return "信用中等";
        }
        return "信用较差";
    }

    /**
     * 信用分对应信用描述
     */
    private int calculateBGColorWithValue(int value) {
        if (value > 700) {
            return mBgColors[4];
        } else if (value > 650) {
            return mBgColors[3];
        } else if (value > 600) {
            return mBgColors[2];
        } else if (value > 550) {
            return mBgColors[1];
        }
        return mBgColors[0];
    }

    private SimpleDateFormat mDateFormat;

    private String getFormatTimeStr() {
        if (mDateFormat == null) {
            mDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        }
        return String.format("评估时间:%s", mDateFormat.format(new Date()));
    }

    public int getCreditValue() {
        return mCreditValue;
    }

    /**
     * 设置信用值
     *
     * @param creditValue 信用值
     */
    public void setCreditValue(int creditValue) {
        if (mCreditValue == creditValue || creditValue < mMin || creditValue > mMax) {
            return;
        }

        mCreditValue = creditValue;
        postInvalidate();
    }

    //    跳到最大值，然后回调
    public void startAnimation(int creditValue) {
//        if (mCreditValue == creditValue || creditValue < mMin || creditValue > mMax) {
//            return;
//        }
        //跳到最大值，然后回调
        ValueAnimator valueAnimator = ValueAnimator.ofInt(mMin, mMax, creditValue);
        valueAnimator.setDuration(2000).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setCreditValue((Integer) animation.getAnimatedValue());
            }
        });
        valueAnimator.setInterpolator(new LinearInterpolator());

        valueAnimator.start();


    }

    /**
     * 设置最大值
     */
    public void setmMax(int mMax) {
        this.mMax = mMax;
    }
}
