package com.example.administrator.latte_ui.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by TYZ on 2017/10/17.
 */

public class MultipleViewHolder extends BaseViewHolder {
    public MultipleViewHolder(View view) {
        super(view);
    }
    public static MultipleViewHolder creat(View view){
        return  new MultipleViewHolder(view);
    }
}
