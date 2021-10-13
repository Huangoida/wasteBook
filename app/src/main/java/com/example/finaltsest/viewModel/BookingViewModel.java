package com.example.finaltsest.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.finaltsest.bean.User;
import com.example.finaltsest.bean.WasteBook;

public class BookingViewModel extends BaseViewModel{

    public MutableLiveData<User> loginUser;
    private MutableLiveData<WasteBook> wasteBook;

    public void setLoginUser(MutableLiveData<User> loginUser) {
        this.loginUser = loginUser;
    }

    public MutableLiveData<WasteBook> getWasteBook() {
        return wasteBook;
    }

    public void setWasteBook(WasteBook wasteBook) {
        this.wasteBook =new MutableLiveData<>();
        this.wasteBook.setValue(wasteBook);
    }

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
