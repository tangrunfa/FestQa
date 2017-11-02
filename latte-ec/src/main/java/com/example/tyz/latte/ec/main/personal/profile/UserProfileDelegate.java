package com.example.tyz.latte.ec.main.personal.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.tyz.latte.delegate.Lattedelegate;
import com.example.tyz.latte.ec.R;
import com.example.tyz.latte.ec.R2;
import com.example.tyz.latte.ec.main.personal.list.ListAdapter;
import com.example.tyz.latte.ec.main.personal.list.ListBean;
import com.example.tyz.latte.ec.main.personal.list.ListItemType;
import com.example.tyz.latte.ec.main.personal.settings.NameDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by TYZ on 2017/10/26.
 */

public class UserProfileDelegate extends Lattedelegate {

    @BindView(R2.id.rv_user_profile)
    RecyclerView mRecycleView=null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        final ListBean image = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("http://i9.qhimg.com/t017d891ca365ef60b5.jpg")
                .build();
        final ListBean name = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new NameDelegate())
                .setText("姓名")
                .setValue("小小烦恼")
                .build();

        final ListBean genner = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setValue("未设置")
                .build();
        final ListBean birth = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(4)

                .setText("生日")
                .setValue("未设置")
                .build();
        final List<ListBean> data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(genner);
        data.add(birth);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecycleView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecycleView.setAdapter(adapter);
        mRecycleView.addOnItemTouchListener(new UserProfileClickListener(this));
    }
}
