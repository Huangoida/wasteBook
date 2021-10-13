package com.example.finaltsest.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.finaltsest.R;
import com.example.finaltsest.adapter.BookOutRecycleVIewApapter;
import com.example.finaltsest.bean.Item;
import com.example.finaltsest.bean.WasteBook;
import com.example.finaltsest.viewModel.BookInViewModel;
import com.example.finaltsest.databinding.BookInFragmentBinding;
import com.example.finaltsest.viewModel.BookingViewModel;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import io.itimetraveler.widget.pickerselector.TimeWheelPicker;

public class BookInFragment extends BaseFragment<BookInViewModel, BookInFragmentBinding>  implements TextWatcher {

    private BookOutRecycleVIewApapter apapter;
    private List<Item> itemList = new ArrayList<>();
    private View old_view = null;
    private BookingViewModel bookingViewModel;
    private GridLayoutManager gridLayoutManager;
    @Override
    public void onResume() {
        super.onResume();
        if (mViewModel.getActivityViewModel().getWasteBook() != null){
            if (mViewModel.getActivityViewModel().getWasteBook().getValue()!= null){
                WasteBook wasteBook =  mViewModel.getActivityViewModel().getWasteBook().getValue();
                mViewModel.setmDest(wasteBook.getNote());
                mViewModel.setAmount(wasteBook.getAmount());
                mViewModel.setDate(wasteBook.getTime());
                mViewModel.setAmountText(String.valueOf(wasteBook.getAmount()));
                mViewModel.setIconId(wasteBook.getIcon());
                mViewModel.setmDateText(TimeWheelPicker.DEFAULT_DATE_FORMAT.format(wasteBook.getTime())+" " + TimeWheelPicker.DEFAULT_TIME_FORMAT.format(wasteBook.getTime()));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        View view =gridLayoutManager.findViewByPosition(wasteBook.getIcon());
                        old_view = view;
                        view.setBackgroundColor(Color.parseColor("#FFA726"));
                    }
                },300);
            }
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.book_in_fragment;
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        bookingViewModel = new ViewModelProvider(getActivity()).get(BookingViewModel.class);
        mViewModel.setContext(this.getActivity());
        mViewModel.setActivityViewModel(bookingViewModel);
        if (bookingViewModel.getWasteBook() != null){
            if (bookingViewModel.getWasteBook().getValue() != null){
                mViewModel.setWasteBookEdit(bookingViewModel.getWasteBook().getValue());
            }
        }
        binding.setVm(mViewModel);
        itemList = LitePal.where("tab=?","1").find(Item.class);
        apapter = new BookOutRecycleVIewApapter(R.layout.item_book_out_view,itemList,getContext());
        gridLayoutManager=new GridLayoutManager(this.getContext(),5);
        binding.bookOutRCV.setAdapter(apapter);
        binding.bookOutRCV.setLayoutManager(gridLayoutManager);
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