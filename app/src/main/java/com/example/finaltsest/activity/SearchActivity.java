package com.example.finaltsest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.example.finaltsest.R;
import com.example.finaltsest.adapter.HomeRecycleVIewAdapter;
import com.example.finaltsest.bean.Item;
import com.example.finaltsest.bean.WasteBook;
import com.example.finaltsest.databinding.ActivitySearchBinding;
import com.example.finaltsest.utils.ToastUtils;
import com.example.finaltsest.viewModel.BaseViewModel;
import com.example.finaltsest.viewModel.SearchViewModel;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity<SearchViewModel, ActivitySearchBinding> {

    List<WasteBook> wasteBooks = new ArrayList<>();
    private HomeRecycleVIewAdapter homeRecycleVIewAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    protected void processLogic() {

        homeRecycleVIewAdapter = new HomeRecycleVIewAdapter(R.layout.item_home_view,wasteBooks,this);
        bingding.searchRev.setAdapter(homeRecycleVIewAdapter);
        bingding.searchRev.setLayoutManager(new LinearLayoutManager(this));
        bingding.search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH){
                    List<Item> itemList = LitePal.where("name=?", String.valueOf(textView.getText())).find(Item.class);
                    if (itemList.size() > 0){
                        for (Item item : itemList) {
                            List<WasteBook> wasteBookList =LitePal.where("icon=?", String.valueOf(item.getId())).find(WasteBook.class);
                            wasteBooks.addAll(wasteBookList);
                        }
                    }
                    List<WasteBook> noteList = LitePal.where("note=?",String.valueOf(textView.getText())).find(WasteBook.class);
                    if (noteList.size() >0){
                        if (itemList.size() > 0){
                            for (WasteBook wasteBook : noteList) {
                                for (WasteBook waste: wasteBooks) {
                                    if (wasteBook.getId() != waste.getId()){
                                        wasteBooks.add(wasteBook);
                                    }
                                }
                            }
                        }else {
                            wasteBooks.addAll(noteList);
                        }

                    }
                    homeRecycleVIewAdapter.setList(wasteBooks);
                }
                return false;
            }
        });
    }


}