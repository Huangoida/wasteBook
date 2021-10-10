package com.example.finaltsest.utils;

import android.content.Context;

import com.example.finaltsest.MyApplication;
import com.example.finaltsest.activity.UserLoginActivity;

public class AppUtils {
    //判断当前app是否登录，没有登录直接跳转到LoginActivity页面
    public static boolean isLogin(Context context) {
        if (MyApplication.getLoginUser(context) == null) {
            ActivitysBuilder.build(context, UserLoginActivity.class)
                    .startActivity();
            return false;
        } else {
            return true;
        }
    }
}
