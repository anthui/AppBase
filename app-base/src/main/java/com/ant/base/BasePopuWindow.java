package com.ant.base;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.ant.utils.LogUtil;

import java.util.List;


/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2017/12/4
 * describe：所有弹窗的父类
 */

public abstract class BasePopuWindow extends PopupWindow {


    private View view = null;
    public Context mContext;

    //  private TuiGuangDetailAdapter tuiGuangDetailAdapter;//详细推广方案
    private List<String> planData;//详细方案数据

    public BasePopuWindow(Context context) {

        mContext = context;
        //popuwindow 基础初始化

        if (getMainContentViewId() == 0) {
            LogUtil.e("BasePopuWindow:必须设置布局id");
            return;
        }
        view = LayoutInflater.from(context).inflate(getMainContentViewId(), null);

        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);

        //添加动画效果
        // setAnimationStyle(R.style.popwindow_anim_style);
        //设置背景透明
        setTouchable(true);
        setOutsideTouchable(true);
        //    setBackgroundDrawable(new ColorDrawable(mContext.getResources().getColor(R.color.dialog_paren_color)));

        setBackgroundDrawable(new ColorDrawable(0));
        //   update();
        setContentView(view);
        //初始化控件
        initViews(view);
    }


    //控件初始化
    public abstract void initViews(View view);

    public abstract int getMainContentViewId();


    public void showCenter() {
        showAtLocation(view, Gravity.CENTER
                | Gravity.CENTER, 0, 0);
    }


}
