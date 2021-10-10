package com.example.finaltsest.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class ActivitysBuilder {

    private Context context;
    private Class<? extends Activity> clz;
    private Intent intent;
    private boolean finish;
    private Fragment fragment;

    public ActivitysBuilder(Context context,Class<? extends Activity> clz){
        this.context = context;
        this.clz = clz;
        intent = new Intent(context,clz);
    }

    public ActivitysBuilder(Fragment fragment, Class<? extends Activity> clz) {
        this.context = fragment.getActivity();
        this.clz = clz;
        this.fragment = fragment;
        intent = new Intent(context, clz);
    }

    public static ActivitysBuilder build(Context context, Class<? extends Activity> clz) {
        return new ActivitysBuilder(context, clz);
    }


    public static ActivitysBuilder build(Fragment fragment, Class<? extends Activity> clz) {
        return new ActivitysBuilder(fragment, clz);
    }

    //是否关闭当前页面
    public ActivitysBuilder finish(boolean finish) {
        this.finish = finish;
        return this;
    }

    public void startActivity() {
        context.startActivity(intent);
        if (finish) {
            ((Activity) context).finish();
        }
    }


}
