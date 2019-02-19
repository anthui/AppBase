package com.ant.views.recycview;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ant.utils.SizeUtil;


/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2018/4/24
 * describe： 设置recycleview的间距问题
 */
public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private int left;
    private int right;
    private int top;
    private int bottom;

    public ItemOffsetDecoration(Context context, int left, int right, int top, int bottom) {
        this.left = SizeUtil.dipToPx(context, left);
        this.right = SizeUtil.dipToPx(context, right);
        this.top = SizeUtil.dipToPx(context, top);
        this.bottom = SizeUtil.dipToPx(context, bottom);
        //   this.left = SizeUtil.dipToPx(context, left);
    }

    public ItemOffsetDecoration(Context context, int leftAndRight, int topAndBottom) {
        this.left = SizeUtil.dipToPx(context, leftAndRight);
        this.right = SizeUtil.dipToPx(context, leftAndRight);
        this.top = SizeUtil.dipToPx(context, topAndBottom);
        this.bottom = SizeUtil.dipToPx(context, topAndBottom);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(left, top, right, bottom);
    }
}