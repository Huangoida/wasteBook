package com.example.finaltsest.utils;

import android.database.sqlite.SQLiteDatabase;

import org.litepal.LitePal;

public class DBUtils {
    private static SQLiteDatabase db;
    public static SQLiteDatabase getInstance(){
        if (db == null){
            synchronized (DBUtils.class){
                if (db == null){
                    db = LitePal.getDatabase();
                }
            }
        }
        return db;
    }
}
