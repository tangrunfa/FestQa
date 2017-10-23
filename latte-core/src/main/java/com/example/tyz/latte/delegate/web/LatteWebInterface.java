package com.example.tyz.latte.delegate.web;


import com.alibaba.fastjson.JSON;
import com.example.tyz.latte.delegate.web.event.Event;
import com.example.tyz.latte.delegate.web.event.EventManager;

/**
 * Created by TYZ on 2017/10/19.
 */

public class LatteWebInterface {
    private final WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    public static LatteWebInterface create(WebDelegate delegate){
        return new LatteWebInterface(delegate);
    }
    public String envert(String params){
        final String action= JSON.parseObject(params).getString("action");
        final Event event= EventManager.getInstance().createEvent(action);
        if (event!=null){
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.excuute(params);

        }
        return null;
    }
}
