package com.example.finaltsest.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.finaltsest.R;
import com.example.finaltsest.activity.BookingActivity;
import com.example.finaltsest.bean.WasteBook;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogTuil {
    public static void showDialog(Context context, String title, View v) {
        final Context mContext = context;
        v.setPadding(20, 20, 20, 20);

        final Dialog bottomDialog = new Dialog(mContext, R.style.BottomDialog);
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_content_normal, null);

        TextView titleView = contentView.findViewById(R.id.title);
        titleView.setText(title);

        View confrimView = contentView.findViewById(R.id.button_confirm);
        View cancelView = contentView.findViewById(R.id.button_cancel);
        LinearLayout content = contentView.findViewById(R.id.content);
        confrimView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
            }
        });

        cancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
            }
        });
        content.removeAllViews();
        content.addView(v);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.show();
    }

    public static void showWateBookDialog(Context context, WasteBook wasteBook){
        final Context mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_rev,null);
        final Dialog dialog = new Dialog(mContext);
        TextView account = view.findViewById(R.id.dialog_account);
        TextView note_name = view.findViewById(R.id.dialog_note_name);
        TextView note = view.findViewById(R.id.dialog_note);
        if (wasteBook.getType()){
            account.setText("+"+String.valueOf(wasteBook.getAmount()));
            account.setTextColor(Color.parseColor("#1B5E20"));
        }else {
            account.setText("-"+String.valueOf(wasteBook.getAmount()));
            account.setTextColor(Color.parseColor("#F44336"));
        }
        if (!wasteBook.getNote().isEmpty()){
            note_name.setVisibility(View.VISIBLE);
            note.setVisibility(View.VISIBLE);
            note.setText(wasteBook.getNote());
        }
        Date date =new Date(wasteBook.getTime());
        SimpleDateFormat sd= new SimpleDateFormat("YYYY年MM月dd日 hh:mm:ss");
        String time= sd.format(date);
        TextView timeTv = view.findViewById(R.id.dialog_date);
        timeTv.setText(time);

        TextView delete = view.findViewById(R.id.dialog_delete);
        TextView edit = view.findViewById(R.id.dialog_edit);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                alertDialog.setMessage("确定删除这条记录?");
                alertDialog.setTitle("删除");
                alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LitePal.delete(WasteBook.class,wasteBook.getId());
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ActivitysBuilder.build(mContext, BookingActivity.class).putExtra("wasteBookId",wasteBook.getId()).startActivity();
            }
        });
        dialog.setContentView(view);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        dialog.getWindow().setLayout(getScreenWidth(mContext),getScreenHeight(mContext)/4);
    }
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
