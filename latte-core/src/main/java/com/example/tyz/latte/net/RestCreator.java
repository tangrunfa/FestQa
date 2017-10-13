package com.example.tyz.latte.net;

import com.example.tyz.latte.app.ConfigType;
import com.example.tyz.latte.app.latte;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/9/25.
 */

public class RestCreator {

    /**
     * 参数容器
     */
    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }
    /**
     * 构建全局Retrofit客户端
     */
    private  static final class Retrofitholder{
        private static final String BASE_URL = latte.getConfiguration(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OKhttpHolder().OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    /**
     * 构建OkHttp
     */
    private  static  final class  OKhttpHolder{
        private static final int TIME_OUT = 60;

        private static final OkHttpClient.Builder BUILDER=new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS=latte.getConfiguration(ConfigType.INTERCEPTOR.name());

        private static final OkHttpClient.Builder addInterceptor(){
            if (INTERCEPTORS!=null&&!INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor:INTERCEPTORS
                     ) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT=addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
    private  static  final class  RestServiceHolder{

        private static final RestService REST_SERVICE
                =new Retrofitholder().RETROFIT_CLIENT.create(RestService.class);
    }


    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }
}
