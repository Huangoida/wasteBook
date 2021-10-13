package com.example.finaltsest.fragment;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.finaltsest.R;
import com.example.finaltsest.activity.SearchActivity;
import com.example.finaltsest.adapter.BookOutRecycleVIewApapter;
import com.example.finaltsest.adapter.HomeRecycleVIewAdapter;
import com.example.finaltsest.bean.Item;
import com.example.finaltsest.bean.WasteBook;
import com.example.finaltsest.utils.DialogTuil;
import com.example.finaltsest.utils.ToastUtils;
import com.example.finaltsest.viewModel.BookingViewModel;
import com.example.finaltsest.viewModel.HomeViewModel;
import com.example.finaltsest.activity.BookingActivity;
import com.example.finaltsest.databinding.HomeFragmentBinding;
import com.example.finaltsest.utils.ActivitysBuilder;
import com.example.finaltsest.viewModel.MainViewModel;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomeFragment extends BaseFragment<HomeViewModel, HomeFragmentBinding> {

    private HomeRecycleVIewAdapter homeRecycleVIewAdapter;
    private List<WasteBook> wasteBooks;
    private MainViewModel mainViewModel;

    @Override
    protected int getContentViewId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        binding.toolbar.inflateMenu(R.menu.home_toolmenu);
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        wasteBooks = LitePal.where("userId=?", String.valueOf(mainViewModel.getLoginUser().getValue().getId())).find(WasteBook.class);
        wasteBooks.sort(Comparator.comparing(WasteBook::getTime));
        homeRecycleVIewAdapter = new HomeRecycleVIewAdapter(R.layout.item_home_view,wasteBooks,this.getContext());
        binding.homeRev.setAdapter(homeRecycleVIewAdapter);
        binding.homeRev.setLayoutManager(new LinearLayoutManager(this.getContext()));
        sumAmount();
    }

    protected void sumAmount(){
        double outAmount = LitePal.where("userId="+String.valueOf(mainViewModel.getLoginUser().getValue().getId()) +" and type=0").sum(WasteBook.class,"amount",Double.class);
        double inAmount = LitePal.where("userId="+String.valueOf(mainViewModel.getLoginUser().getValue().getId()) +" and type=1").sum(WasteBook.class,"amount",Double.class);
        double totalAmount = inAmount - outAmount;

        binding.inAcount.setText("￥"+String.valueOf(inAmount));
        binding.outAcount.setText("￥"+String.valueOf(outAmount));
        if (totalAmount < 0){
            binding.totalAcount.setText("-￥"+String.valueOf(Math.abs(totalAmount)));
        }else {
            binding.totalAcount.setText("￥"+String.valueOf(Math.abs(totalAmount)));
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
        homeRecycleVIewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                ToastUtils.showToast(String.valueOf(position));
                DialogTuil.showWateBookDialog(getContext(), (WasteBook) adapter.getData().get(position));
            }
        });
        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.aciton_search:
                        ActivitysBuilder.build(HomeFragment.this, SearchActivity.class).startActivity();
                        break;
                }
                return true;
            }
        });
    }


}