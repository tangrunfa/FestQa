package com.example.tyz.latte.delegate.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tyz.latte.delegate.IPageLoadListener;
import com.example.tyz.latte.delegate.web.chromeclient.WebChromeClientImpl;
import com.example.tyz.latte.delegate.web.client.WebViewClientImpl;
import com.example.tyz.latte.delegate.web.route.RouteKeys;
import com.example.tyz.latte.delegate.web.route.Router;

/**
 * Created by TYZ on 2017/10/19.
 */

public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener mIPageLoadListener = null;

    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl webDelegate = new WebDelegateImpl();
        webDelegate.setArguments(args);
        return webDelegate;
    }

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }
    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {
        if (getUrl() != null) {
            //用原生的方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webViewClient.setPageLoadListener(mIPageLoadListener);
        return webViewClient;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }


}
