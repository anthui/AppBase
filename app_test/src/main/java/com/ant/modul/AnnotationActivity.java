package com.ant.modul;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.CheckResult;

import com.ant.anttestlibrary.R;
import com.ant.anttestlibrary.databinding.ActivityHom2BindingImpl;
import com.ant.app_base.BaseBindActivity;

import butterknife.BindView;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/4/13.
 * describe：注解
 */
public class AnnotationActivity extends BaseBindActivity<ActivityHom2BindingImpl> {
    @BindView(R.id.add_btn)
    TextView textView;

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_hom2;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {
        int size = getSize();
    }

    @CheckResult
    private int getSize() {


        return 1;
    }
}

