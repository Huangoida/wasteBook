package com.example.finaltsest.fragment;

import android.os.Bundle;

import com.example.finaltsest.R;
import com.example.finaltsest.viewModel.BookInViewModel;
import com.example.finaltsest.databinding.BookInFragmentBinding;

public class BookInFragment extends BaseFragment<BookInViewModel, BookInFragmentBinding> {


    @Override
    protected int getContentViewId() {
        return R.layout.book_in_fragment;
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }
}