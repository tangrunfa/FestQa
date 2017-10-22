package com.example.administrator.latte_ui.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by TYZ on 2017/10/18.
 */

public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorInt int color,int size){
        setDividerLookup(new DividerLookupImpl(color,size));
    }

    public static BaseDecoration create(@ColorInt int color,int size){
        return new BaseDecoration(color,size);
    }
}
