//package com.ant.anttestlibrary.activity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//import com.ant.anttestlibrary.R;
//import com.ant.app_base.BaseActivity;
//import com.ant.app_utils.LogUtil;
//import com.ant.app_utils.date.RxTimer;
//
//
///**
// * copyright：
// * author：anthui
// * Version：1.0
// * creation date： 2019/8/5.
// * describe：定时器
// * https://mcxiaoke.gitbooks.io/rxdocs/content/topics/Getting-Started.html
// */
//public class RxTimeTextActivity extends BaseActivity {
//
//
//    private TextView btnCLick1;
//    private TextView btnCLick2;
//    private TextView tvTitle;
//    RxTimer rxTimer1, rxTimer2;
//
//    @Override
//    protected void initComponents(Bundle savedInstanceState) {
//
//
//        btnCLick1 = findViewById(R.id.btn_click1);
//        btnCLick2 = findViewById(R.id.btn_click2);
//
//        rxTimer1 = new RxTimer();
//        rxTimer2 = new RxTimer();
//
//        btnCLick1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rxTimer2.cancel();
//
////                rxTimer1.timer(1000, new RxTimer.RxAction() {
////                    @Override
////                    public void action(long number) {
////                        LogUtil.e("当前时间==========" + number);
////
////                        btnCLick1.setText("num==" + number);
////                    }
////                });
//            }
//        });
//
//        btnCLick2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rxTimer2.interval(1000, new RxTimer.RxAction() {
//                    @Override
//                    public void action(long number) {
//                        LogUtil.e("当前时间==========" + number);
//                        btnCLick2.setText("num==" + number);
//
//                    }
//                });
//            }
//        });
//
//        tvTitle = findViewById(R.id.tv_title);
//        tvTitle.setText("Rx定时器");
//    }
//
//    private String filePath = "/storage/emulated/0/Download/ic_launcher.png";
//
//    @Override
//    protected void initData() {
//
//
//    }
//
//
//    @Override
//    public int getMainContentViewId() {
//        return R.layout.activity_rxtimr;
//    }
//
//
//}
