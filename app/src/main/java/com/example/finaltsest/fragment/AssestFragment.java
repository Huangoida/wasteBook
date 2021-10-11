package com.example.finaltsest.fragment;

import android.os.Bundle;

import com.example.finaltsest.R;
import com.example.finaltsest.viewModel.AssestViewModel;
import com.example.finaltsest.databinding.AssestFragmentBinding;

public class AssestFragment extends BaseFragment<AssestViewModel, AssestFragmentBinding> {


    @Override
    protected int getContentViewId() {
        return R.layout.assest_fragment;
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }
}