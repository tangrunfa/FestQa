package com.example.tyz.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.example.tyz.latte.app.Latte;

/**
 * Created by Administrator on 2017/10/16.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout){
        this.REFRESH_LAYOUT=swipeRefreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private  void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
            refresh();
    }
}
