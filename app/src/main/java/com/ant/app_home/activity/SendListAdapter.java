package com.ant.app_home.activity;

import android.content.Context;

import com.ant.app_base.baseAdapter.BaseAdapter;
import com.ant.app_base.baseAdapter.base.ViewHolder;
import com.ant.app_home.R;
import com.ant.app_utils.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * copyright：
 *
 * @author：anthui Version：1.0
 * creation date： 2020/6/2.
 * describe：
 */
public class SendListAdapter extends BaseAdapter<MessageBean> {
    public SendListAdapter(Context context, List<MessageBean> datas) {
        super(context, R.layout.item_list_send, datas);
    }

    @Override
    protected void convert(ViewHolder holder, MessageBean messageBean, int position) {

        holder.setText(R.id.tv_num, messageBean.getPhoneNum());
        holder.setText(R.id.tv_message, messageBean.getMessage());
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        String text = DateUtil.dateToString(date, DateUtil.VIDEO_TIME);
        holder.setText(R.id.tv_time, text);

    }
}
