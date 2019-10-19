package com.ant.anttestlibrary.activity;

import android.os.Bundle;
import android.view.View;

import com.ant.anttestlibrary.R;
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

    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

        dashView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dashView.startAnimation(30);

//
//                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 60, 0);
//                valueAnimator.setDuration(60000).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        dashView.startAnimation();
//
//                    }
//                });
//                valueAnimator.setInterpolator(new LinearInterpolator());
//
//                valueAnimator.start();

//                dashView.setCreditValue(new Random().nextInt(950 - 350) + 350);

            }
        });
    }


}
