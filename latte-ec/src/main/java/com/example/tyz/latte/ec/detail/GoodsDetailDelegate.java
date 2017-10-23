package com.example.tyz.latte.ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.tyz.latte.delegate.Lattedelegate;
import com.example.tyz.latte.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by TYZ on 2017/10/18.
 */

public class GoodsDetailDelegate extends Lattedelegate {

    public  static GoodsDetailDelegate create(){
        return new GoodsDetailDelegate();
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
