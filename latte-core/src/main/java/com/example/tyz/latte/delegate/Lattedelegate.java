package com.example.tyz.latte.delegate;

/**
 * Created by Administrator on 2017/9/22.
 */

public abstract class Lattedelegate extends PermissionCheckerDelegate  {
    public <T extends Lattedelegate> T getParentDelegate(){
        return (T)getParentFragment();
    }
}
