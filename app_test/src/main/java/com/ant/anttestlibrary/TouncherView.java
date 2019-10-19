package com.ant.anttestlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ant.app_utils.LogUtil;

import static com.ant.anttestlibrary.activity.LauncherActivity.getEventName;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/17.
 * describe：
 */
public class TouncherView extends TextView {

    public TouncherView(Context context) {
        super(context);
    }

    public TouncherView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouncherView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.e("============onTouchEvent===============view" + getEventName(event));

        float x = event.getX();
        float y = event.getY();
        float rawX = event.getRawX();
        float rawY = event.getRawY();


//        long downTime = event.getDownTime();
//        event.time
//        int actionMasked = event.getActionMasked();
//
//        int actionIndex = event.getActionIndex();
//        event.getpoin
        return super.onTouchEvent(event);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        LogUtil.e("============dispatchTouchEvent===============view" + getEventName(event));
        return super.dispatchTouchEvent(event);

    }
}
