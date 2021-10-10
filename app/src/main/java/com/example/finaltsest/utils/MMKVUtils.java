package com.example.finaltsest.utils;

import com.tencent.mmkv.MMKV;

public class MMKVUtils {

    private static MMKV mmkv;
    public static MMKV getInstance(){
        if (mmkv == null){
            synchronized (MMKVUtils.class){
                if (mmkv == null){
                    mmkv = MMKV.defaultMMKV();
                }
            }
        }
        return mmkv;
    }
}
