package com.example.finaltsest.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.example.finaltsest.MainActivity;
import com.example.finaltsest.R;
import com.example.finaltsest.viewModel.RegisterViewModel;
import com.example.finaltsest.bean.User;
import com.example.finaltsest.databinding.ActivityRegisterBinding;
import com.example.finaltsest.utils.ActivitysBuilder;
import com.example.finaltsest.utils.ToastUtils;

public class RegisterActivity extends BaseActivity<RegisterViewModel, ActivityRegisterBinding> implements TextWatcher {



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
            register();
        }
    }

    public void register(){
        User user = new User(getStringByUI(bingding.Username),getStringByUI(bingding.Password));
        int flag =mViewModel.Register(user);
        switch (flag){
            case 0:
                ToastUtils.showToast("注册成功");
                ActivitysBuilder.build(RegisterActivity.this, MainActivity.class).startActivity();
                break;
            case 1:
                ToastUtils.showToast("该用户名已被注册");
                break;
            case 2:
                ToastUtils.showToast("注册失败，未知错误");
                break;
            default:
                break;
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