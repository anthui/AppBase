package com.ant.anttestlibrary.activity;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.ant.anttestlibrary.R;
import com.ant.anttestlibrary.dialog.inPutDialog.InputDialog;
import com.ant.app_base.BaseActivity;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/12/17.
 * describe：输入框也买你
 */
public class InputActicity2 extends BaseActivity {

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_input2;
    }

    InputDialog inputDialog;

    @Override
    public void initData() {

    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {


        InputFragment inputFragment = new InputFragment();

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().add(R.id.layout_frame, inputFragment).commit();

    }

}
