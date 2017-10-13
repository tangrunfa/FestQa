package com.example.tyz.latte.delegate.bottom;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/10/13.
 */

public final class ItemBuilder {
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate){
        ITEMS.put(bean,delegate);
        return this;
    }
    public ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> items){
        ITEMS.putAll(items);
        return this;
    }
    public final  LinkedHashMap<BottomTabBean, BottomItemDelegate> build(){
        return ITEMS;
    }
}
