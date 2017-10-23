package com.example.tyz.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2017/9/20.
 */

public enum Ecicons implements Icon {
    icon_people('\ue6e1'),
    icon_scan('\ue602'),
    icon_ali_pay('\ue606');
    private  char character;

    Ecicons(char character){
    this.character=character;
    }
    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
