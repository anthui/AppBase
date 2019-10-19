package com.ant.anttestlibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.ant.anttestlibrary.R;
import com.ant.anttestlibrary.TouncherLayout;
import com.ant.app_base.BaseActivity;
import com.ant.app_utils.LogUtil;

import butterknife.BindView;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/15.
 * describe： 这里验证事件的分发机制
 */
public class LauncherActivity extends BaseActivity {
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.view_home2)
    TouncherLayout viewHome2;
    @BindView(R.id.view_home3)
    TouncherLayout viewHome3;
    @BindView(R.id.view_home)
    TouncherLayout viewHome;

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_launcher;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

        viewHome.pageType = 1;
        viewHome2.pageType = 2;
        viewHome3.pageType = 3;
        tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogUtil.e("msg==================================click");
            }
        });
        tvContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return true;
            }
        });


        LogUtil.e("msg==============long====================click");
        int[] ints = {1, 22, 34, 21, 4, 56, 22, 55, 77, 223, 2, 4};
//        sort(ints);

        maoPao(ints);
        xuanze(ints);

        int[] sor = {1, 2,3, 5, 6, 7, 9, 22, 53, 55, 65, 85};
        int i = erCha(sor, -22);
        LogUtil.e("打印=====================" + i);


    }

    //选择排序
    public void sort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            //记录最小值
            int min = i;
            for (int i1 = i + 1; i1 < list.length; i1++) {
                if (list[min] > list[i1]) {
                    min = i1;
                }
            }
            //说明最小值的位置已经发送改变
            if (min != i) {
                int temp = list[i];
                list[i] = list[min];
                list[min] = temp;
            }
        }

        for (int i = 0; i < list.length; i++) {
            loge("================================" + list[i]);
        }

    }
    //冒泡排序
//    public void sort(int[] list) {
//
//        for (int i = 0; i < list.length - 1; i++) {
//            for (int i1 = i + 1; i1 < list.length; i1++) {
//
//
//                Integer first = list[i];
//                Integer end = list[i1];
//                //从小到大排序
//                if (first > end) {
//                    int temp = first;
//                    list[i] = end;
//                    list[i1] = temp;
//                }
//
//                //从大到小排序
////                if (first < end) {
////                    int temp = first;
////                    list[i] = end;
////                    list[i1] = temp;
////                }
//
//            }
//        }


//    @OnClick({ R.id.view_home})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.tv_content:
//
//                loge("tv_vontent");
//                break;
//            case R.id.view_home2:
//                loge("layout_home1");
//
//                break;
//            case R.id.view_home:
//                loge("layout_home");
//
//                break;
//
//        }
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        loge("==========onTouchEvent=============activity" + getEventName(event));
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        loge("==========dispatchTouchEvent=============activity" + getEventName(ev));
        View decorView = getWindow().getDecorView();
        if (true) {
            startActivity(new Intent(mActivity, NewIntenActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
        return super.dispatchTouchEvent(ev);
    }


    public static String getEventName(MotionEvent event) {
        String name = "哈哈";
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                name = "--------按下 DOWN";
                break;
            case MotionEvent.ACTION_UP:
                name = "--------起来 UP";

                break;
            case MotionEvent.ACTION_SCROLL:

                name = "-------滑动 SCROLL";

                break;
            case MotionEvent.ACTION_CANCEL:
                name = "------取消 CANCEL";

                break;
        }
        return name;

    }


    private void log(int[] list) {
        for (int i = 0; i < list.length; i++) {
            LogUtil.e("打印=========" + list[i]);
        }
    }

    private void maoPao(int[] list) {

        for (int i = 0; i < list.length - 1; i++) {

            for (int j = i + 1; j < list.length; j++) {
                if (list[i] > list[j]) {
                    int temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }

            }
        }

        log(list);

    }


    private void xuanze(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < list.length; j++) {
                if (list[min] > list[j]) {
                    min = j;
                }
            }

            if (min != i) {

                int temp = list[min];
                list[min] = list[i];
                list[i] = temp;
            }
        }
        log(list);

    }


    private int erCha(int[] list, int dex) {

        int left = 0;
        int right = list.length - 1;

        int num = 0;
        while (left <= right) {
            int index = (left + right) / 2;
            num = num + 1;
            LogUtil.e("第几次便利==" + num);
            if (dex == list[index]) {
                return index;
            }
            //在右区间
            if (dex > list[index]) {
                left = index+1;
            } else {
                right = index-1;
            }
        }
        return -1;
    }


}
