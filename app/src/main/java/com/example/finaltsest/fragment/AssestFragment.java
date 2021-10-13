package com.example.finaltsest.fragment;

import android.os.Bundle;

import com.example.finaltsest.R;
import com.example.finaltsest.bean.WasteBook;
import com.example.finaltsest.viewModel.AssestViewModel;
import com.example.finaltsest.databinding.AssestFragmentBinding;

import org.litepal.LitePal;

import java.util.List;

import im.dacer.androidcharts.LineView;

public class AssestFragment extends BaseFragment<AssestViewModel, AssestFragmentBinding> {


    @Override
    protected int getContentViewId() {
        return R.layout.assest_fragment;
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        List<WasteBook> wasteBooks =LitePal.findAll(WasteBook.class);
        
    }

    @Override
    protected void setListener() {

    }
}