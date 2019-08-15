package com.ant.app_base.baseAdapter;

import android.content.Context;
import android.view.LayoutInflater;

import com.ant.app_base.baseAdapter.base.ItemAdapter;
import com.ant.app_base.baseAdapter.base.ViewHolder;

import java.util.List;

/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2017/11/22
 * describe： 基类 多功能适配器--所有单布局适配器中的父类
 */

//单个布局时抽取出来
public abstract class BaseAdapter<T> extends BaseItemAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public BaseAdapter(final Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemAdapter(new ItemAdapter<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                BaseAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);




}
