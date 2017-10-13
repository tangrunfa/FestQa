package com.example.tyz.latte.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.example.tyz.latte.R;
import com.example.tyz.latte.delegate.Lattedelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Administrator on 2017/9/21.
 */

public abstract class ProxyActivity extends SupportActivity {
    public abstract Lattedelegate setrootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initcontainer(savedInstanceState);
    }

    //    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        initcontainer(savedInstanceState);
//    }

    private void initcontainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container= new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState==null){
            loadRootFragment(R.id.delegate_container,setrootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();//单activity 回收
        System.runFinalization();
    }
}
