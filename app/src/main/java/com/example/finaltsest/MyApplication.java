package com.example.finaltsest;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.example.finaltsest.activity.UserLoginActivity;
import com.example.finaltsest.bean.User;
import com.example.finaltsest.utils.ContextUtils;
import com.example.finaltsest.utils.DBUtils;
import com.example.finaltsest.utils.MMKVUtils;
import com.tencent.mmkv.MMKV;

import org.litepal.LitePal;

public class MyApplication extends Application {

    private static User loginUser;
    private static MMKV mv;
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        MMKV.initialize(this);
        ContextUtils.initContext(this);
        DBUtils.getInstance();
    }

    public static User getLoginUser(Context context){
        if (loginUser == null){
            MMKV.initialize(context);
            loginUser = MMKVUtils.getInstance().decodeParcelable("loginUser",User.class);
        }
        return loginUser;
    }
}
