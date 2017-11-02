package com.example.tyz.latte.util.callback;

import android.support.annotation.Nullable;

/**
 * Created by TYZ on 2017/10/26.
 */

public interface  IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}