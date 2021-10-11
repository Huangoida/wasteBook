package com.example.finaltsest;

import android.Manifest;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finaltsest.activity.BaseActivity;
import com.example.finaltsest.bean.User;
import com.example.finaltsest.viewModel.MainViewModel;
import com.example.finaltsest.adapter.FragmentAdapter;
import com.example.finaltsest.databinding.ActivityMainBinding;
import com.example.finaltsest.fragment.AssestFragment;
import com.example.finaltsest.fragment.HomeFragment;
import com.example.finaltsest.fragment.MineFragment;
import com.example.finaltsest.utils.AppUtils;
import com.winton.bottomnavigationview.NavigationView;


import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<NavigationView.Model> tabs =new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityPermissionsDispatcher.checkUserIsLoginWithPermissionCheck(this);
        fragmentList.add(new HomeFragment());
        fragmentList.add(new AssestFragment());
        fragmentList.add(new MineFragment());
        bingding.viewPager.setAdapter(new FragmentAdapter(this, fragmentList));
        tabs.add(new NavigationView.Model.Builder(R.mipmap.home_select,R.mipmap.home_normal).title("主页").build());
        tabs.add(new NavigationView.Model.Builder(R.mipmap.assest_select,R.mipmap.assest_normal).title("资产").build());
        tabs.add(new NavigationView.Model.Builder(R.mipmap.mine_selected,R.mipmap.mine_normal).title("我的").build());
        bingding.nv.setItems(tabs);
        bingding.nv.build();
        bingding.nv.setOnTabSelectedListener(new NavigationView.OnTabSelectedListener() {
            @Override
            public void selected(int index, NavigationView.Model model) {
                bingding.viewPager.setCurrentItem(index,true);
            }
            @Override
            public void unselected(int index, NavigationView.Model model) {

            }
        });
        bingding.viewPager.setUserInputEnabled(false);
        User user =MyApplication.getLoginUser(this);
        mViewModel.setLoginUser(user);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void processLogic() {

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