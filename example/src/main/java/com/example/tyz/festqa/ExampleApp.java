package com.example.tyz.festqa;

import android.app.Application;
import android.support.annotation.Nullable;

import com.example.tyz.festqa.event.ShareEvent;
import com.example.tyz.latte.app.Latte;
import com.example.tyz.latte.delegate.web.event.TestEvent;
import com.example.tyz.latte.ec.database.DatabaseManager;
import com.example.tyz.latte.ec.icon.FontEcModul;
import com.example.tyz.latte.net.interceptor.DebugInterceptor;
import com.example.tyz.latte.net.rx.AddCookieInterceptor;
import com.example.tyz.latte.util.callback.CallbackManager;
import com.example.tyz.latte.util.callback.CallbackType;
import com.example.tyz.latte.util.callback.IGlobalCallback;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2017/9/20.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate(){

        super.onCreate();
        try {
            Latte.init(this).withIcon(new FontAwesomeModule()).withIcon(new FontEcModul())
                    .withApiHost("http:127.0.0.1/")
                    .withInterceptor(new DebugInterceptor("test", R.raw.test))
                    .withJavascriptInterface("latte")
                    .withWeChatAppId("")
                    .withJavascriptInterface("latte")
                    .withWebEvent("test", new TestEvent())
                    //添加cookie 同步的拦截器
                    .withWebHost("http://www.baidu.com/")
                    .withInterceptor(new AddCookieInterceptor())
                    .withWeChatAppSecret("")
                    .withWebEvent("test", new TestEvent())
                    .withWebEvent("share", new ShareEvent())
                   .congfigure();
            DatabaseManager.getInstance().init(this);
            initStetho();


            //开启极光推送
            JPushInterface.setDebugMode(true);
            JPushInterface.init(this);

            CallbackManager.getInstance()
                    .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                        @Override
                        public void executeCallback(@Nullable Object args) {
                            if (JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                                //开启极光推送
                                JPushInterface.setDebugMode(true);
                                JPushInterface.init(Latte.getApplicationContext());
                            }
                        }
                    })
                    .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                        @Override
                        public void executeCallback(@Nullable Object args) {
                            if (!JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                                JPushInterface.stopPush(Latte.getApplicationContext());
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
