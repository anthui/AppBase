package com.ant.app_base.baseAdapter.base;


/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2018/1/31
 * describe：
 */
public interface ItemAdapter<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}
