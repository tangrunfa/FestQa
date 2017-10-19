package com.example.tyz.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tyz.latte.app.Latte;
import com.example.tyz.latte.net.RestClient;
import com.example.tyz.latte.net.callback.ISucces;
import com.example.tyz.latte.ui.recycler.DataConverter;
import com.example.tyz.latte.ui.recycler.MultipleRecyclerAdapter;

/**
 * Created by Administrator on 2017/10/16.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter=null;
    private final DataConverter CONVERTER;

    private RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                           RecyclerView recyclerView,
                           DataConverter converter, PagingBean bean) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }


    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                           RecyclerView recyclerView,
                           DataConverter converter) {
        return  new RefreshHandler(swipeRefreshLayout,recyclerView,converter,new PagingBean());
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

    public void fristPage(String url){
        BEAN.setmDelayed(1000);
        RestClient.builder()
                .url("http://117.48.205.138/RestServer/api/" +url)
                .succes(new ISucces() {
                    @Override
                    public void onSuccess(String response) {
                    final JSONObject object= JSON.parseObject(response);
                        BEAN.setmToatal(object.getInteger("total"))
                                .setmPageSize(object.getInteger("page_size"));
                        //设置adapter
                        mAdapter=MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
//                        Toast.makeText(Latte.getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }
    @Override
    public void onRefresh() {
            refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
