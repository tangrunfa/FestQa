package com.example.tyz.latte.ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.tyz.latte.app.Latte;
import com.example.tyz.latte.ec.R;
import com.example.tyz.latte.ui.recycler.MultipleFields;
import com.example.tyz.latte.ui.recycler.MultipleItemEntity;
import com.example.tyz.latte.ui.recycler.MultipleRecyclerAdapter;
import com.example.tyz.latte.ui.recycler.MultipleViewHolder;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * Created by TYZ on 2017/10/20.
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private int nowpos = 0;
    private boolean mIsSelectedAll = false;
    //初始化glide
    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }
    public void setIsSelectedAll(boolean isSelectedAll){
        this.mIsSelectedAll=isSelectedAll;
    }
    public void setNowpos(int nowpos){
        this.nowpos=nowpos;
    }
    @Override
    protected void convert(MultipleViewHolder holder,final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                //先取出所有值
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartItemFields.TITLE);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double price = entity.getField(ShopCartItemFields.PRICE);

                //取出所以控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);
                //赋值

                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(imgThumb);

                //在左侧勾勾渲染之前改变全选与否状态
                entity.setField(ShopCartItemFields.IS_SELECTED, mIsSelectedAll);
                final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                //根据数据状态显示左侧勾勾
                if (isSelected){
                    iconIsSelected.setTextColor(
                            ContextCompat.getColor(mContext,R.color.app_main));

                }else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                //添加左侧勾勾点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                        if (currentSelected) {
                            iconIsSelected.setTextColor(Color.GRAY);
                            entity.setField(ShopCartItemFields.IS_SELECTED, false);
                        } else {
                            iconIsSelected.setTextColor
                                    (ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                            entity.setField(ShopCartItemFields.IS_SELECTED, true);
                        }
                    }
                });
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                break;

            default:
                break;
        }
    }
}
