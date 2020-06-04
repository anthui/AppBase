package com.ant.modul.leak;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ant.anttestlibrary.R;
import com.ant.app_utils.LogUtil;

/**
 * copyright：
 *
 * @author：anthui Version：1.0
 * creation date： 2020/5/11.
 * describe： dialogFragment 内存泄露模拟
 */
public class LeakFragment extends Fragment {


//    Handler handler = new Handler();
//    Message message;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        message = handler.obtainMessage();


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        TextView textView = new TextView(getContext());
//        textView.setText("hahahh");
//        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        View inflate = inflater.inflate(R.layout.fragment_db, null);
        return inflate;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("msg========================= onCreate  " + hashCode());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e("msg========================= onDestroy " + hashCode());
    }
}
