package com.ant.app_home.activity;

import android.app.ActivityManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.ant.app_base.BaseActivity;
import com.ant.app_base.version.APPUtil;
import com.ant.app_home.R;
import com.mg.APPUtils;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/15.
 * describe：
 */
public class LauncherActivity extends BaseActivity {
    @Override
    public int getMainContentViewId() {
        return R.layout.activity_launcher;
    }

    @Override
    public void initData() {
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);

    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

    }
}
