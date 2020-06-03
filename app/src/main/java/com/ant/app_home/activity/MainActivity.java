package com.ant.app_home.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.ant.app_base.BaseActivity;
import com.ant.app_base.config.IntentConfig;
import com.ant.app_home.R;
import com.ant.app_utils.LogUtil;
import com.ant.app_utils.PermissionsHelper;
import com.ant.app_utils.StringUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {


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
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.et_message)
    EditText etMessage;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_file)
    TextView tvFile;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @Override
    public void initComponents(Bundle savedInstanceState, View view) {
        permissionsHelper = new PermissionsHelper(mActivity);

        tvRight.setVisibility(View.VISIBLE);
        tvTitle.setText("新建消息");
        tvRight.setText("发送");
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String s1 = s.toString().replaceAll(",", "\n");
                tvPhone.setText(s1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvMessage.setText(s);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_main;
    }


    private void sendMessage(String phone, String msg) {
        SmsManager smsManager = SmsManager.getDefault();

//        PendingIntent pi = PendingIntent.getBroadcast(this, 0, new Intent(), 0);

        ArrayList<String> texts = smsManager.divideMessage(msg);
        for (String text : texts) {
            smsManager.sendTextMessage(phone, null, text, null, null);//分别发送每一条短信
        }
        Toast.makeText(mContext, "发送成功!", Toast.LENGTH_LONG).show();//提示成功

        tvMessage.setText("发送成功\n接收手机号：" + phone + "\n内容：" + msg);
    }

    String msg_default = "调研中，敖日格勒一行先后深入到扣河子镇秦家沟鸿昊种植专业合作社、六家子镇昌盛养殖专业合作社进行实地查看，就合作社运营模式、收益情况、推动集体经济发展等情况进行了调研指导。\\n\" +\n" +
            "                        \"敖日格勒指出，要全力推进党组织领办合作社发展集体经济工作，通过整合资金、整合项目、抱团发展等多种方式，发展嘎查村级主导产业，为决战决胜脱贫攻坚、实施乡村振兴夯实产业基础；要切实发挥基层党组织政治功能和服务功能，推动嘎查村集体经济提质增效，不断夯实基层发展基础；要结合农村牧区产权制度改革，进一步加大集体“三资”整合力度，引导农牧民以土地、劳动力等进行入股分红，推动嘎查村集体经济持续递增，带动农牧民群众持续增收。";

    PermissionsHelper permissionsHelper;

//    @OnClick(R.id.tv_send)
//    public void onViewClicked() {
//
//
//        permissionsHelper.chekPermissions(new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, new PermissionsHelper.IPermissionsResult() {
//            @Override
//            public void passPermissons() {
//
//
//                String trim1 = etInput.getText().toString().trim();
//
//                if (!StringUtil.isMobile(trim1)) {
//                    showToast("请输入正确的手机号码 " + trim1);
//                    return;
//                }
//                String trim = etMessage.getText().toString().trim();
//                if (StringUtil.isEmpty(trim)) {
//                    sendMessage(trim1, msg_default);
//
//                } else {
//                    sendMessage(trim1, trim);
//
//                }
//            }
//
//            @Override
//            public void forbitPermissons() {
//
//            }
//        });
//
//
//    }

    ;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @OnClick({R.id.iv_back, R.id.tv_file, R.id.tv_right, R.id.tv_config})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_config:
                startActivity(new Intent(mActivity, ConfigActivity.class));
                break;
            case R.id.tv_right:

                permissionsHelper.chekPermissions(new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, new PermissionsHelper.IPermissionsResult() {
                    @Override
                    public void passPermissons() {


                        String message = etMessage.getText().toString().trim();

                        String phoneList = etInput.getText().toString().trim();

                        if (StringUtil.isEmpty(message)) {
                            showToast("请输入消息内容");
                            return;
                        }
                        if (StringUtil.isEmpty(phoneList)) {
                            showToast("请输入电话号码");
                            return;
                        }
                        Intent intent = new Intent();
                        intent.putExtra(IntentConfig.KEY_OBJECT_DATA, phoneList);
                        intent.putExtra(IntentConfig.KEY_PAGE_TYPE, message);
                        setResult(IntentConfig.RESULT_CODE_OK, intent);
                        finish();

                    }

                    @Override
                    public void forbitPermissons() {

                    }
                });

                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_file:

                permissionsHelper.chekPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionsHelper.IPermissionsResult() {
                    @Override
                    public void passPermissons() {
                        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("text/plain");
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivityForResult(intent, 100);
                    }

                    @Override
                    public void forbitPermissons() {
                        showToast("请允许 文件读写权限");

                    }
                });

                break;
        }
    }


    Uri uri = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {

            if (data != null) {
                uri = data.getData();
                if (uri != null) {
                    String s = readTextFromUri(uri);
                    StringUtil.isEmpty(s);
                    etInput.setText("" + s);
                    //获取到
                    LogUtil.e("msg============= " + s);
                }

            }
        }

    }

    private String readTextFromUri(Uri uri) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            try (InputStream inputStream =
                         getContentResolver().openInputStream(uri);
                 BufferedReader reader = new BufferedReader(
                         new InputStreamReader(Objects.requireNonNull(inputStream)))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(",");
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

