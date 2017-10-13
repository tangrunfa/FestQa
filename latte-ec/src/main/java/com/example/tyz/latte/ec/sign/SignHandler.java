package com.example.tyz.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.tyz.latte.app.AccountManager;
import com.example.tyz.latte.ec.database.DatabaseManager;
import com.example.tyz.latte.ec.database.UserProfile;

/**
 * Created by Administrator on 2017/10/11.
 */

public class SignHandler {

    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(userProfile);
        //已经注册并成功登录了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(userProfile);
        //已经注册并成功登录了
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }
}
