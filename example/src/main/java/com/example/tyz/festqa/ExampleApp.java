package com.example.tyz.festqa;

import android.app.Application;

import com.example.tyz.latte.app.latte;
import com.example.tyz.latte.ec.database.DatabaseManager;
import com.example.tyz.latte.ec.icon.FontEcModul;
import com.example.tyz.latte.net.interceptor.DebugInterceptor;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;


/**
 * Created by Administrator on 2017/9/20.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate(){

        super.onCreate();
        try {
            latte.init(this).withIcon(new FontAwesomeModule()).withIcon(new FontEcModul())
                    .withApiHost("http:127.0.0.1/")
                    .withInterceptor(new DebugInterceptor("test", R.raw.test))
                    .withWeChatAppId("")
                    .withWeChatAppSecret("")
                   .congfigure();
            DatabaseManager.getInstance().init(this);
            initStetho();
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
