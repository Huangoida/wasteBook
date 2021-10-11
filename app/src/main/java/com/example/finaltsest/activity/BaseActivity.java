package com.example.finaltsest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;


import com.example.finaltsest.viewModel.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity <VM extends BaseViewModel,VDB extends ViewDataBinding> extends AppCompatActivity {

    protected abstract int getContentViewId();

    protected abstract void processLogic();

    protected VM mViewModel;
    protected VDB bingding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        bingding = DataBindingUtil.setContentView(this,getContentViewId());
        bingding.setLifecycleOwner(this);

        createViewModel();
        processLogic();
    }

    public void createViewModel(){
        if (mViewModel == null){
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if(type instanceof ParameterizedType){
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            }else {
                modelClass = BaseViewModel.class;
            }
            mViewModel = (VM) new ViewModelProvider(this).get(modelClass);
        }
    }

    public String getStringByUI(View view){
        if (view instanceof EditText) {
            return ((EditText) view).getText().toString().trim();
        } else if (view instanceof TextView) {
            return ((TextView) view).getText().toString().trim();
        }
        return "";
    }
}
