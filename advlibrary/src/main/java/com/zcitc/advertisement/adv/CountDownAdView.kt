package com.zcitc.advertisement.adv

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.zcitc.advertisement.adv.AdvStatisticsUtils
import com.zcitc.advertisement.bean.ADPlanItemsData
import com.zcitc.advertisement.databinding.ItemCountdownAdvBinding

import com.zcitc.advertisement.utils.setStatusBarHeight
import com.zcitc.glidelibrary.GlideUtils
import com.zcitc.utilslibrary.FastClickUtils


/**
 * 倒计时广告view
 */
class CountDownAdView : RelativeLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)

    }
   var onAdvClickLisener: AdvClickLisener? = null
    lateinit var mBinding: ItemCountdownAdvBinding
    fun init(context: Context) {
        mBinding = ItemCountdownAdvBinding.inflate(LayoutInflater.from(context), this, true)
        mBinding.jumpTv.setOnClickListener {
            if (!advUrl.isNullOrEmpty()) {
                if (!FastClickUtils.isFastClick()) {
                    if(!adList.isNullOrEmpty()){
                        AdvStatisticsUtils().saveAdvStatisticsdData(context, AdvStatisticsUtils().changeData(context,adList[0]))
                    }
                    isClickNextStartAd = true
                    val url: String? = gotoUrl
                    onAdvClickLisener?.onClick(advUrl, advTitle)
//                    AppManager.getAppManager().startWebAD(advUrl, advTitle,url)

                }
            }
        }
        mBinding.nextTv.setOnClickListener {
            mStartAdDownTimer?.cancel()
            onCountDownFinishListener?.onCountDownFinish()
        }
    }



    private var advUrl: String? = ""
    private var gotoUrl: String? = ""
    private var advTitle: String? = ""

    var adList: MutableList<ADPlanItemsData> = ArrayList()
    fun showCountDownAdData(activity: Activity,gotoUrl: String?, adList: MutableList<ADPlanItemsData>,onAdvClickLisener: AdvClickLisener?) {
        this.gotoUrl = gotoUrl
        this.adList = adList
        this.onAdvClickLisener = onAdvClickLisener
        if (adList.isNullOrEmpty()) {
            return
        }

        if (adList.size > 0) {
            mBinding.advRL.visibility = View.VISIBLE
            setStatusBarHeight( mBinding.mToolbarView,activity,activity.windowManager)
            advUrl = adList[0].eventLink
            advTitle = ""

            GlideUtils.showImage(activity, adList[0].getImgUrl(), mBinding.mStarAdImg)
            setStartAdDownTimer(adList[0].autoCloseSecondInt, adList[0].autoCloseSecondInt * 1000L)
            mStartAdDownTimer?.start()
            var elementPlanItems: MutableList<ADPlanItemsData> = ArrayList()
            elementPlanItems.add(adList[0])
            AdvStatisticsUtils().getAdvsdisplayrecord(activity, elementPlanItems)
        }


    }


    /**
     * 启动广告倒计时
     */
    var mStartAdDownTimer: CountDownTimer? = null

    /**是否点击跳过*/
    var isClickNextStartAd: Boolean = false
    private fun setStartAdDownTimer(countdownTime: Int, millisInFuture: Long) {
        var time = countdownTime
        mStartAdDownTimer = object : CountDownTimer(millisInFuture, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                time--
                if (time == 1) {
                    mBinding.nextTv.text = "跳过 $time"
                    mStartAdDownTimer?.onFinish()
                } else {
                    mBinding.nextTv.text = "跳过 $time"
                }


            }

            /**
             * 倒计时结束后调用的
             */
            override fun onFinish() {
                if (!isClickNextStartAd) {
                    onCountDownFinishListener?.onCountDownFinish()
                }
                mStartAdDownTimer?.cancel()
            }
        }
    }

    var onCountDownFinishListener: OnCountDownFinishListener? = null

    interface OnCountDownFinishListener {
        fun onCountDownFinish()
    }
}