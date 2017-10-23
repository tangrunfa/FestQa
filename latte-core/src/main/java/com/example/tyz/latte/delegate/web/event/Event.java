package com.example.tyz.latte.delegate.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.example.tyz.latte.delegate.Lattedelegate;
import com.example.tyz.latte.delegate.web.WebDelegate;

/**
 * Created by TYZ n 2017/10/19.
 */

public abstract class Event implements IEvent {
    private Context mContext=null;
    private WebDelegate mDelegate=null;
    private String mAction=null;
    private String mUrl=null;

    public WebView getWebView(){
        return mDelegate.getWebView();
    }

    private WebView mWebView = null;
    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public Lattedelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
