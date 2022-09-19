package com.zcitc.advertisement.adapter;

import android.content.Context;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.viewpager.widget.ViewPager;


import com.zcitc.advertisement.R;
import com.zcitc.advertisement.widget.HXLinePagerIndicator;
import com.zcitc.advertisement.widget.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * date   : 2021/3/212:49
 * @author liujie
 */
public class MyCommonNavigatorAdapter extends CommonNavigatorAdapter {
    List<String> recommends = new ArrayList<>();
    Context mContext;
    ViewPager viewPager;
    int w = 33;
    public MyCommonNavigatorAdapter(Context mContext, List<String> recommends, ViewPager viewPager){
        this.mContext = mContext;
        this.recommends = recommends;
        this.viewPager = viewPager;
    }
    public MyCommonNavigatorAdapter(Context mContext, List<String> recommends, ViewPager viewPager,int w){
        this.mContext = mContext;
        this.recommends = recommends;
        this.viewPager = viewPager;
        this.w = w;
    }
    @Override
    public int getCount() {
        return recommends.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, int index) {
        ScaleTransitionPagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
        simplePagerTitleView.setText(recommends.get(index));
        simplePagerTitleView.setNormalColor(mContext.getResources().getColor(R.color.gray_4));
        simplePagerTitleView.setSelectedColor(mContext.getResources().getColor(R.color.green_2));
        simplePagerTitleView.setOnClickListener(v -> viewPager.setCurrentItem(index));

        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        HXLinePagerIndicator indicator = new HXLinePagerIndicator(context);
        indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
        indicator.setLineHeight(UIUtil.dip2px(context, 3));
        indicator.setLineWidth(UIUtil.dip2px(context, w));
        indicator.setRoundRadius(UIUtil.dip2px(context, 2));

        indicator.setStartInterpolator(new AccelerateInterpolator());
        indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));

        return indicator;
    }
}
