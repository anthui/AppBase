package com.ant.modul.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.lifecycle.Observer;

import com.ant.anttestlibrary.R;
import com.ant.app_base.BaseActivity;
import com.ant.app_utils.LogUtil;

import butterknife.OnClick;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/9.
 * describe：
 */
public class LifecycleActivity2 extends BaseActivity {
    BaseLifecycle baseLifecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseLifecycle = new BaseLifecycle();
        LogUtil.e(" activity2================== onCreate()");

        getLifecycle().addObserver(baseLifecycle);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e(" activity2================== onDestroy()");

//        getLifecycle().removeObserver(baseLifecycle);
    }

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_lifecycle;
    }

    @Override
    public void initData() {

    }



    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {
        LiveModel.getInstance().addObserver(new Observer<String>() {
            @Override
            public void onChanged(String s) {
                LogUtil.e("msg  数据发生变化= " + s + "  actvityName== " + getLocalClassName());
            }
        });
    }


    @OnClick(R.id.tv_content)
    public void onViewClicked() {

        startActivity(new Intent(mActivity, LifecycleActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.e(" activity2================== onStart()");

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.e(" activity2================== onResume()");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LiveModel.getInstance().setLiveData("onResume 1111111111111111111111111");

            }
        },100);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.e(" activity2================== onPause");
    }
}
