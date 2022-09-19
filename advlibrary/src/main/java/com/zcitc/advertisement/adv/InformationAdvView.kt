package com.zcitc.advertisement.adv

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.zcitc.advertisement.adapter.PlateRecommendAdapter
import com.zcitc.advertisement.bean.ADPlanItemsData
import com.zcitc.advertisement.databinding.ItemAdvNewsBinding



class InformationAdvView : RelativeLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)

    }
    lateinit var mBinding: ItemAdvNewsBinding
    fun init(context: Context) {
        mBinding = ItemAdvNewsBinding.inflate(LayoutInflater.from(context), this, true)

    }
    fun showInformationAdvData(activity: Activity, adList: MutableList<ADPlanItemsData>,onAdvClickLisener: AdvClickLisener?) {
            var mRecommendArticleAdapter: PlateRecommendAdapter
            mBinding.recommendPlateRv?.layoutManager = GridLayoutManager(activity, 2)
            mRecommendArticleAdapter = PlateRecommendAdapter(adList)
            mBinding.recommendPlateRv?.adapter = mRecommendArticleAdapter
            mRecommendArticleAdapter?.setOnItemClickListener { _, _, position ->
                if (position < adList.size)
                    onAdvClickLisener?.onClick(adList[position].eventLink, "")
//                    AppManager.getAppManager().startWeb(adList[position].eventLink, "")
            }


    }
}