package com.zcitc.advertisement.adv

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.fragment.app.FragmentManager
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.loader.ImageLoader
import com.zcitc.advertisement.ApiRoutes
import com.zcitc.advertisement.R
import com.zcitc.advertisement.bean.ADPlanItemsData
import com.zcitc.advertisement.bean.AdvertisementRecommendationData
import com.zcitc.advertisement.databinding.ItemAdvLayoutBinding


import com.zcitc.advertisement.utils.dp2px


import com.zcitc.advertisement.widget.AutoScrollTextView
import com.zcitc.advertisement.widget.MyBanner
import com.zcitc.glidelibrary.GlideUtils
import com.zcitc.utilslibrary.FastClickUtils


class AdvView : RelativeLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)

    }

    lateinit var rootbinding: ItemAdvLayoutBinding
    fun init(context: Context) {
//        LayoutInflater.from(context).inflate(R.layout.item_adv_layout, this)

        rootbinding = ItemAdvLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    }

    /**
     * 数据流广告
     * 随机
     */
    fun showRandomADData(activity: Context, data: ADPlanItemsData?) {
        rootbinding.advRootRl.removeAllViews()

        var houseAdView: HouseAdView = HouseAdView(activity)
        houseAdView.showHouseAdvData(data)
        rootbinding.advRootRl.addView(houseAdView)
    }


    /**
     * 启动页广告
     *gotoUrl:外部打app要跳转的链接
     */
    fun showStartAdData(
        activity: Activity,
        gotoUrl: String?,
        startAdData: MutableList<ADPlanItemsData>,
        onCountDownFinishListener: CountDownAdView.OnCountDownFinishListener?,
        onAdvClickLisener: AdvClickLisener?
    ) {
        rootbinding.advRootRl.removeAllViews()
        var countDownAdView: CountDownAdView = CountDownAdView(activity)
        countDownAdView.showCountDownAdData(activity, gotoUrl, startAdData,onAdvClickLisener)
        countDownAdView.onCountDownFinishListener = onCountDownFinishListener
        rootbinding.advRootRl.addView(countDownAdView)


    }

    /**
     * 启动页广告
     *gotoUrl:外部打app要跳转的链接
     */
    fun showStartAdData(
        activity: Activity,
        startAdData: MutableList<ADPlanItemsData>,
        onCountDownFinishListener: CountDownAdView.OnCountDownFinishListener?,
        onAdvClickLisener: AdvClickLisener?
    ) {
        rootbinding.advRootRl.removeAllViews()
        var countDownAdView: CountDownAdView = CountDownAdView(activity)
        countDownAdView.showCountDownAdData(activity, null, startAdData,onAdvClickLisener)
        countDownAdView.onCountDownFinishListener = onCountDownFinishListener
        rootbinding.advRootRl.addView(countDownAdView)
    }

    /**
     * 弹窗广告
     * PopAd
     */
    fun showPopAdData(activity: Context, list: List<ADPlanItemsData>?) {

    }

    /**
     * 顶部搜索关键字广告
     *  item_adv_search_key
     */
    fun showSearchKeyData(activity: Context, list: List<ADPlanItemsData>?, onAdvClickLisener: AdvClickLisener?) {

        rootbinding.advRootRl.removeAllViews()

        var toolbarKey = AutoScrollTextView(activity)
        val lp = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        toolbarKey.layoutParams = lp
        toolbarKey.setPadding(0, dp2px(activity, 9), 0, 0)
//
        var hotSearchesKeyList: MutableList<String> = ArrayList()
        if (list.isNullOrEmpty()) {
            setNullSearchsDataView(hotSearchesKeyList, toolbarKey,onAdvClickLisener)
            rootbinding.advRootRl.addView(toolbarKey)
            return
        }
        try {

            for (dataList in list) {
                if (dataList.bizData != null) {
                    if (!dataList.bizData.name.isNullOrEmpty()) {
                        hotSearchesKeyList.add(dataList.bizData.name!!)
                    }

                }
            }

            if (!hotSearchesKeyList.isNullOrEmpty()) {
                toolbarKey.setText(hotSearchesKeyList[0])
                toolbarKey.setList(hotSearchesKeyList)
                toolbarKey.stopScroll()
                toolbarKey.startScroll()
                toolbarKey.setClickLisener {
                    try {
                        AdvStatisticsUtils().saveAdvStatisticsdData(
                            activity,
                            AdvStatisticsUtils().changeData(
                                activity!!,
                                list[it]
                            )
                        )
                        onAdvClickLisener?.onClick(ApiRoutes.HOUSESEARCH,"搜索", hotSearchesKeyList[it])
//                        AppManager.getAppManager()
//                            .startWeb(ApiRoutes.HOUSESEARCH, false, "搜索", hotSearchesKeyList[it])
                    } catch (e: Exception) {
                    }

                }
            } else {
                setNullSearchsDataView(hotSearchesKeyList, toolbarKey,onAdvClickLisener)
            }
        } catch (e: Exception) {
            setNullSearchsDataView(hotSearchesKeyList, toolbarKey,onAdvClickLisener)

        }
        rootbinding.advRootRl.addView(toolbarKey)

    }

    private fun setNullSearchsDataView(
        hotSearchesKeyList: MutableList<String>,
        toolbarKey: AutoScrollTextView,
        onAdvClickLisener: AdvClickLisener?
    ) {
        hotSearchesKeyList.clear()
        hotSearchesKeyList.add("请输入房源名称")
        toolbarKey.setText(hotSearchesKeyList[0])
        toolbarKey.setList(hotSearchesKeyList)
        toolbarKey.stopScroll()
        toolbarKey.setClickLisener {
            onAdvClickLisener?.onClick(ApiRoutes.HOUSESEARCH, "搜索", "")
//            AppManager.getAppManager().startWeb(ApiRoutes.HOUSESEARCH, false, "搜索", "")
        }
    }

    /**
     * 顶部轮播图广告
     * item_adv_top_banner
     * 984*400
     */

    fun showTopBannerAdvData(activity: Activity, bannerList: List<ADPlanItemsData>?,onAdvClickLisener : AdvClickLisener?) {
        rootbinding.advRootRl.removeAllViews()
        var topBanner: MyBanner =
            createBanner(activity, 0.4065040650406504f, BannerConfig.CIRCLE_INDICATOR, 0)
        setBannerData(
            activity,
            topBanner,
            bannerList,
            onAdvClickLisener
        )
        rootbinding.advRootRl.addView(topBanner)
    }

    fun createBanner(activity: Activity, proportion: Float, style: Int, topH: Int): MyBanner {
        var banner: MyBanner = MyBanner(activity)
        val display: Display = activity.windowManager.defaultDisplay
        val lp = LinearLayout.LayoutParams(
            display.width, Math.round(display.width * proportion).toInt()
        )
        lp.topMargin = dp2px(activity, topH)
        banner.layoutParams = lp
        banner.setBackgroundColor(activity.resources.getColor(R.color.gray_f5f5f5))
        banner.setPadding(dp2px(activity, 16), 0, dp2px(activity, 16), 0)
        banner.setBannerStyle(style)
        return banner
    }

    private fun setBannerData(
        activity: Activity,
        banner: MyBanner,
        bannerList: List<ADPlanItemsData>?,
        onAdvClickLisener: AdvClickLisener?
    ) {

        var path: List<*>? = null
        if (bannerList.isNullOrEmpty()) {
            path = java.util.ArrayList<Int>()
            path.add(R.mipmap.banner)
        } else {
            path = java.util.ArrayList<String>()
            for (bean in bannerList) {
                path.add(bean.getImgUrl())
            }
        }

        banner.setImageLoader(GlideImageLoader())
        banner.setImages(path)
        banner.setDelayTime(3000)
        banner.setViewPagerIsScroll(true)
        banner.start()
        bannerList?.let {
            if (bannerList.size > 1) {
                banner.setOnBannerListener(OnBannerListener { position ->
                    if (!bannerList.isNullOrEmpty()) {
                        if (position < bannerList.size) {
                            if (!bannerList[position].eventLink.isNullOrEmpty()
                            ) {
                                if (!FastClickUtils.isFastClick()) {
                                    onAdvClickLisener?.onClick(bannerList[position].eventLink, "")
//                                    AppManager.getAppManager()
//                                        .startWeb(bannerList[position].eventLink, "")
                                    AdvStatisticsUtils().saveAdvStatisticsdData(
                                        activity,
                                        AdvStatisticsUtils().changeData(
                                            activity,
                                            bannerList[position]
                                        )
                                    )
                                }
                            }
                        }
                    }
                })
            } else if (bannerList.size == 1) {
                banner.setOnClickListener(OnClickListener { v: View? ->
                    if (bannerList.size > 0) {
                        if (bannerList.get(0).eventLink != null && "" != bannerList.get(0)
                                .eventLink
                        ) {
                            if (!FastClickUtils.isFastClick()) {
                                onAdvClickLisener?.onClick(bannerList.get(0).eventLink, "")
//                                AppManager.getAppManager().startWeb(bannerList.get(0).eventLink, "")
                                AdvStatisticsUtils().saveAdvStatisticsdData(
                                    activity,
                                    AdvStatisticsUtils().changeData(
                                        activity,
                                        bannerList.get(0)
                                    )
                                )
                            }
                        }
                    }


                })
            } else {

            }
        }

    }

    /**
     * 中部轮播图广告
     * item_adv_top_banner
     */
    fun showCentralBannerAdvData(activity: Activity, bannerList: List<ADPlanItemsData>?, onAdvClickLisener: AdvClickLisener?) {
        rootbinding.advRootRl.removeAllViews()
        rootbinding.advRootRl.visibility = View.GONE
        if (!bannerList.isNullOrEmpty()) {
            rootbinding.advRootRl.visibility = View.VISIBLE
            var banner: MyBanner =
                createBanner(activity, 0.19271623672f, BannerConfig.NOT_INDICATOR, 10)
            setBannerData(
                activity,
                banner,
                bannerList,
                onAdvClickLisener
            )
            rootbinding.advRootRl.addView(banner)
        }


    }

    /**
     *推荐广告  如：优秀门店, 优秀顾问，优秀中介
     * item_adv_recommend
     */

    fun showRecommendAdvData(
        activity: Activity,
        childFragmentManager: FragmentManager,
        data: AdvertisementRecommendationData?,
        onAdvClickLisener: AdvClickLisener?
    ) {
        rootbinding.advRootRl.removeAllViews()
        rootbinding.advRootRl.visibility = View.GONE
        if (data != null) {
            var recommendAdvView: RecommendAdvView = RecommendAdvView(activity)
            setTopMargin(recommendAdvView, activity)
            recommendAdvView.showRecommendAdvData(activity, childFragmentManager, data,onAdvClickLisener)
            rootbinding.advRootRl.addView(recommendAdvView)
            rootbinding.advRootRl.visibility = View.VISIBLE
        }
    }

    /**
     * 推荐文章 资讯广告
     *item_adv_news
     */

    fun showHotInformationAdvData(activity: Activity, data: MutableList<ADPlanItemsData>,onAdvClickLisener: AdvClickLisener?) {
        rootbinding.advRootRl.removeAllViews()
        rootbinding.advRootRl.visibility = View.GONE
        if (!data.isNullOrEmpty()) {
            rootbinding.advRootRl.visibility = View.VISIBLE
            var informationAdvView: InformationAdvView = InformationAdvView(activity)
            setTopMargin(informationAdvView, activity)
            informationAdvView.showInformationAdvData(activity, data,onAdvClickLisener)
            rootbinding.advRootRl.addView(informationAdvView)
        }


    }

    fun setTopMargin(view: View, activity: Activity) {
        val lp = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        lp.topMargin = dp2px(activity, 10)
        view.layoutParams = lp
    }

    class GlideImageLoader : ImageLoader() {
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView) {
            try {
                if (path is String) {
                    GlideUtils.showImage(
                        context,
                        path as String?,
                        imageView,
                        R.mipmap.home_image_placeholder3
                    )
                    imageView.scaleType = ImageView.ScaleType.FIT_XY
                }
                if (path is Int) {
                    GlideUtils.showImage(
                        context,
                        R.mipmap.banner,
                        imageView,
                        R.mipmap.home_image_placeholder3
                    )
                    imageView.scaleType = ImageView.ScaleType.FIT_XY

                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }


}