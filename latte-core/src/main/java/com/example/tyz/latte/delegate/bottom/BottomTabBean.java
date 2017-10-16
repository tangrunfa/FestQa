package com.example.tyz.latte.delegate.bottom;

/**
 * Created by Administrator on 2017/10/13.
 */

public final class BottomTabBean {



    private final CharSequence ICON;
    private final CharSequence TITLE;
    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }
    public CharSequence getICON() {
        return ICON;
    }

    public CharSequence getTITLE() {
        return TITLE;
    }

}
