package com.example.tyz.festqa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.example.administrator.latte.ui.launcher.ILauncherListener;
import com.example.administrator.latte.ui.launcher.OnLauncherFinishTag;
import com.example.tyz.latte.activites.ProxyActivity;
import com.example.tyz.latte.app.Latte;
import com.example.tyz.latte.delegate.Lattedelegate;
import com.example.tyz.latte.ec.launcher.LauncherDelegate;
import com.example.tyz.latte.ec.main.EcBottomDelegate;
import com.example.tyz.latte.ec.sign.ISignListener;

import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2017/9/26.
 */

public class ExampleActivity extends ProxyActivity implements ISignListener,ILauncherListener {
    @Override
    public Lattedelegate setRootDelegate() {
        return  new LauncherDelegate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this,true);//沉浸式状态栏
    }



    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"登录成功！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功！",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            default:
                break;

    }
    }
}
