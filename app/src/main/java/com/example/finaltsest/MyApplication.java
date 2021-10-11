package com.example.finaltsest;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.example.finaltsest.activity.UserLoginActivity;
import com.example.finaltsest.bean.Item;
import com.example.finaltsest.bean.User;
import com.example.finaltsest.utils.ContextUtils;
import com.example.finaltsest.utils.DBUtils;
import com.example.finaltsest.utils.MMKVUtils;
import com.tencent.mmkv.MMKV;

import org.litepal.LitePal;

import java.util.List;

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
        List<Item> itemList =  LitePal.findAll(Item.class);
        if (itemList.size() == 0){
            initDataBase();
        }
    }

    public void initDataBase(){
        String[] name = {"餐饮","休闲玩乐","购物","穿搭美容","水果零食","交通","生活日用","人情社交",
                "运动","生活服务","买菜","住房","爱车","学习","网络虚拟","烟酒","医疗保健","金融保险","家居家电","酒店旅行","转账","公益"};
        String[] iconName = {"canyin","xiuxianyule","gouwuchekong","yifu","shuiguo","gongjiao","iconfontshenghuojiaofei","shejiao"
                ,"yundonghuwaileimufill","shenghuofuwu","maicai","zhufang","chuzu","xuexi","youxitianchong","wine"
                ,"yaopin","licai","jiaju","jiudian","weibiaoti5","gongyi"};
        for (int i = 0; i < name.length; i++) {
            Item item = new Item();
            item.setName(name[i]);
            item.setIconName(iconName[i]);
            item.setId(i);
            item.setTab(0);
            item.save();
        }
    }

    public static User getLoginUser(Context context){
        if (loginUser == null){
            MMKV.initialize(context);
            loginUser = MMKVUtils.getInstance().decodeParcelable("loginUser",User.class);
        }
        return loginUser;
    }
}
