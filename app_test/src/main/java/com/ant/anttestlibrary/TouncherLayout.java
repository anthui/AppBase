package com.ant.anttestlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.ant.app_utils.LogUtil;

import static com.ant.anttestlibrary.activity.LauncherActivity.getEventName;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/17.
 * describe：
 */
public class TouncherLayout extends RelativeLayout {

    public TouncherLayout(Context context) {
        super(context);
    }


    public int pageType = 0;

    public TouncherLayout(Context context, AttributeSet attrs) {
        super(context, attrs);


    }

    public TouncherLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {


        LogUtil.e("============dispatchTouchEvent============================" + pageType + getEventName(event));

        return super.dispatchTouchEvent(event);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.e("============onTouchEvent============================" + pageType + getEventName(event));


//        if (pageType == 2) {
//            return true;
//        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        LogUtil.e("============onInterceptTouchEvent============================" + pageType + getEventName(event));
        if (pageType == 2) {
            return true;
        }
        return super.onInterceptTouchEvent(event);

    }
}
