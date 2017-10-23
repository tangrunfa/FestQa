package com.example.tyz.latte.net.callback;

import android.os.Handler;


import com.example.tyz.latte.ui.loader.LatteLoader;
import com.example.tyz.latte.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Administrator on 2017/9/25.
 */

public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;

    public RequestCallbacks(IRequest request, ISucces succes, IFailure failure, IError error,LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = succes;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE=style;
    }

    private final ISucces SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;


    private static final Handler HANDLER=new Handler();

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESS!=null){
                    SUCCESS.onSuccess(response.body());
                }
            }
            else {
                if (ERROR!=null){
                    ERROR.onError(response.code(),response.message());
                }
            }
            if (LOADER_STYLE!=null){
                HANDLER.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LatteLoader.stopshowDailog();
                    }
                },1000);
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable throwable) {
        if (FAILURE!=null){
            FAILURE.onFailure();
        }
        if (REQUEST!=null){
            REQUEST.onRequestEnd();
        }
    }
}
