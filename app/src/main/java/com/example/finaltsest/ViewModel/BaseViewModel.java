package com.example.finaltsest.ViewModel;

import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {
    public BaseViewModel() {
        super();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
