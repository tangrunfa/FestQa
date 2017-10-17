package com.example.tyz.latte.ui.recycler;

import java.util.ArrayList;

/**
 * Created by TYZ on 2017/10/17.
 */

public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES=new ArrayList<>();



    private  String mJsonData;

    public abstract ArrayList<MultipleItemEntity> convert();

    protected String getmJsonData() {
        if (mJsonData==null&&mJsonData.isEmpty()){
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }

    public DataConverter setmJsonData(String mJsonData) {
        this.mJsonData = mJsonData;
        return this;
    }
}
