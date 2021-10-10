package com.example.finaltsest.ViewModel;

import com.example.finaltsest.bean.User;
import com.example.finaltsest.utils.DBUtils;
import com.example.finaltsest.utils.MMKVUtils;
import com.example.finaltsest.utils.ToastUtils;

import org.litepal.LitePal;

import java.util.List;

public class RegisterViewModel extends BaseViewModel{

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
