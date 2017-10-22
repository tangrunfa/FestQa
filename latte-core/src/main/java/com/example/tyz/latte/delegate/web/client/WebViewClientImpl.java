package com.example.tyz.latte.delegate.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.example.tyz.latte.app.ConfigType;
import com.example.tyz.latte.app.Latte;
import com.example.tyz.latte.delegate.IPageLoadListener;
import com.example.tyz.latte.delegate.web.WebDelegate;
import com.example.tyz.latte.delegate.web.route.Router;

import com.example.tyz.latte.ui.loader.LatteLoader;
import com.example.tyz.latte.util.storage.LattePreference;

/**
 * Created by TYZ on 2017/10/19.
 */

public class WebViewClientImpl extends WebViewClient {
    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Latte.getHandler();

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }
    public WebViewClientImpl(WebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        return Router.getInstance().handleWebUrl(DELEGATE,url);//所有跳转由原生接管
    }

    //获取浏览器cookie
    private void syncCookie(){
        final CookieManager cookieManager=CookieManager.getInstance();
         /*
          注意，这里的Cookie和API请求的Cookie是不一样的，这个在网页不可见
         */
         final  String webhost=Latte.getConfiguration(ConfigType.WEB_HOST.name());
        if (webhost!=null){
            if (cookieManager.hasCookies()){
                final String cookstr=cookieManager.getCookie(webhost);
                if (cookstr!=null&&!cookstr.equals("")){
                    LattePreference.addCustomAppProfile("cookie",cookstr);
                }
            }
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);
    }
}
