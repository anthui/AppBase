package com.ant.modul.lifecycle.viewmodel.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
public class FragmentB extends Fragment {
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        textView = new TextView(getContext());
        textView.setText("hahahha");
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setBackgroundColor(Color.RED);
        return textView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LifeViewModel viewModel = new ViewModelProvider(requireActivity()).get(LifeViewModel.class);

        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);

            }
        });
        LogUtil.e("获取 ViewMOdeo fragmentB  == " + viewModel.hashCode());

    }

}
