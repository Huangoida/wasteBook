package com.example.finaltsest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.example.finaltsest.R;
import com.example.finaltsest.ViewModel.LoginViewModel;
import com.example.finaltsest.databinding.ActivityRegisterBinding;
import com.example.finaltsest.utils.ToastUtils;

public class RegisterActivity extends BaseActivity<LoginViewModel, ActivityRegisterBinding> implements TextWatcher {


    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void processLogic() {
        bingding.btnRegister.setClickable(false);
        bingding.Username.addTextChangedListener(this);
        bingding.Password.addTextChangedListener(this);
        bingding.setRegisterHandles(new RegisterHandles());
    }

    public class RegisterHandles{
        public void clickRegisterBtn(View view){
            if (TextUtils.isEmpty(getStringByUI(bingding.Username))){
                ToastUtils.showToast("用户名不能为空");
                return;
            }
            if(TextUtils.isEmpty(getStringByUI(bingding.Password))){
                ToastUtils.showToast("密码不能为空");
                return;
            }
            bingding.Username.setFocusable(false);
            bingding.Password.setFocusable(false);

        }
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (!TextUtils.isEmpty(getStringByUI(bingding.Username)) && !TextUtils.isEmpty(getStringByUI(bingding.Password))){
            bingding.btnRegister.setClickable(true);
        } else {
            bingding.btnRegister.setClickable(false);
        }
    }
}