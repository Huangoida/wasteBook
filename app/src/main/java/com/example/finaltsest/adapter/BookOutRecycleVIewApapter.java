package com.example.finaltsest.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.finaltsest.R;
import com.example.finaltsest.bean.Item;

import java.lang.reflect.Field;
import java.util.List;

public class BookOutRecycleVIewApapter extends BaseQuickAdapter<Item, BaseViewHolder> {

    Class drawable = R.drawable.class;
    private Context context;

    public BookOutRecycleVIewApapter(int layoutResId, @Nullable List<Item> data,Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, Item item) {
        baseViewHolder.setText(R.id.item_TV,item.getName());
        try {
            Field field = drawable.getField(item.getIconName());
            int resID = field.getInt(item.getIconName());
            baseViewHolder.setImageDrawable(R.id.item_IV,ContextCompat.getDrawable(context,resID));

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
