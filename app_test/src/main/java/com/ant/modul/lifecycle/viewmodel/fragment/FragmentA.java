package com.ant.modul.lifecycle.viewmodel.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ant.app_utils.LogUtil;
import com.ant.modul.lifecycle.viewmodel.LifeViewModel;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/10.
 * describe：
 */
public class FragmentA extends Fragment {
    TextView textView;
    private LifeViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        textView = new TextView(getContext());
        textView.setText("hahahha");
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return textView;
    }

    int num = 0;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(LifeViewModel.class);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                num++;
                model.getLiveData().setValue("哈哈" + num);
                textView.setText("哈哈" + num);
            }
        });
        LogUtil.e("获取 ViewMOdeo fragmentA  == " + model.hashCode());


    }
}
