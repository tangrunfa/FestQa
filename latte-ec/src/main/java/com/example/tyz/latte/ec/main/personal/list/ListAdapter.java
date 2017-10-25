package com.example.tyz.latte.ec.main.personal.list;

import android.support.v7.widget.SwitchCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tyz.latte.ec.R;

import java.util.List;

/**
 * Created by TYZ on 2017/10/25.
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(20, R.layout.arrow_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case 20:
                helper.setText(R.id.tv_arrow_text, item.getText());
                helper.setText(R.id.tv_arrow_value, item.getValue());
                break;
//            case ListItemType.ITEM_AVATAR:
//                Glide.with(mContext)
//                        .load(item.getImageUrl())
//                        .apply(OPTIONS)
//                        .into((ImageView) helper.getView(R.id.img_arrow_avatar));
//                break;
//            case ListItemType.ITEM_SWITCH:
//                helper.setText(R.id.tv_arrow_switch_text,item.getText());
//                final SwitchCompat switchCompat = helper.getView(R.id.list_item_switch);
//                switchCompat.setChecked(true);
//                switchCompat.setOnCheckedChangeListener(item.getmOnCheckedChangeListener());
//                break;
            default:
                break;
        }
    }
}
