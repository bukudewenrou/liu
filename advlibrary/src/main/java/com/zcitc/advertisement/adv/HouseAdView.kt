package com.zcitc.advertisement.adv


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.zcitc.advertisement.bean.ADPlanItemsData
import com.zcitc.advertisement.databinding.ItemHouseBinding

class HouseAdView : RelativeLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)

    }

    lateinit var mBinding: ItemHouseBinding
    fun init(context: Context) {
        mBinding = ItemHouseBinding.inflate(LayoutInflater.from(context), this, true)

    }

    fun showHouseAdvData( data : ADPlanItemsData?) {

//        mBinding.latestOpeningPriceTv1.paint.isFakeBoldText = true //加粗
//
//        if (type == 1) {
//            mBinding.introductionTv.text =  "30天内成交" + data.bizData.getSalesOf30Day() + "套"
//            mBinding.unitTv.text =  "元/㎡起"
//            mBinding.latestOpeningPriceTv1.text =  data.bizData.getReferenceUnitPrice().toString()
//
//        } else if (type == 11) {
//            mBinding.introductionTv.text =  "报名期内" + data.bizData.getIntentionAppliesOf30Day() + "人认购"
//            mBinding.unitTv.text =  "元/㎡起"
//            mBinding.latestOpeningPriceTv1.text =  data.bizData.getReferenceUnitPrice().toString()
//
//        } else if (type == 21) {
//            mBinding.introductionTv.text =  "" + data.bizData.getTotalViews() + "人浏览过"
//            mBinding.unitTv.text =  "万元"
//            mBinding.latestOpeningPriceTv1.text =  data.bizData.getReferenceTotalPrice().toString()
//
//        } else if (type == 31) {
//            mBinding.introductionTv.text =  "低于均价" + data.bizData.getLowerPercentage() + "%"
//            mBinding.unitTv.text =  "万元"
//            mBinding.latestOpeningPriceTv1.text =  data.bizData.getReferenceTotalPrice().toString()
//
//        } else {
//            mBinding.introductionTv.text =  "30天内成交" + data.bizData.getSalesOf30Day() + "套"
//            mBinding.unitTv.text =  "元/㎡起"
//            mBinding.latestOpeningPriceTv1.text =  data.bizData.getReferenceUnitPrice().toString()
//
//        }
//
//        GlideUtils.showImage(context, data.bizData.getImageUrl(), mBinding.latestOpeningImgIv1, R.mipmap.ic_default)
//        mBinding.latestOpeningTypeTv1.text = data.bizData.getPurposeName()
//        mBinding.latestOpeningTitleTv1.text = data.bizData.getName()
//        mBinding.latestOpeningConTv1.text = data.bizData.getDivisionName()+ "  " + data.bizData.getTownName() + "  " + data.bizData.getGrossFloorArea()
//        mBinding.latestOpeningIntroductionTv1.visibility = View.GONE
//        mBinding.latestOpeningIntroductionTv2.visibility = View.GONE
//        mBinding.latestOpeningIntroductionTv3.visibility = View.GONE
//        if(data.bizData.getPurposeName().isEmpty()){
//            mBinding.latestOpeningTypeTv1.visibility = View.GONE
//        }else{
//            mBinding.latestOpeningTypeTv1.visibility = View.VISIBLE
//        }
//
//
//        try {
//            val tagsList: List<String> = data.bizData.getFeatures()
//            for (i in tagsList.indices) {
//                if (i == 0) {
//                    mBinding.latestOpeningIntroductionTv1.visibility = View.VISIBLE
//                    mBinding.latestOpeningIntroductionTv1.text = tagsList[i]
//                } else if (i == 1) {
//                    mBinding.latestOpeningIntroductionTv2.visibility = View.VISIBLE
//                    mBinding.latestOpeningIntroductionTv2.text = tagsList[i]
//
//                } else if (i == 2) {
//                    mBinding.latestOpeningIntroductionTv3.visibility = View.VISIBLE
//                    mBinding.latestOpeningIntroductionTv3.text = tagsList[i]
//
//                }
//            }
//        } catch (ignored: Exception) {
//        }

    }
}