package com.zcitc.advertisement.adv

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Message
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.zcitc.advertisement.adapter.FragmentStatePagerItemAdapter
import com.zcitc.advertisement.adapter.MyCommonNavigatorAdapter
import com.zcitc.advertisement.bean.ADPlanItemsData
import com.zcitc.advertisement.bean.AdvertisementRecommendationData
import com.zcitc.advertisement.databinding.ItemAdvRecommendBinding
import com.zcitc.advertisement.fragment.RecommendFragment
import com.zcitc.utilslibrary.utils.LiveDataBus
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator


class RecommendAdvView : RelativeLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)

    }
    lateinit var itemAdvRecommendBinding: ItemAdvRecommendBinding
    fun init(context: Context) {
        itemAdvRecommendBinding = ItemAdvRecommendBinding.inflate(LayoutInflater.from(context), this, true)
        LiveDataBus.get().with("RECOMMEND_ADV_CLICK", Message::class.java)
            .observeForever {
                onAdvClickLisener?.onClick(it.obj as String,"")
            }
    }
    var recommendTabPagerAdapter: FragmentStatePagerItemAdapter? = null
    var onAdvClickLisener: AdvClickLisener? = null
    fun showRecommendAdvData(activity: Activity, childFragmentManager: FragmentManager, data: AdvertisementRecommendationData,onAdvClickLisener: AdvClickLisener?) {
        this.onAdvClickLisener = onAdvClickLisener
        itemAdvRecommendBinding?.recommendLl?.visibility = View.GONE
        if (recommendTabPagerAdapter == null) {
        var fragments: MutableList<Fragment> = ArrayList()
        var titles: MutableList<String> = ArrayList()
        for (it in data.datas) {
            var dataList: MutableList<ADPlanItemsData> = java.util.ArrayList()
            dataList.addAll(getAdvList(activity,it.codeStr))
            if(dataList.size>0){
                titles.add(it.tags)
                val fragment =
                    RecommendFragment.newInstance(it.codeStr)
                fragments.add(fragment)
            }

        }

        if (fragments.size > 0) {
            if (recommendTabPagerAdapter == null) {
                recommendTabPagerAdapter =
                    FragmentStatePagerItemAdapter(childFragmentManager, fragments)
                itemAdvRecommendBinding?.recommendVp?.adapter = recommendTabPagerAdapter

                itemAdvRecommendBinding?.recommendMagicIndicator?.setBackgroundColor(Color.TRANSPARENT)

                val commonNavigator = CommonNavigator(activity)
                commonNavigator.isAdjustMode = titles.size <= 4
                commonNavigator.adapter =
                    MyCommonNavigatorAdapter(
                        activity,
                        titles,
                        itemAdvRecommendBinding?.recommendVp
                    )
                itemAdvRecommendBinding!!.recommendMagicIndicator.navigator = commonNavigator
                ViewPagerHelper.bind(
                    itemAdvRecommendBinding?.recommendMagicIndicator,
                    itemAdvRecommendBinding?.recommendVp
                )
                if (fragments.size > 0) {
                    itemAdvRecommendBinding?.recommendLl?.visibility = View.VISIBLE
                }else{
                    itemAdvRecommendBinding?.recommendLl?.visibility = View.GONE
                }
            }else{
                recommendTabPagerAdapter?.setDataSource(fragments)
                recommendTabPagerAdapter?.notifyDataSetChanged()
                if (fragments.size > 0) {
                    itemAdvRecommendBinding?.recommendLl?.visibility = View.VISIBLE
                }else{
                    itemAdvRecommendBinding?.recommendLl?.visibility = View.GONE
                }
            }
        }}
    }
}