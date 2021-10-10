package com.example.finaltsest;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.example.finaltsest.activity.UserLoginActivity;
import com.example.finaltsest.bean.User;
import com.example.finaltsest.utils.ContextUtils;
import com.tencent.mmkv.MMKV;

public class MyApplication extends Application {

    private static User loginUser;
    private static MMKV mv;
    @Override
    public void onCreate() {
        super.onCreate();
        MMKV.initialize(this);
        ContextUtils.initContext(this);
    }

    public static User getLoginUser(Context context){
        if (loginUser == null){
            MMKV.initialize(context);
            mv =MMKV.defaultMMKV();
            loginUser = mv.decodeParcelable("user",User.class);
        }
        return loginUser;
    }
}
