package com.example.finaltsest.fragment;

import android.os.Bundle;

import com.example.finaltsest.R;
import com.example.finaltsest.viewModel.MineViewModel;
import com.example.finaltsest.databinding.MineFragmentBinding;

public class MineFragment extends BaseFragment<MineViewModel, MineFragmentBinding> {


    @Override
    protected int getContentViewId() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }


}