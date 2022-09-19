package com.zcitc.advertisement.adv

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.zcitc.advertisement.bean.ADPlanItemsData
import com.zcitc.advertisement.databinding.ItemSplashAdvBinding
import com.zcitc.glidelibrary.GlideUtils
import com.zcitc.utilslibrary.FastClickUtils


/**
 * 倒计时广告view
 */
class SplashAdView : RelativeLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)

    }

    lateinit var mBinding: ItemSplashAdvBinding
    fun init(context: Context) {
        mBinding = ItemSplashAdvBinding.inflate(LayoutInflater.from(context), this, true)
        mBinding.mStarAdImg.setOnClickListener {
            if (!advUrl.isNullOrEmpty()) {
                if (!FastClickUtils.isFastClick()) {
                    if(!adList.isNullOrEmpty()){
                        AdvStatisticsUtils().saveAdvStatisticsdData(context, AdvStatisticsUtils().changeData(context,adList[0]))
                    }
                    onAdvClickLisener?.onClick(advUrl, "",)
//                    AppManager.getAppManager().startWebAD(advUrl, "",null)

                }
            }
        }
    }



    private var advUrl: String? = ""

    var onAdvClickLisener: AdvClickLisener? = null
    var adList: MutableList<ADPlanItemsData> = ArrayList()
    fun showCountDownAdData(activity: Activity, adList: MutableList<ADPlanItemsData>,onAdvClickLisener: AdvClickLisener?) {

        this.onAdvClickLisener = onAdvClickLisener
        this.adList = adList
        if (adList.isNullOrEmpty()) {
            return
        }

        if (adList.size > 0) {
            advUrl = adList[0].eventLink
            mBinding.advRL.visibility = View.VISIBLE
            GlideUtils.showImage(activity, adList[0].getImgUrl(), mBinding.mStarAdImg)
            var elementPlanItems: MutableList<ADPlanItemsData> = ArrayList()
            elementPlanItems.add(adList[0])
            AdvStatisticsUtils().getAdvsdisplayrecord(activity, elementPlanItems)
        }


    }



}