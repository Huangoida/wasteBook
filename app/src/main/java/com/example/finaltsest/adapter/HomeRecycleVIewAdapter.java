package com.example.finaltsest.adapter;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.finaltsest.R;
import com.example.finaltsest.bean.Item;
import com.example.finaltsest.bean.WasteBook;

import org.litepal.LitePal;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomeRecycleVIewAdapter extends BaseQuickAdapter<WasteBook, BaseViewHolder> {


    Class drawable = R.drawable.class;
    private Context context;

    public HomeRecycleVIewAdapter(int layoutResId, @Nullable List<WasteBook> data,Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, WasteBook s) {
        try {
            Item item = LitePal.where("id=?", String.valueOf(s.getIcon())).findFirst(Item.class);
            Field field = drawable.getField(item.getIconName());
            int resID = field.getInt(item.getIconName());
            baseViewHolder.setImageDrawable(R.id.home_icon_IV, ContextCompat.getDrawable(context,resID));
            baseViewHolder.setText(R.id.home_iconTitle_TV,item.getName());
            Date date = new Date(s.getTime());
            SimpleDateFormat sd = new SimpleDateFormat("MM-dd");
            String time =sd.format(date);
            if (s.getNote() != null | !s.getNote().isEmpty()){
                time= time +" | " + s.getNote();
            }
            baseViewHolder.setText(R.id.home_time_TV,time);
            if (s.getType()){
                baseViewHolder.setText(R.id.home_acount_TV,"+"+String.valueOf(s.getAmount()));
                baseViewHolder.setTextColor(R.id.home_acount_TV, Color.parseColor("#1B5E20"));
            }else {
                baseViewHolder.setText(R.id.home_acount_TV,"-"+String.valueOf(s.getAmount()));
                baseViewHolder.setTextColor(R.id.home_acount_TV, Color.parseColor("#F44336"));
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
