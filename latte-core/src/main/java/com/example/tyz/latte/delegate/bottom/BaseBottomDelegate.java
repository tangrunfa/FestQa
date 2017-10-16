package com.example.tyz.latte.delegate.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tyz.latte.R;
import com.example.tyz.latte.R2;
import com.example.tyz.latte.delegate.Lattedelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/10/13.
 */

public abstract class BaseBottomDelegate extends Lattedelegate implements View.OnClickListener{
    private final ArrayList<BottomItemDelegate>  ITEMS_DELEGATES=new ArrayList<>();
    private final ArrayList<BottomTabBean>  TAB_BEAN=new ArrayList<>();
    private final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS=new LinkedHashMap<>();
    private int mCurrentDelegate=0;
    private int mIndexDelegate=0;
    private int mClickColor= Color.RED;

    public abstract LinkedHashMap<BottomTabBean,BottomItemDelegate> setItem(ItemBuilder builder);

    public abstract int setIndexDelegate();
    @ColorInt
    public  abstract int setClickColor();

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottoBar=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate=setIndexDelegate();
        if (setClickColor()!=0){
            mClickColor=setClickColor();
        }
        final  ItemBuilder builder=new ItemBuilder();
        final  LinkedHashMap<BottomTabBean,BottomItemDelegate> items=setItem(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean,BottomItemDelegate> item:ITEMS.entrySet()){
            final BottomTabBean key=item.getKey();
            final BottomItemDelegate value=item.getValue();
            TAB_BEAN.add(key);
            ITEMS_DELEGATES.add(value);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {
        final int size=ITEMS.size();
        for (int i=0;i<size;i++){
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout,mBottoBar);
            final RelativeLayout item= (RelativeLayout) mBottoBar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itemICon= (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle= (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean=TAB_BEAN.get(i);
            //初始化数据
            itemICon.setText(bean.getICON());
            itemTitle.setText(bean.getTITLE());
            if (i==mIndexDelegate){
                itemICon.setTextColor(mClickColor);
                itemTitle.setTextColor(mClickColor);
            }
        }
        final SupportFragment[] delegateArry =ITEMS_DELEGATES.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.bottom_bar_delegate_container,mIndexDelegate,delegateArry);
    }

    private void resetColor(){
        final int count=mBottoBar.getChildCount();
        for (int i=0;i<count;i++){
            final RelativeLayout item= (RelativeLayout) mBottoBar.getChildAt(i);
            final IconTextView itemICon= (IconTextView) item.getChildAt(0);
            itemICon.setTextColor(Color.GRAY);
            final AppCompatTextView itemTitle= (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        final int tag= (int) v.getTag();
        resetColor();
        final RelativeLayout item= (RelativeLayout) v;
        final IconTextView itemICon= (IconTextView) item.getChildAt(0);
        itemICon.setTextColor(mClickColor);
        final AppCompatTextView itemTitle= (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickColor);
        showHideFragment(ITEMS_DELEGATES.get(tag),ITEMS_DELEGATES.get(mCurrentDelegate));
        //注意先后顺序
        mCurrentDelegate=tag;
    }
}
