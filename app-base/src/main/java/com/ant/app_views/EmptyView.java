package com.ant.app_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ant.app_base.R;


/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2017/12/27
 * describe： 空值页集成
 */
public class EmptyView extends LinearLayout {

    public static final int STATE_ERROR = 0;
    public static final int STATE_NETWORK = 1;
    public static final int STATE_NO_DATA = 2;
    public static final int STATE_LOADING = 4;
    public static final int STATE_NONE = 3;

    private ImageView mIvIcon;
    private TextView mTvMessage;
    private int state = STATE_NONE;
    //进度条
    private ProgressBar progressBar;

    public EmptyView(Context context) {
        super(context);
        init(context);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public EmptyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    View ly_root;

    public void setWhitee() {
        ly_root.setBackgroundResource(R.color.color_white);
    }

    private void init(Context context) {
        inflate(context, R.layout.layout_empty, this);
        mIvIcon = (ImageView) findViewById(R.id.iv_icon);
        ly_root = findViewById(R.id.ly_root);
        //空置处理
        ly_root.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        progressBar = findViewById(R.id.progressBar);
        mTvMessage = (TextView) findViewById(R.id.tv_message);
        // mTvMessage.setVisibility(View.INVISIBLE);
        updateView();

    }

    public void removeClick() {
        ly_root.setOnClickListener(null);
    }
    //  AnimationDrawable drawable;

    public void setState(int state) {
        this.state = state;
        updateView();
    }

    public int getState() {
        return this.state;
    }


    public void setMessage(int res) {
        mTvMessage.setText(res);
    }

    public void setMessage(String res) {
        mTvMessage.setText(res);
    }

    public void setTvColor(String color)

    {
        int i = Color.parseColor(color);
        mTvMessage.setTextColor(i);

    }

    public void setIcon(int icon) {
        mIvIcon.setImageResource(icon);
    }


    private void updateView() {

        if (state == STATE_NONE) {
            setVisibility(View.GONE);
            return;
        }

        setVisibility(View.VISIBLE);

        mIvIcon.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        switch (state) {
            case STATE_NONE:
                setVisibility(View.GONE);
                break;
            case STATE_ERROR:
                mIvIcon.setVisibility(View.VISIBLE);
                mIvIcon.setImageResource(R.mipmap.icon_no_net);
                mTvMessage.setText("");

                break;
            case STATE_NETWORK:
                mIvIcon.setVisibility(View.VISIBLE);
                mIvIcon.setImageResource(R.mipmap.icon_no_net);
                mTvMessage.setText(R.string.str_no_net);
                break;
            case STATE_NO_DATA:

                mIvIcon.setVisibility(View.VISIBLE);
                mIvIcon.setImageResource(R.mipmap.icon_no_data);
                mTvMessage.setText(R.string.str_no_data);
                break;
            case STATE_LOADING:
                progressBar.setVisibility(VISIBLE);
                mTvMessage.setText(R.string.str_loading);
                break;
            default:
                break;
        }
    }

    public void setNetClickListener(final OnClickListener netClickListener) {
        // this.netClickListener = netClickListener;
        mIvIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if (netClickListener != null && state == STATE_NETWORK)
                    netClickListener.onClick(view);
            }
        });
        //  mIvIcon.setOnClickListener(netClickListener);
    }

    public void setNoData(boolean noData) {
        if (noData) {
            setState(EmptyView.STATE_NO_DATA);
        } else {
            setState(EmptyView.STATE_NONE);

        }
    }
}
