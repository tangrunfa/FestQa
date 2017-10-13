package com.example.tyz.festqa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.example.tyz.latte.activites.ProxyActivity;
import com.example.tyz.latte.app.latte;
import com.example.tyz.latte.delegate.Lattedelegate;
import com.example.tyz.latte.ec.launcher.LauncherDelegate;
import com.example.tyz.latte.ec.sign.ISignListener;
import com.example.tyz.latte.ec.sign.SignInDelegate;
import com.example.tyz.latte.ui.launcher.ILauncherListener;
import com.example.tyz.latte.ui.launcher.OnLauncherFinishTag;

/**
 * Created by Administrator on 2017/9/26.
 */

public class ExampleActivity extends ProxyActivity implements ISignListener,ILauncherListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        latte.getConfigurator().withActivity(this);
    }

    @Override
    public Lattedelegate setrootDelegate() {
        return new LauncherDelegate();
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
                startWithPop(new exampleDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;

    }
    }
}
