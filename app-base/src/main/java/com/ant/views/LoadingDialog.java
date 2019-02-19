//package com.ant.views;
//
//import android.os.Bundle;
//
//import com.ant.base.nicedialog.BaseNiceDialog;
//import com.ant.base.nicedialog.DialogViewHolder;
//import com.scwang.smartrefresh.layout.R;
//
///**
// * copyright：haoxin
// * author：anthui
// * Version：1.0
// * creation date：2018/2/7
// * describe：页面加载布局
// */
//public class LoadingDialog extends BaseNiceDialog {
//
//
//    public static LoadingDialog getInstance() {
//        return new LoadingDialog();
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setOutCancel(false);
//        setWidth(100);
//        setHeight(100);
//        setDimAmount(0);
//    }
//
//    @Override
//    public int intLayoutId() {
//        return R.layout.loading_layout;
//    }
//
//
//    @Override
//    public void convertView(DialogViewHolder holder, final BaseNiceDialog dialog) {
//
//    }
//}