package com.example.tyz.festqa.event;

import android.webkit.WebView;
import android.widget.Toast;

import com.example.tyz.latte.delegate.web.event.Event;

/**
 *
 */

public class TestEvent extends Event {

    @Override
    public String excuute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
