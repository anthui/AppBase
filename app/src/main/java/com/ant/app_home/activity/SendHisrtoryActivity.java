package com.ant.app_home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ant.app_base.BaseActivity;
import com.ant.app_base.config.IntentConfig;
import com.ant.app_home.R;
import com.ant.app_utils.LogUtil;
import com.ant.app_utils.StringUtil;
import com.ant.app_views.EmptyView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * copyright：
 *
 * @author：anthui Version：1.0
 * creation date： 2020/6/2.
 * describe：
 */
public class SendHisrtoryActivity extends BaseActivity {
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
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.epmty_view)
    EmptyView epmtyView;

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_history;
    }

    @Override
    public void initData() {


    }

    ArrayList<MessageBean> datas;
    SendListAdapter adapter;

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        datas = new ArrayList<>();
        adapter = new SendListAdapter(mContext, datas);
        recyclerView.setAdapter(adapter);

        ivBack.setVisibility(View.GONE);
        tvTitle.setText("发送消息");
        tvRight.setText("新建");
        tvRight.setVisibility(View.VISIBLE);

    }


    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.tv_right:
                startActivityForResult(new Intent(mActivity, MainActivity.class), 100);
                break;
        }
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            if (StringUtil.isEmpty(message)) {
                showToast("消息获取失败");
                return;
            }

            if (split == null || split.length == 0) {
                showToast("手机号获取失败");
                return;
            }

            int arg1 = msg.what;
            if (arg1 > split.length - 1) {
                epmtyView.setState(EmptyView.STATE_NONE);
                showToast("发送完成");
                return;
            }

            sendNum++;
            sendMessages(split[arg1], message);

            if (configBean == null) {
                configBean = ConfigBean.getInstance(mContext);
            }


            LogUtil.e("msg========= " + sendNum + "  congiNum==" + configBean.getNum());
            if (sendNum >= configBean.getNum()) {
                LogUtil.e("延迟 =============================");
                if (configBean.getJiangeTime() > 0) {
                    showToast("进入" + configBean.getJiangeTime() + "秒等待");
                }
                sendNum = 0;
                handler.sendEmptyMessageDelayed(arg1 + 1, configBean.getJiangeTime() * 1000);
            } else {
                handler.sendEmptyMessageDelayed(arg1 + 1, configBean.getJiange() * 1000);
            }


        }
    };
    String[] split;
    String message = "";

    ConfigBean configBean;

    int sendNum = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == IntentConfig.RESULT_CODE_OK) {


            String phoneList = data.getStringExtra(IntentConfig.KEY_OBJECT_DATA);
            message = data.getStringExtra(IntentConfig.KEY_PAGE_TYPE);

            split = phoneList.split(",");

            epmtyView.setState(EmptyView.STATE_LOADING);
            configBean = ConfigBean.getInstance(mContext);

            LogUtil.e("msg=== 参数=== " + configBean.toString());
            sendNum = 0;
            handler.sendEmptyMessage(0);
        }
    }


    private void sendMessages(String phone, String msg) {

        MessageBean e = new MessageBean();
        e.setPhoneNum(phone);

        //是手机号时，在发送
        if (StringUtil.isMobile(phone)) {
            showToast("发送给：" + phone);

            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> texts = smsManager.divideMessage(msg);
            for (String text : texts) {
                smsManager.sendTextMessage(phone, null, text, null, null);//分别发送每一条短信
            }
            e.setMessage(msg);
        } else {
            showToast("错误号码：" + phone);

            e.setMessage("手机号有误");
        }


        if (datas.size() > 3000) {
            datas.clear();
            datas.add(0, e);
            adapter.notifyDataSetChanged();
        } else {
            datas.add(0, e);
            adapter.notifyItemInserted(0);
            recyclerView.smoothScrollToPosition(0);
        }


    }


}
