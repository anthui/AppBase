package com.ant.anttestlibrary.activity;

import android.os.Bundle;
import android.view.View;

import com.ant.anttestlibrary.R;
import com.ant.anttestlibrary.view.BMIView;
import com.ant.anttestlibrary.view.DashboardView;
import com.ant.app_base.BaseActivity;

import butterknife.BindView;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/10/16.
 * describe：
 */
public class DashboardActivity extends BaseActivity {
    @BindView(R.id.dash_view)
    DashboardView dashView;

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_dash;
    }

    @Override
    public void initData() {


        BMIView view1 = findViewById(R.id.BMIView1);
        BMIView view2 = findViewById(R.id.BMIView2);

        view1.setValue(24);
        view1.setValue(44);


        // 代码设置各种值
//        NewBmiView bmiview = findViewById(R.id.bmiview);
//        //将BMI指数传递过去
//        bmiview.setBmi(35);
//        bmiview.setBmiText("35.0");

    }


    private String checn(long walkTime) {
        String hour = "00";
        String min = "00";
        if (walkTime > 60) {
            long h = walkTime / 60;
            if (h >= 10) {
                hour = h + "";
            } else {
                hour = "0" + h;
            }

            //剩余分钟
            long m = walkTime - 60 * h;

            if (m >= 10) {
                min = m + "";

            } else {
                min = "0" + m;
            }

        } else if (walkTime < 10) {

            min = "0" + walkTime;
        } else {
            min = "" + walkTime;

        }

        return hour + " 小时 " + min + " 分钟";
    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

        dashView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dashView.startAnimation(30);
            }
        });
    }


}
