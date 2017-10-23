package com.example.administrator.latte_ui.ui.banner;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.tyz.latte.R;

import java.util.ArrayList;

/**
 * Created by TYZ on 2017/10/18.
 */

public class BannerCreator {
    public static void setDefault(ConvenientBanner<String> convenientBanner, ArrayList<String> banners,
                                  OnItemClickListener clickListener){
        convenientBanner.setPages(new HolderCreator(),banners)
                .setPageIndicator(new int[]{R.drawable.dot_focus,R.drawable.dot_normal})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);
    }
}
