package com.example.tyz.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.tyz.latte.delegate.bottom.BottomItemDelegate;
import com.example.tyz.latte.ec.R;
import com.example.tyz.latte.ec.main.sort.content.ContentDelegate;
import com.example.tyz.latte.ec.main.sort.list.VerticalListDelegate;

/**
 * Created by TYZ on 2017/10/18.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate verticalListDelegate=new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container,verticalListDelegate);
        //设置右侧第一个分类显示，默认显示分类1
        loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));;
    }
}
