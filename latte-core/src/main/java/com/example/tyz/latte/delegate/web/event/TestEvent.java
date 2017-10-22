package com.example.tyz.latte.delegate.web.event;

import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by TYZ on 2017/10/19.
 */

public class TestEvent extends Event {
    @Override
    public String excuute(String params) {
        Toast.makeText(getContext(),getAction(),Toast.LENGTH_SHORT).show();
        if (getAction().equals("test")){
            final WebView webView=getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativel call!",null);
                }
            });
        }
        return null;
    }
}
