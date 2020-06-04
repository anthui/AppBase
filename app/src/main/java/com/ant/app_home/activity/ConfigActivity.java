package com.ant.app_home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.ant.app_base.BaseActivity;
import com.ant.app_base.config.IntentConfig;
import com.ant.app_database.sp.SharedPreferencesUtil;
import com.ant.app_home.R;
import com.ant.app_utils.LogUtil;
import com.ant.app_utils.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * copyright：
 *
 * @author：anthui Version：1.0
 * creation date： 2020/6/3.
 * describe：
 */
public class ConfigActivity extends BaseActivity {
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
    @BindView(R.id.et_jiange)
    EditText etJiange;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_piliang)
    EditText etPiliang;

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_config;
    }

    @Override
    public void initData() {

    }


    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

        tvTitle.setText("参数配置");
        tvRight.setText("保存");
        tvRight.setVisibility(View.VISIBLE);
        ConfigBean instance = ConfigBean.getInstance(mContext);
        etJiange.setText("" + instance.getJiange());
        etPhoneNum.setText("" + instance.getNum());
        etPiliang.setText("" + instance.getJiangeTime());
    }


    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                String jiangeTime = etPiliang.getText().toString().trim();

                if (StringUtil.isEmpty(jiangeTime)) {
                    showToast("间隔时长不能为空，不需要就写0");
                    return;
                }
                String num = etPhoneNum.getText().toString().trim();
                if (StringUtil.isEmpty(num)) {
                    showToast("批量数量不能为空，不需要就写0");
                    return;
                }
                String jiange = etJiange.getText().toString().trim();

                if (StringUtil.isEmpty(jiange)) {
                    showToast("每条间隔不能为空，不需要就写0");
                    return;
                }
                int value = Integer.parseInt(jiangeTime);
                SharedPreferencesUtil.setInteger(mContext, IntentConfig.KEY_jiange_time, value);
                int value1 = Integer.parseInt(jiange);
                SharedPreferencesUtil.setInteger(mContext, IntentConfig.KEY_jiange, value1);
                int value2 = Integer.parseInt(num);
                LogUtil.e("msg==============" + value + "  " + value1 + "  " + value2);
                SharedPreferencesUtil.setInteger(mContext, IntentConfig.KEY_jiange_num, value2);

                showToast("保存成功");
                finish();

                break;
        }
    }
}
