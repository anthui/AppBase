package com.ant.anttestlibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.ant.app_utils.SizeUtil;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/10/24.
 * describe：
 */
public class NewBmiView extends View {

    /**
     * 分段颜色
     */
    private static final int[] SECTION_COLORS = {Color.rgb(255, 204, 47), Color.GREEN,
            Color.RED};
    /**
     * 画笔
     */
    private Paint mPaint;
    private Paint textPaint;
    private Paint drawablePaint;
    private Paint drawableBMIPaint;
    private Paint bmiTextpaint;
    private int bmiwidth, mWidth, mHeight, widthSum;
    private double value;
    private double i;
    private double bmi;

    private float valueWidth;
    private String bmiText;

    // 定义计算颜色的参数
    private int x, y, z;


    public NewBmiView(Context context) {

        super(context);
        initviews(context);
    }

    public NewBmiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initviews(context);
    }

    Context context;

    public NewBmiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initviews(context);
    }

    private void initviews(Context context) {
        this.context = context;
    }


    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.EXACTLY
                || widthSpecMode == MeasureSpec.AT_MOST) {
            widthSum = widthSpecSize;
        } else {
            widthSum = 0;
        }
        if (heightSpecMode == MeasureSpec.AT_MOST
                || heightSpecMode == MeasureSpec.UNSPECIFIED) {
            mHeight = SizeUtil.dipToPx(context, 15);
        } else {
            mHeight = heightSpecSize;
        }
        setMeasuredDimension(widthSum, mHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画自定义的渐变条
        mPaint = new Paint();
        // 去除锯齿
        mPaint.setAntiAlias(true);
        // 自定义圆角的弧度
        int round = mHeight / 20;
        // 新建矩形
        RectF rectBg = new RectF(bmiwidth, mHeight - (mHeight * 1 / 2), mWidth
                + bmiwidth, mHeight - (mHeight * 2 / 5));
        // 设置渐变色
        // CLAMP重复最后一个颜色至最后
        // MIRROR重复着色的图像水平或垂直方向已镜像方式填充会有翻转效果
        // REPEAT重复着色的图像水平或垂直方向
        LinearGradient shader = new LinearGradient(bmiwidth, mHeight
                - (mHeight * 1 / 2), mWidth + bmiwidth, mHeight
                - (mHeight * 2 / 5), SECTION_COLORS, null,
                Shader.TileMode.MIRROR);
        mPaint.setShader(shader);
        // rect：RectF对象。x方向上的圆角半径。ry：y方向上的圆角半径。paint：绘制时所使用的画笔。
        canvas.drawRoundRect(rectBg, round, round, mPaint);

        // 画下面的小箭头
//        drawablePaint = new Paint();
//        drawablePaint.setAntiAlias(true);
//        Bitmap arrowBitmap = BitmapFactory.decodeResource(getResources(),
//                R.mipmap.bf_load2);
//        canvas.drawBitmap(arrowBitmap, mWidth * 2 / 17 + bmiwidth, mHeight
//                - (mHeight * 2 / 5) + 5, drawablePaint);
//        canvas.drawBitmap(arrowBitmap, mWidth * 7 / 17 + bmiwidth, mHeight
//                - (mHeight * 2 / 5) + 5, drawablePaint);
//        canvas.drawBitmap(arrowBitmap, mWidth * 12 / 17 + bmiwidth, mHeight
//                - (mHeight * 2 / 5) + 5, drawablePaint);
//
//        // 画下方的文字
//        String text = "偏瘦";
//        Rect textBounds = new Rect();
//        textPaint = new Paint();
//        textPaint.setAntiAlias(true);
//        textPaint.setColor(Color.GRAY);
//        textPaint.setTextSize(30);
//        // 获取字体的高宽
//        textPaint.getTextBounds(text, 0, text.length(), textBounds);
//        float textWidth = textBounds.width();
//        float textHeight = textBounds.height();
//
//        canvas.drawText("偏瘦", (mWidth * 2 / 17) / 2 - textWidth / 2 + bmiwidth,
//                mHeight * 7 / 10 + textHeight / 2 + 10, textPaint);
//        canvas.drawText("标准", (mWidth * 2 / 17) + (mWidth * 5 / 17) / 2
//                - textWidth / 2 + bmiwidth, mHeight * 7 / 10 + textHeight / 2
//                + 10, textPaint);
//        canvas.drawText("超重", (mWidth * 7 / 17) + (mWidth * 5 / 17) / 2
//                - textWidth / 2 + bmiwidth, mHeight * 7 / 10 + textHeight / 2
//                + 10, textPaint);
//        canvas.drawText("肥胖", (mWidth * 12 / 17) + (mWidth * 5 / 17) / 2
//                - textWidth / 2 + bmiwidth, mHeight * 7 / 10 + textHeight / 2
//                + 10, textPaint);
//
//        // 画上方偏移的小方块
//        drawableBMIPaint = new Paint();
//        drawableBMIPaint.setAntiAlias(true);
//        // 设置颜色
//
//        // 通过BMI来RGB计算颜色
//        i = (value - 18) * (34 / 17);
//        if (i >= 0 && i <= 17) {
//            x = (int) ((17 - i) * (255 / 17));
//            y = 204;
//            z = 47;
//
//        }
//        if (i > 17 && i <= 34) {
//            x = (int) ((i - 17) * (255 / 17));
//            y = (int) ((34 - i) * (255 / 17));
//            z = 0;
//        }
//
//        drawableBMIPaint.setColor(Color.rgb(x, y, z));
//        System.out.println("颜色值为" + String.valueOf(x) + String.valueOf(y)
//                + String.valueOf(z));
//
////        canvas.drawRect(getvalue(), mHeight / 6, c() + bmiBitmap.getWidth(),
////                bmiBitmap.getHeight() + mHeight / 6, drawableBMIPaint);
////        System.out.println("偏移量为" + getvalue());
////        canvas.drawBitmap(bmiBitmap, getvalue(), mHeight / 6, drawablePaint);
//
//        // 画上方偏移的小方块里面的文字
//        String bmitext = "40.0";
//        Rect bmitextBounds = new Rect();
//        bmiTextpaint = new Paint();
//        bmiTextpaint.setAntiAlias(true);
//        bmiTextpaint.setTextSize(35);
//        bmiTextpaint.setColor(Color.WHITE);
//        // 获取字体的高宽
//        bmiTextpaint.getTextBounds(bmitext, 0, bmitext.length(), bmitextBounds);
//        canvas.drawText(bmiText, getvalue() - (bmitextBounds.width() / 2)
//                        + bmiwidth, mHeight / 3 + (bmitextBounds.height() / 3),
//                bmiTextpaint);
    }

    public void setBmi(double bmi) {
        this.value = bmi;
        // 设置颜色
        if (value < 18) {
            this.value = 18;
        } else if (value > 35) {
            this.value = 35;
        }
        invalidate();
    }

    public void setBmiText(String bmiText) {
        this.bmiText = bmiText;
    }
}