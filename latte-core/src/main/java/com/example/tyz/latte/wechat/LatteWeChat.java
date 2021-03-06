package com.example.tyz.latte.wechat;

import android.app.Activity;

import com.example.tyz.latte.app.ConfigType;

import com.example.tyz.latte.app.Latte;
import com.example.tyz.latte.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2017/10/13.
 */

public class LatteWeChat {
    public static final String APP_ID = Latte.getConfiguration(ConfigType.WE_CHAT_APP_ID.name());
    public static final String APP_SECRET = Latte.getConfiguration(ConfigType.WE_CHAT_APP_SECRET.name());
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback=null;

    private static final class Holder {
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }

    public static LatteWeChat getInstance() {
        return Holder.INSTANCE;
    }

    private LatteWeChat() {
        final Activity activity = Latte.getConfiguration(ConfigType.ACTIVITY.name());
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }
    public LatteWeChat onSignSuccess(IWeChatSignInCallback callback) {
        this.mSignInCallback = callback;
        return this;
    }
    public IWeChatSignInCallback getSignInCallback(){
        return mSignInCallback;
    }
    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
