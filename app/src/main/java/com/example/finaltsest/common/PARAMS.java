package com.example.finaltsest.common;

import java.util.HashMap;

public class PARAMS {

    public static HashMap<String,Object> login(String username,String password){
        HashMap<String,Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        return map;
    }

}
