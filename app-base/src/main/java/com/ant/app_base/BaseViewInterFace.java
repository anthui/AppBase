package com.ant.app_base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/14.
 * describe： 将Activity、Fragment、Dialog的布局方法提取，目的为了他们之前可以快速替换
 * <p>
 * 目的限制 名称
 */
public interface BaseViewInterFace {


    /**
     * 获取布局资源id
     */
    public @LayoutRes
    int getMainContentViewId();

    /**
     * 数据初始化方法
     */
    public void initData();

    /**
     * 控件初始化方法
     */
    public void initComponents(Bundle savedInstanceState, View rootView);

    /**
     * 列表初始化
     */
    public void initRecyclerView();


//    ********************************************以下方法只是为了 限制名称一致*************************************

    /**
     * debug弹窗
     */
    public void showDebugToast(String msg);

    /**
     * 弹窗
     */
    public void showToast(String msg);

    /**
     * 弹窗
     */
    public void loge(String msg);

    /**
     * 弹窗
     */
    public void showNoOpenToast();

    /**
     * 弹窗
     */
    public void showCenterToast(String msg, boolean success);

}
