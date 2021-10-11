package com.example.finaltsest.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.finaltsest.bean.User;

public class BookingViewModel extends BaseViewModel{

    public MutableLiveData<User> loginUser;

    public BookingViewModel(@NonNull Application application) {
        super(application);
        loginUser = new MutableLiveData<>();
    }

    public MutableLiveData<User> getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser.setValue(loginUser);
    }
}
