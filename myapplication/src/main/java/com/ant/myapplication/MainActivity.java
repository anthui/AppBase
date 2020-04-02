package com.ant.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ant.app_base.BaseActivity;

public class MainActivity extends BaseActivity {

    private TextView tvTitle;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//    }

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        findViewById(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
            }
        });
    }
}
