package com.example.finaltsest.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.finaltsest.bean.User;
import com.example.finaltsest.utils.MMKVUtils;

import org.litepal.LitePal;

import java.util.List;

public class LoginViewModel extends BaseViewModel{

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public User login(String username, String password){

        List<User> loginUser = LitePal.where("username=? and password=?",username,password).find(User.class);
        if (loginUser.size() == 0){
            return  null;
        }else {
            MMKVUtils.getInstance().encode("loginUser",loginUser.get(0));
            return loginUser.get(0);
        }
    }
}
