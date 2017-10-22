package com.example.tyz.latte.net;

import android.content.Context;

import com.example.tyz.latte.net.callback.IError;
import com.example.tyz.latte.net.callback.IFailure;
import com.example.tyz.latte.net.callback.IRequest;
import com.example.tyz.latte.net.callback.ISucces;
import com.example.tyz.latte.ui.loader.LoaderStyle;


import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/9/25.
 */


public final class RestClientBuilder {
    /**
     * Created by Administrator on 2017/9/25.
     */


    private String mUrl = null;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest = null;
    private ISucces mISucces = null;
    private IError mIError = null;
    private IFailure mIFailure = null;
    private RequestBody mBody = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {
    }


    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }
//    public final RestClientBuilder params(wMap<String,Object> params){
//        PARAMS.putAll(params);
//        return  this;
//    }
//    public final RestClientBuilder params(String key, Object value){
//        if (mParams==null){
//            mParams=new WeakHashMap<>();
//        }
//        this.mParams.put(key,value);
//        return  this;
//    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }


    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder succes(ISucces ISucces) {
        this.mISucces = ISucces;
        return this;
    }

    public final RestClientBuilder failure(IFailure IFailure) {
        this.mIFailure = IFailure;
        return this;
    }

    public final RestClientBuilder error(IError IError) {
        this.mIError = IError;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest IRequest) {
        this.mIRequest = IRequest;
        return this;
    }

    //    public  Map<String,Object> checkparam(){
//        if (mParams==null){
//            return  new WeakHashMap<>();
//        }
//        return mParams;
//    }
    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mDownloadDir, mExtension, mName, mIRequest, mISucces,
                mIFailure, mIError, mBody, mFile, mLoaderStyle, mContext);
    }

}
