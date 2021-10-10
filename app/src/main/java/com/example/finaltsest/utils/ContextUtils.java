package com.example.finaltsest.utils;

import android.content.Context;

public class ContextUtils {

    private static Context mContext;

    public static void initContext(Context context) {
        mContext = context;}

    public static Context getApplication() {
        if (mContext!=null){
            return mContext;
        }else {
            throw new UnsupportedOperationException("context 未初始化");
        }
    }

}
