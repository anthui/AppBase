package com.ant.kotlin.activity;

import android.os.Bundle;
import android.view.View;

import com.ant.anttestlibrary.R;
import com.ant.app_base.baseDialog.BaseDialog;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/16.
 * describe：
 */
public class ComDialog extends BaseDialog {
    @Override
    public int getMainContentViewId() {
        return R.layout.dialog_info;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

    }
}
