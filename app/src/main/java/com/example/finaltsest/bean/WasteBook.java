package com.example.finaltsest.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class WasteBook extends LitePalSupport {

    private int id;

    private long userId;

    //false 支出 true收入
    private boolean type;

    private double amount;

    private int icon;

    //备注
    private String note;

    private long time;

    public WasteBook(long userId, boolean type, double amount, int icon, String note, long time) {
        this.userId = userId;
        this.type = type;
        this.amount = amount;
        this.icon = icon;
        this.note = note;
        this.time = time;
    }

    public WasteBook() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


}
