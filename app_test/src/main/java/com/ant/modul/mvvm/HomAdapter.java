package com.ant.modul.mvvm;

import android.content.Context;

import com.ant.anttestlibrary.R;
import com.ant.app_base.baseAdapter.BaseAdapter;
import com.ant.app_base.baseAdapter.base.ViewHolder;

import java.util.List;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/11.
 * describe：
 */
public class HomAdapter extends BaseAdapter<String> {
    public HomAdapter(Context context, List<String> datas) {
        super(context, R.layout.item_list, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.tv_name, s);

    }
}
