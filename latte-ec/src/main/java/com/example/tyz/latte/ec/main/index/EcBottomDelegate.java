package com.example.tyz.latte.ec.main.index;

import android.graphics.Color;

import com.example.tyz.latte.delegate.bottom.BaseBottomDelegate;
import com.example.tyz.latte.delegate.bottom.BottomItemDelegate;
import com.example.tyz.latte.delegate.bottom.BottomTabBean;
import com.example.tyz.latte.delegate.bottom.ItemBuilder;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/10/13.
 */

public class EcBottomDelegate  extends BaseBottomDelegate{
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItem(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return  Color.parseColor("#ffff8800");
    }
}
