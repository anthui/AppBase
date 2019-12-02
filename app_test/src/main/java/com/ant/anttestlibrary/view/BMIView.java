package com.ant.anttestlibrary.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.ant.anttestlibrary.R;
import com.ant.app_utils.SizeUtil;


/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/10/24.
 * describe：
 */
public class BMIView extends View {


    Paint mPaint;

    int width = 0;

    int height = 8;

    int radiao = 0;
    int length = 0;


    int textNumColor;//文字颜色

    int margerTop = 178;//具体顶部位置
    int textMargerTop = 10;//文字与进度条的间距
    int textNameMargerTop = 30;//底部文字与进度条的间距
    int textSize = 12; //文字大小


    Context mContext;

    public BMIView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        height = SizeUtil.dipToPx(context, height);
        radiao = height / 2;


        mPaint = new Paint();
        margerTop = SizeUtil.dipToPx(context, margerTop);
        textMargerTop = margerTop - SizeUtil.dipToPx(context, textMargerTop);
        //头部距离+高度+间距
        textNameMargerTop = margerTop + SizeUtil.dipToPx(context, textNameMargerTop) + height;

        textNumColor = Color.parseColor("#333333");

        textSize = SizeUtil.spTopx(context, textSize);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_point1);
    }


    Bitmap bitmap;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        length = width / 4;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.parseColor("#FF30C0F4"));

        //绘制左边圆角
        canvas.drawArc(new RectF(0, margerTop, height, height + margerTop), 90.0F, 180.0F, true, mPaint);
        //绘制矩形
        canvas.drawRect(new Rect(radiao, margerTop, width - radiao, height + margerTop), mPaint);


        mPaint.setColor(Color.parseColor("#FF13D375"));

        //绘制第二部分
        canvas.drawRect(new Rect(width - 3 * length, margerTop, width - radiao, height + margerTop), mPaint);
        mPaint.setColor(Color.parseColor("#FFFFC041"));

        //绘制第三部分
        canvas.drawRect(new Rect(width - 2 * length, margerTop, width - radiao, height + margerTop), mPaint);
        mPaint.setColor(Color.parseColor("#FFE85E58"));


        //绘制第四部分
        canvas.drawRect(new Rect(width - length, margerTop, width - radiao, height + margerTop), mPaint);

        //绘制右边圆角
        canvas.drawArc(new RectF(width - height, margerTop, width, height + margerTop), -90.0F, 180.0F, true, mPaint);


        //绘制数字文本
        mPaint.setColor(textNumColor);
        mPaint.setTextSize(textSize);
        float v = mPaint.measureText("13") / 2;
        canvas.drawText("18.5", width - 3 * length - v, textMargerTop, mPaint);
        canvas.drawText("24", width - 2 * length - v, textMargerTop, mPaint);
        canvas.drawText("28", width - 1 * length - v, textMargerTop, mPaint);

        //绘制底部文字文本

        mPaint.setTextSize(SizeUtil.spTopx(mContext, 14));
        float f = mPaint.measureText("偏瘦");

        float v1 = (length - f) / 2;
        canvas.drawText("偏瘦", v1, textNameMargerTop, mPaint);
        canvas.drawText("理想", v1 + length, textNameMargerTop, mPaint);
        canvas.drawText("偏胖", v1 + 2 * length, textNameMargerTop, mPaint);
        canvas.drawText("肥胖", v1 + 3 * length, textNameMargerTop, mPaint);


        //绘制指示器
        getXPosition(canvas, mPaint, height, length);


        //绘制头部子

        int textSize = SizeUtil.spTopx(mContext, 48);
        mPaint.setTextSize(textSize);

        float v2 = mPaint.measureText(value + "");
        int x = (int) (width / 2 - v2 / 2);
        int y = textSize + SizeUtil.dipToPx(mContext, 32);
        canvas.drawText(value + "", x, y, mPaint);

        //绘制中间  偏瘦 等文案

        int textSize1 = SizeUtil.spTopx(mContext, 20);

        mPaint.setTextSize(textSize1);
        mPaint.setColor(Color.parseColor("#FF30C0F4"));
        float w = (width - mPaint.measureText("偏瘦")) / 2;
        canvas.drawText(getCenterString(value), w, y + textSize1 + SizeUtil.dipToPx(mContext, 20), mPaint);

    }

    private void getXPosition(Canvas canvas, Paint mPaint, int height, int length) {

        int bitMapWith = SizeUtil.dipToPx(mContext, 10);
        int bitMapHeight = SizeUtil.dipToPx(mContext, 12);
        int marginTop = height - SizeUtil.dipToPx(mContext, 3);


        int positionX = 0;
        if (value < 18.5) {
            positionX = (length - bitMapWith) / 2;

        } else if (value < 24) {
            //15.8---24

            float i = 24 - 18.5f;
            float i1 = length / i;
            float v = value - 24;
            float v1 = v * i1;

            positionX = (int) (length * 2 + (v1 - bitMapWith) / 2);


        } else if (value < 28) {
//            24-28


            int i = 28 - 24;
            int i1 = length / i;
            float v = value - 24;
            float v1 = v * i1;

            positionX = (int) (length * 2 + (v1 - bitMapWith) / 2);


        } else {
            positionX = length * 3 + (length - bitMapWith) / 2;


        }


        Rect rect = new Rect(positionX, margerTop - bitMapHeight + marginTop, positionX + bitMapWith, margerTop + marginTop);
        canvas.drawBitmap(bitmap, null, rect, mPaint);

    }


    /**
     * 获取指示器的位置
     */
//    public float getXPosition(int length, float value, Paint mPaint) {
//
//        if (value < 18.5) {
//            return "偏瘦";
//        } else if (value < 24) {
//            return "理想";
//        } else if (value < 28) {
//            return "偏胖";
//        } else {
//            return "肥胖";
//
//        }
//
//    }

    public String getCenterString(float value) {

        if (value < 18.5) {
            return "偏瘦";
        } else if (value < 24) {
            return "理想";
        } else if (value < 28) {
            return "偏胖";
        } else {
            return "肥胖";

        }


    }

    private float value = 0;

    /**
     * 设置数值
     */
    public void setValue(float num) {
        this.value = num;
        invalidate();

    }
}
