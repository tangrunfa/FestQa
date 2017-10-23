package com.example.tyz.latte.ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.example.tyz.latte.ec.R;
import com.example.administrator.latte_ui.ui.recycler.RgbValue;

/**
 * Created by TYZ on 2017/10/18.
 */

public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar>{


    //顶部距离
    private int mDistanceY = 0;
    //颜色变化速度
    private static final int SHOW_SPEED = 3;
    //定义变化的颜色
    private final RgbValue  RGBVALUE=RgbValue.create(255,124,2);

    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId()== R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }


    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
       //增加滑动距离
        mDistanceY+=dy;
        //toolbar 高度
        final int targetHeight=child.getHeight();
        //当滑动时，并且距离小于 toolbar 高度的时候，调整渐变色
        if (mDistanceY<=targetHeight && mDistanceY>0){
            final  float scale=(float) mDistanceY/targetHeight;
            final float alpha=scale*255;
            child.setBackgroundColor(Color.argb((int)alpha,RGBVALUE.red(),RGBVALUE.green(),RGBVALUE.blue()));
        }else {
            child.setBackgroundColor(Color.rgb(RGBVALUE.red(), RGBVALUE.green(), RGBVALUE.blue()));
        }
    }
}
