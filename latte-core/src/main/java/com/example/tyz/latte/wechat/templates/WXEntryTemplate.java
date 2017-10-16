package com.example.tyz.latte.wechat.templates;

import com.example.tyz.latte.wechat.BaseWXEntryActivity;
import com.example.tyz.latte.wechat.LatteWeChat;

/**
 * Created by Administrator on 2017/10/12.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {
    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
