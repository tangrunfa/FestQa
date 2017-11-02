package com.example.tyz.latte.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.tyz.latte.delegate.Lattedelegate;
import com.example.tyz.latte.ec.R;

/**
 * Created by TYZ on 2017/10/26.
 */

public class NameDelegate extends Lattedelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
