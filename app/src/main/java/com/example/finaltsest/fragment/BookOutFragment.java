package com.example.finaltsest.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.finaltsest.MainActivity;
import com.example.finaltsest.R;
import com.example.finaltsest.adapter.BookOutRecycleVIewApapter;
import com.example.finaltsest.bean.Item;
import com.example.finaltsest.utils.ToastUtils;
import com.example.finaltsest.viewModel.BookOutViewModel;
import com.example.finaltsest.databinding.BookOutFragmentBinding;
import com.example.finaltsest.viewModel.BookingViewModel;
import com.example.finaltsest.viewModel.MainViewModel;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class BookOutFragment extends BaseFragment<BookOutViewModel, BookOutFragmentBinding> implements TextWatcher {

    private BookOutRecycleVIewApapter apapter;
    private List<Item> itemList = new ArrayList<>();
    private View old_view = null;
    private BookingViewModel bookingViewModel;
    @Override
    protected int getContentViewId() {
        return R.layout.book_out_fragment;
    }


    @Override
    protected void processLogic(Bundle savedInstanceState) {
        bookingViewModel = new ViewModelProvider(getActivity()).get(BookingViewModel.class);
        mViewModel.setContext(this.getActivity());
        mViewModel.setActivityViewModel(bookingViewModel);
        binding.setVm(mViewModel);
        itemList = LitePal.where("tab=?","0").find(Item.class);
        apapter = new BookOutRecycleVIewApapter(R.layout.item_book_out_view,itemList,getContext());

        binding.bookOutRCV.setAdapter(apapter);
        binding.bookOutRCV.setLayoutManager(new GridLayoutManager(this.getContext(),5));
        apapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (old_view != null){
                    old_view.setBackgroundColor(Color.parseColor("#08000000"));
                }
                old_view = view;
                view.setBackgroundColor(Color.parseColor("#FFA726"));
                Item item = (Item) adapter.getData().get(position);
                mViewModel.setIconId(item.getId());
            }
        });
        binding.tvDesc.addTextChangedListener(this);
    }



    @Override
    protected void setListener() {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        mViewModel.setmType(editable.toString());
    }
}