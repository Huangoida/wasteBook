package com.example.finaltsest.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.finaltsest.bean.User;
import com.example.finaltsest.utils.MMKVUtils;

import org.litepal.LitePal;

import java.util.List;

public class RegisterViewModel extends BaseViewModel{

    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }

    public int Register(User user){
        List<User> romUsers=LitePal.where("username =?",user.getUsername()).find(User.class);
        if (romUsers.size() >0){
            return 1;
        }
        boolean flag = user.save();
        if (flag){
            MMKVUtils.getInstance().encode("loginUser",user);
            return 0;
        }else {
            return 2;
        }
    }

}
