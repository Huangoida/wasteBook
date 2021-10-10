package com.example.finaltsest.ViewModel;

import androidx.lifecycle.LiveData;

import com.example.finaltsest.bean.User;
import com.example.finaltsest.utils.MMKVUtils;
import com.tencent.mmkv.MMKV;

import org.litepal.LitePal;

import java.util.HashMap;
import java.util.List;

public class LoginViewModel extends BaseViewModel{

    public User login(String username,String password){

        List<User> loginUser = LitePal.where("username=? and password=?",username,password).find(User.class);
        if (loginUser.size() == 0){
            return  null;
        }else {
            MMKVUtils.getInstance().encode("loginUser",loginUser.get(0));
            return loginUser.get(0);
        }
    }
}
