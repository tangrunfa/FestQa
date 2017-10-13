package com.example.tyz.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.tyz.latte.app.latte;

/**
 * Created by Administrator on 2017/9/26.
 */

public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources= latte.getApplicationContext().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.widthPixels;
    }
    public static int getScreenHeight(){
        final Resources resources= latte.getApplicationContext().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
