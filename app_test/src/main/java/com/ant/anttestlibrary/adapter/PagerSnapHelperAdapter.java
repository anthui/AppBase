package com.ant.anttestlibrary.adapter;

import android.content.Context;
import android.graphics.Color;

import com.ant.anttestlibrary.R;
import com.ant.app_base.baseAdapter.BaseAdapter;
import com.ant.app_utils.LogUtil;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class PagerSnapHelperAdapter extends BaseAdapter<String> {

    // 数据集
    private ArrayList<String> mDataList;


    public PagerSnapHelperAdapter(Context context, List<String> datas) {
        super(context, R.layout.recycle_pager_item, datas);
    }


//    private int mWidth;
//    private int mHeight;
//
//    //
//    public PagerSnapHelperAdapter(ArrayList<String> dataset, int width, int height) {
//        super();
//        this.mDataList = dataset;
//        //
//        mWidth = width;
//        mHeight = height;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        Log.e("xiaxl: ", "---onCreateViewHolder---");
//        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
//        View view = View.inflate(viewGroup.getContext(), R.layout.recycle_pager_item, null);
//
//
//        View contentView = view.findViewById(R.id.add_btn);
//        RelativeLayout.LayoutParams rl = (RelativeLayout.LayoutParams) contentView.getLayoutParams();
//        rl.width = mWidth;
//        rl.height = mHeight;
//        contentView.setLayoutParams(rl);
//
//
//        // 创建一个ViewHolder
//        ViewHolder holder = new ViewHolder(view);
//        return holder;
//    }

//    @Override
//    public void onBindViewHolder(ViewHolder viewHolder, int position) {
//        Log.e("xiaxl: ", "---onBindViewHolder---" + position);
//
//        // 绑定数据到ViewHolder上
//        viewHolder.itemView.setTag(position);
//        //
//        viewHolder.mTextView.setText(position + " item");
//    }
//
//    @Override
//    public int getItemCount() {
//        return mDataList.size();
//    }

    @Override
    protected void convert(com.ant.app_base.baseAdapter.base.ViewHolder holder, String s, int position) {

        LogUtil.e("msg====================" + position);
        holder.setText(R.id.add_btn, "item" + position);
        if (position % 2 == 0) {

            holder.setBackgroundColor(R.id.add_btn, Color.parseColor("#ff0000"));
        } else {
            holder.setBackgroundColor(R.id.add_btn, Color.parseColor("#00ff00"));

        }
    }


    /**
     *
     */
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView mTextView;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            mTextView = (TextView) itemView.findViewById(R.id.add_btn);
//        }
//    }
}
