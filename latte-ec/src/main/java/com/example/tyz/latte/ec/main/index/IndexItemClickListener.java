package com.example.tyz.latte.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.administrator.latte.ui.recycler.MultipleFields;
import com.example.administrator.latte.ui.recycler.MultipleItemEntity;
import com.example.tyz.latte.delegate.Lattedelegate;
import com.example.tyz.latte.ec.detail.GoodsDetailDelegate;

/**
 * Created by TYZ on 2017/10/18.
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final Lattedelegate DELEGATE;

    private IndexItemClickListener(Lattedelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static  SimpleClickListener create(Lattedelegate delegate){
        return new IndexItemClickListener(delegate);
    }
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final int goodsId = entity.getField(MultipleFields.ID);
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(goodsId);
        DELEGATE.getSupportDelegate().start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
