package com.example.finaltsest.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    private static Context mContext=ContextUtils.getApplication();

    private static Toast toast = null;



    public static void showToast(String message) {
        if (toast == null) {
            toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast.cancel();
            toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
