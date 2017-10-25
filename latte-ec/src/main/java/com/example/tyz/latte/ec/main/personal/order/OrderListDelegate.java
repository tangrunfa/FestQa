package com.example.tyz.latte.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.latte.ui.recycler.MultipleItemEntity;
import com.example.tyz.latte.delegate.Lattedelegate;
import com.example.tyz.latte.ec.R;
import com.example.tyz.latte.ec.R2;
import com.example.tyz.latte.ec.main.personal.PersonalDelegate;
import com.example.tyz.latte.net.RestClient;
import com.example.tyz.latte.net.callback.ISucces;

import java.util.List;

import butterknife.BindView;

/**
 * Created by TYZ on 2017/10/25.
 */

public class OrderListDelegate extends Lattedelegate {
    private String mType = null;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args=getArguments();
        mType=args.getString(PersonalDelegate.ORDER_TYPE);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("http://117.48.205.138/RestServer/api/" +"order_list.php")
                .params("type", mType)
                .succes(new ISucces() {
                    @Override
                    public void onSuccess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new OrderListDataConverter().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);
//                        mRecyclerView.addOnItemTouchListener(new OrderListClickListener(OrderListDelegate.this));
                    }
                })
                .build()
                .get();
    }
}