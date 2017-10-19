package com.example.tyz.latte.delegate.web.event;

import com.example.tyz.latte.util.log.LatteLogger;

/**
 * Created by TYZ on 2017/10/19.
 */

public class UndefineEvent extends Event {
    @Override
    public String excuute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
