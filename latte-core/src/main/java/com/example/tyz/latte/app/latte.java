package com.example.tyz.latte.app;

import android.content.Context;

/**
 * Created by Administrator on 2017/9/20.
 */

public final class latte {

//    public static Configurator init(Context context){
//        getconfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
//        return Configurator.getInstance();
//    }
//    private static HashMap<String,Object> getconfigurations(){
//        return  Configurator.getInstance().getLatteConfigs();
//    }
//    public  static  Context getApplication(){
//        return (Context) getconfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
//    }

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getconfigurations(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigType.APPLICATION_CONTEXT.name());
    }
}
