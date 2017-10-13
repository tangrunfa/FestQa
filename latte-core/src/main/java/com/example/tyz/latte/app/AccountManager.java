package com.example.tyz.latte.app;

import com.example.tyz.latte.util.storage.LattePreference;

/**
 * Created by Administrator on 2017/10/11.
 */

public class AccountManager {
    private enum SignTag {
        SIGN_TAG
    }
    //保存用户登录状态，登录后调用
    public  static void setSignState(Boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }
    public  static  Boolean IsSignIn(){
        return  LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }
    public  static void checkAccount(IUserChecker checker){
            if (IsSignIn()){
                checker.onSignIn();
            }else {
                checker.onNotSignIn();
            }
    }
}
