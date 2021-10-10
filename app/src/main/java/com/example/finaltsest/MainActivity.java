package com.example.finaltsest;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.finaltsest.activity.BaseActivity;
import com.example.finaltsest.activity.UserLoginActivity;
import com.example.finaltsest.ViewModel.MainViewModel;
import com.example.finaltsest.databinding.ActivityMainBinding;
import com.example.finaltsest.utils.AppUtils;
import com.tencent.mmkv.MMKV;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void processLogic() {
        MainActivityPermissionsDispatcher.checkUserIsLoginWithPermissionCheck(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    void checkUserIsLogin(){
        AppUtils.isLogin(this);
    }


    @OnShowRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
    void showRationaleForRead(final PermissionRequest request){
        new AlertDialog.Builder(this)
                .setMessage("need read permission")
                .setPositiveButton("Yes",((dialogInterface, i) -> request.proceed()))
                .setNegativeButton("No",((dialogInterface, i) -> request.cancel()))
                .show();
    }

    @OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE)
    void showDeniedForRead(){
        Toast.makeText(this, "permission_camera_denied", Toast.LENGTH_SHORT).show();
    }

}