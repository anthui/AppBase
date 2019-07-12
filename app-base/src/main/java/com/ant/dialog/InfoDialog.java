package com.ant.dialog;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ant.base.R;
import com.ant.base.nicedialog.BaseDialog;
import com.ant.utils.CommonUtils;
import com.ant.utils.StringUtil;

/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2018/6/27
 * describe：标准信息弹框,输入框
 */
public class InfoDialog extends BaseDialog {
    TextView tvTitle;
    TextView tvCancle;
    TextView tvOk;
    EditText etInput;
    // Unbinder unbinder;
    String msg = "";
    String etText = "";//输入框文本
    String etHint = "";//预留文本
    String cancle = "取消";
    String ok = "确定";

    //0 默认提示 1输入框
    private int pageType = 0;

    @Override
    public int getMainContentViewId() {
        return R.layout.dialog_info;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOutCancel(false);
    }

    @Override
    protected void initData() {
        tvTitle.setText(msg);
        tvOk.setText(ok);
        tvCancle.setText(cancle);

        switch (pageType) {
            case 0:
                break;
            case 1:
                etInput.setText(etText);
                etInput.setHint(etHint);
                etInput.setVisibility(View.VISIBLE);

                CommonUtils.showKeyboard(mContext, etInput);
                break;
        }

    }


    @Override
    protected void initComponents(View mView) {
        cancle = getString(R.string.str_cancel);
        ok = getString(R.string.str_confirm);

        tvTitle = mView.findViewById(R.id.tv_title);
        tvCancle = mView.findViewById(R.id.tv_cancle);
        tvOk = mView.findViewById(R.id.tv_ok);
        etInput = mView.findViewById(R.id.et_input);
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCallBack != null) {

                    if (pageType == 0) {

                        onCallBack.onListener(InfoDialog.this, false, "");
                    } else if (pageType == 1) {
                        String trim = etInput.getText().toString().trim();

                        etText = trim;
                        onCallBack.onListener(InfoDialog.this, false, etText);

                    }
                }
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCallBack != null) {

                    if (pageType == 0) {

                        onCallBack.onListener(InfoDialog.this, true, "");
                    } else if (pageType == 1) {
                        String trim = etInput.getText().toString().trim();

                        etText = trim;

                        onCallBack.onListener(InfoDialog.this, true, etText);

                    }


                }
            }
        });

    }


    public InfoDialog setMsg(String msg) {
        this.msg = msg + "";
        if (tvTitle != null) {
            tvTitle.setText(msg);
        }
        return this;
    }

    /**
     * 设置输入框
     */
    public InfoDialog setMsg(String title, String etText, String etHint) {


        loge("===================" + etText);
        this.msg = title + "";
        if (tvTitle != null) {
            tvTitle.setText(title);
        }

        pageType = 1;

        if (!StringUtil.isEmpty(etText)) {


            this.etText = etText + "";
            loge("===================");

        } else {
            loge("============kong=======");

        }

        if (etInput != null) {
            etInput.setText(etText);
            etInput.setVisibility(View.VISIBLE);
        }
        this.etHint = etHint + "";
        if (etInput != null) {
            etInput.setHint(etHint);
        }
        return this;
    }

    /**
     * 设置确定按钮
     */
    public InfoDialog setMsg(String cancle, String ok) {

        this.cancle = cancle;
        if (tvCancle != null) {
            tvCancle.setText(cancle);
        }
        this.ok = ok;
        if (tvOk != null) {
            tvOk.setText(ok);
        }
        return this;
    }

    OnCallBack onCallBack;

    public InfoDialog setOnCallBack(OnCallBack onCallBack) {
        this.onCallBack = onCallBack;
        return this;

    }

    public interface OnCallBack {
        void onListener(InfoDialog infoDialog, boolean isOk, String etText);

    }
}
