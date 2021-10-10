package com.example.finaltsest.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import androidx.lifecycle.LiveData;

import com.example.finaltsest.MainActivity;
import com.example.finaltsest.R;
import com.example.finaltsest.ViewModel.LoginViewModel;
import com.example.finaltsest.bean.User;
import com.example.finaltsest.databinding.ActivityUserLoginBinding;
import com.example.finaltsest.utils.ActivitysBuilder;
import com.example.finaltsest.utils.ToastUtils;

public class UserLoginActivity extends BaseActivity<LoginViewModel, ActivityUserLoginBinding> implements TextWatcher {



    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_login;
    }

    @Override
    protected void processLogic() {
        bingding.btnLogin.setClickable(false);
        bingding.Username.addTextChangedListener(this);
        bingding.Password.addTextChangedListener(this);
        bingding.setLoginHandler(new LoginHandles());

    }

    public class LoginHandles {
        public void clickLoginBtn(View view){
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
            login();
        }

        public void clickRegister(View view){
            ActivitysBuilder.build(UserLoginActivity.this,RegisterActivity.class)
                    .startActivity();
        }
    }

    public void login(){
        String username = getStringByUI(bingding.Username);
        String password = getStringByUI(bingding.Password);
        User user =mViewModel.login(username,password);
        if (user == null){
            ToastUtils.showToast("用户名或密码错误");
            return;
        }
        ActivitysBuilder.build(UserLoginActivity.this, MainActivity.class).putExtra("username",user).startActivity();
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
            bingding.btnLogin.setClickable(true);
        } else {
            bingding.btnLogin.setClickable(false);
        }
    }
}