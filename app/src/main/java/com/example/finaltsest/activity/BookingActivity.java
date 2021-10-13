package com.example.finaltsest.activity;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.finaltsest.MyApplication;
import com.example.finaltsest.R;
import com.example.finaltsest.bean.User;
import com.example.finaltsest.bean.WasteBook;
import com.example.finaltsest.viewModel.BookingViewModel;
import com.example.finaltsest.adapter.FragmentAdapter;
import com.example.finaltsest.databinding.ActivityBookingBinding;
import com.example.finaltsest.fragment.BookInFragment;
import com.example.finaltsest.fragment.BookOutFragment;
import com.google.android.material.tabs.TabLayoutMediator;
import com.winton.bottomnavigationview.NavigationView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends BaseActivity<BookingViewModel, ActivityBookingBinding> {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<NavigationView.Model> tabs =new ArrayList<>();
    String[] mTitle = {"支出","收入"};

    @Override
    protected int getContentViewId() {
        return R.layout.activity_booking;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentList.add(new BookOutFragment());
        fragmentList.add(new BookInFragment());
        bingding.viewPager.setAdapter(new FragmentAdapter(this,fragmentList));
        TabLayoutMediator mediator = new TabLayoutMediator(bingding.nv,bingding.viewPager,((tab, position) -> {
            tab.setText(mTitle[position]);
        }));
        mediator.attach();
        User user = MyApplication.getLoginUser(this);
        mViewModel.setLoginUser(user);
        editWateBook();
    }

    protected void editWateBook(){
        int id =getIntent().getIntExtra("wasteBookId",-1);
        if (id != -1){
            WasteBook wasteBook =LitePal.where("id=?", String.valueOf(id)).findFirst(WasteBook.class);
            mViewModel.setWasteBook(wasteBook);
        }
        if (mViewModel.getWasteBook() != null){
            if (mViewModel.getWasteBook().getValue().getType()){
                bingding.viewPager.setCurrentItem(1);
            }else {
                bingding.viewPager.setCurrentItem(0);
            }
        }

    }

    @Override
    protected void processLogic() {

    }


}