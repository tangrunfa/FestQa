package com.example.tyz.latte.ui.refresh;

/**
 * Created by TYZ on 2017/10/18.
 */

public final class PagingBean {
    //当前第几页
    private int mPageIndex = 0;
    //总数数据条数
    private int mToatal = 0;
    //一页显示几条数据
    private int mPageSize = 0;
    //当前已经显示了几条数据
    private int mCurrentCount = 0;
    //加载延迟
    private int mDelayed = 0;

    public int getmPageIndex() {
        return mPageIndex;
    }

    public PagingBean setmPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getmToatal() {
        return mToatal;
    }

    public PagingBean setmToatal(int mToatal) {
        this.mToatal = mToatal;
        return this;
    }

    public int getmPageSize() {
        return mPageSize;
    }

    public PagingBean setmPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getmCurrentCount() {
        return mCurrentCount;
    }

    public PagingBean setmCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getmDelayed() {
        return mDelayed;
    }

    public PagingBean setmDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    PagingBean addIndex(){
        mPageIndex++;
        return this;
    }
}
