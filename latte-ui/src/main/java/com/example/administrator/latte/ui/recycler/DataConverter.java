package com.example.administrator.latte_ui.ui.recycler;

import java.util.ArrayList;

/**
 * 处理数据的模板
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

    public DataConverter setJsonData(String mJsonData) {
        this.mJsonData = mJsonData;
        return this;
    }
}
