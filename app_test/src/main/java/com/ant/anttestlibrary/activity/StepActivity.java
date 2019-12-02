package com.ant.anttestlibrary.activity;

import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.ant.anttestlibrary.R;
import com.ant.app_base.BaseActivity;
import com.ant.app_utils.LogUtil;

import butterknife.BindView;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/11/8.
 * describe：
 */
public class StepActivity extends BaseActivity implements SensorEventListener {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.view_line_bar)
    View viewLineBar;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_step)
    TextView tvStep;

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_step;
    }

    @Override
    public void initData() {

    }

    Sensor stepCounter;

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {


        //获取传感器 管理器
        SensorManager sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);


        //获取步数传感器
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        Sensor stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        //是否注册成功
        boolean b = sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_FASTEST);
//        boolean a = sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_FASTEST);


//        LogUtil.e("msg 步数传感器注册===== " + b);
        LogUtil.e("msg 步数传感器注册===== " + stepCounter + "  是否注册成功===  " + b);
//        LogUtil.e("msg 步数传感器注册===== " + b);

    }


    int count = 0;

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;

        if (event.sensor == stepCounter) {
            tvStep.setText("当前步数=== " + values[0] + "");

        } else {
            count += values[0];
            tvStep.setText("当前步数=== " + count + "");

        }


        StringBuilder stringBuilder = new StringBuilder();
        for (float value : values) {
            stringBuilder.append(value);
            stringBuilder.append("\n");
        }
        LogUtil.e("msg==== " + stringBuilder.toString() + "  type==");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


        LogUtil.e("accuracy====== " + accuracy);
    }
}
