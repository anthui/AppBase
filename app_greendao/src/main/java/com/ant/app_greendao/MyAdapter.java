package com.ant.app_greendao;

import android.content.Context;
import android.graphics.Color;

import com.ant.app_base.baseAdapter.BaseAdapter;
import com.ant.app_base.baseAdapter.base.ViewHolder;
import com.ant.app_greendao.dataBean.User;

import java.util.List;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/2/21.
 * describe：
 */
public class MyAdapter extends BaseAdapter<User> {

    public MyAdapter(Context context, int layoutId, List<User> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, User s, int position) {

        holder.setText(R.id.tv_content, position + "  " + s.toString());
        if (position % 2 == 0) {
            holder.setBackgroundColor(R.id.tv_content, Color.parseColor("#ff0000"));
        } else {
            holder.setBackgroundColor(R.id.tv_content, Color.parseColor("#00ff00"));

        }
    }
}
