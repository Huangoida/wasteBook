package com.example.finaltsest.fragment;

import android.os.Bundle;

import android.view.View;

import com.example.finaltsest.R;
import com.example.finaltsest.bean.WasteBook;
import com.example.finaltsest.viewModel.HomeViewModel;
import com.example.finaltsest.activity.BookingActivity;
import com.example.finaltsest.databinding.HomeFragmentBinding;
import com.example.finaltsest.utils.ActivitysBuilder;

import org.litepal.LitePal;

import java.util.List;

public class HomeFragment extends BaseFragment<HomeViewModel, HomeFragmentBinding> {


    @Override
    protected int getContentViewId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        List<WasteBook> wasteBooks = LitePal.findAll(WasteBook.class);
        for (WasteBook w :
                wasteBooks) {
            System.out.println(w.getAmount());
        }
    }

    @Override
    protected void setListener() {
        binding.addWasteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivitysBuilder.build(HomeFragment.this, BookingActivity.class).startActivity();
            }
        });
    }
}