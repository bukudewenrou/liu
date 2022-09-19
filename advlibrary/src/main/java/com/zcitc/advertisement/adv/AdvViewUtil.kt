package com.zcitc.advertisement.adv

import android.app.Activity
import android.app.Application
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.lzy.okgo.OkGo
import com.lzy.okgo.https.HttpsUtils
import com.lzy.okgo.model.HttpHeaders
import com.zcitc.advertisement.KeySet
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_AGENCY_ADS
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_AGENT_ADS
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_BANNER_ADS
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_BLACK_ADS
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_CONSULTANT_ADS
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_FINANCIAL_INSTITUTION_ADS
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_POP_ADS
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_SEARCH_ADS
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_START_ADS
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_TOP_CAROUSEL_ADS
import com.zcitc.advertisement.bean.ADItemGroupsData

import com.zcitc.advertisement.bean.ADPlanItemsData
import com.zcitc.advertisement.helper.RefreshTokenUtils

import com.zcitc.advertisement.utils.LocalDataUtils
import com.zcitc.advertisement.utils.isNullOrEmptyStr
import com.zcitc.glidelibrary.GlideUtils

import com.zcitc.utilslibrary.FastClickUtils
import okhttp3.OkHttpClient
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class AdvViewUtil {

    /**
     * 初始化广告
     */
    fun initAdv(application: Application) {
        initHttp(application)
//        initWebViewCache(application)

    }

    val userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.43 BIDUBrowser/6.x Safari/537.31"
    private fun initHttp(application: Application) {
        val builder = OkHttpClient.Builder()
        val sslParams1 = HttpsUtils.getSslSocketFactory()
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager)
        var headers: HttpHeaders = HttpHeaders()
        headers.put("User-Agent", userAgent)
        headers.put("platform", "1")
        headers.put("app-version", "1.0")
        headers.put("api-version", "1")
        OkGo.getInstance().init(application).setOkHttpClient(builder.build())
            .addCommonHeaders(headers)
    }

//    private fun initWebViewCache(application: Application) {
//        val builder = WebViewCacheInterceptor.Builder(application)
//        builder.setCachePath(File(application.cacheDir, "cache_path_name")) //设置缓存路径，默认getCacheDir，名称CacheWebViewCache
//            .setCacheSize(1024 * 1024 * 100.toLong()) //设置缓存大小，默认100M
//            .setConnectTimeoutSecond(20) //设置http请求链接超时，默认20秒
//            .setReadTimeoutSecond(20) //设置http请求链接读取超时，默认20秒
//        val extension = CacheExtensionConfig()
//        extension.addExtension("json").removeExtension("swf")
//        builder.setCacheExtensionConfig(extension)
//        builder.setDebug(BuildConfig.DEBUG)
//        builder.setResourceInterceptor { true }
//        WebViewCacheInterceptorInst.getInstance().init(builder)
//    }




    fun getAdvDataAddShow(
        activity: FragmentActivity,
        tagData: String,onAdvClickLisener: AdvClickLisener?
    ) {
        getAdvDataAddShow(activity, null, tagData, null, null,onAdvClickLisener)
    }
    fun getAdvDataAddShow(
        activity: FragmentActivity, advView: AdvView?,
        tagData: String,onAdvClickLisener: AdvClickLisener?
    ) {
        getAdvDataAddShow(activity, advView, tagData, null, null,onAdvClickLisener)
    }

    fun getAdvDataAddShow(
        activity: FragmentActivity, advView: AdvView?,
        tagData: String, onCountDownFinishListener: CountDownAdView.OnCountDownFinishListener?,onAdvClickLisener: AdvClickLisener?
    ) {
        RefreshTokenUtils.requestToken(activity) {
            var configsVersion = LocalDataUtils().getValue(activity, KeySet.CONFIGS_VERSION)
            getAdAllData(activity, configsVersion, {
                setAddAdvView(activity, advView, tagData, null, onCountDownFinishListener,onAdvClickLisener)
            }, {
                setAddAdvView(activity, advView, tagData, null, onCountDownFinishListener,onAdvClickLisener)
            }, {
                setAddAdvView(activity, advView, tagData, null, onCountDownFinishListener,onAdvClickLisener)
            })
        }
    }

    fun getAdvDataAddShow(
        activity: FragmentActivity, advView: AdvView?,
        tagData: String, url: String?,onAdvClickLisener: AdvClickLisener?
    ) {
        RefreshTokenUtils.requestToken(activity) {
            var configsVersion = LocalDataUtils().getValue(activity, KeySet.CONFIGS_VERSION)
            getAdAllData(activity, configsVersion, {
                setAddAdvView(activity, advView, tagData, url, null,onAdvClickLisener)
            }, {
                setAddAdvView(activity, advView, tagData, url, null,onAdvClickLisener)
            }, {
                setAddAdvView(activity, advView, tagData, url, null,onAdvClickLisener)
            })
        }
    }

    fun getAdvDataAddShow(
        activity: FragmentActivity, advView: AdvView?,
        tagData: String, url: String?, onCountDownFinishListener: CountDownAdView.OnCountDownFinishListener?,onAdvClickLisener: AdvClickLisener?
    ) {
        RefreshTokenUtils.requestToken(activity) {
            var configsVersion = LocalDataUtils().getValue(activity, KeySet.CONFIGS_VERSION)
            getAdAllData(activity, configsVersion, {
                setAddAdvView(activity, advView, tagData, url, onCountDownFinishListener,onAdvClickLisener)
            }, {
                setAddAdvView(activity, advView, tagData, url, onCountDownFinishListener,onAdvClickLisener)
            }, {
                setAddAdvView(activity, advView, tagData, url, onCountDownFinishListener,onAdvClickLisener)
            })
        }
    }
    fun getPOPAdvShow(
        activity: FragmentActivity,
        onAdvClickLisener: AdvClickLisener?
    ) {
        RefreshTokenUtils.requestToken(activity) {
            var configsVersion = LocalDataUtils().getValue(activity, KeySet.CONFIGS_VERSION)
            getAdAllData(activity, configsVersion, {
                   showAdPopDialog(activity,onAdvClickLisener)
            }, {
                    showAdPopDialog(activity,onAdvClickLisener)
            }, {
                    showAdPopDialog(activity,onAdvClickLisener)
            })
        }
    }
    private fun setAddAdvView(
        activity: FragmentActivity, advView: AdvView?,
        tagData: String, url: String?, onCountDownFinishListener: CountDownAdView.OnCountDownFinishListener?,onAdvClickLisener: AdvClickLisener?
    ) {
        if (tagData.equals(FIRST_PAGE_CONSULTANT_ADS)
            || tagData.equals(FIRST_PAGE_AGENT_ADS)
            || tagData.equals(FIRST_PAGE_AGENCY_ADS)
            || tagData.equals(FIRST_PAGE_FINANCIAL_INSTITUTION_ADS)
        ) {
            advView?.let {view->
                getAdvMenuList(activity, {
                    view.showRecommendAdvData(activity!!, activity.supportFragmentManager, it,onAdvClickLisener)
                }, {
                    view.showRecommendAdvData(activity!!, activity.supportFragmentManager, null,onAdvClickLisener)
                }, {
                    view.showRecommendAdvData(activity!!, activity.supportFragmentManager, null,onAdvClickLisener)
                })
            }

        } else if (tagData.equals(FIRST_PAGE_START_ADS)) {
            advView?.let {
                var elementPlanItems: MutableList<ADPlanItemsData> = ArrayList()
                elementPlanItems.addAll(getAdvList(activity, FIRST_PAGE_START_ADS))
                if (!elementPlanItems.isNullOrEmpty()) {
                    it.visibility = View.VISIBLE

                    it.showStartAdData(activity, url, elementPlanItems, onCountDownFinishListener,onAdvClickLisener)
                } else {
                    it.visibility = View.GONE
                    onCountDownFinishListener?.onCountDownFinish()
                }
            }


        } else if (tagData.equals(FIRST_PAGE_POP_ADS)) {
            var elementPlanItems: MutableList<ADPlanItemsData> = ArrayList()
            elementPlanItems.addAll(getAdvList(activity, FIRST_PAGE_POP_ADS))
            if (!elementPlanItems.isNullOrEmpty()) {
                showAdPopDialog(activity, elementPlanItems,onAdvClickLisener)
            }

        } else {
            advView?.let { addAdvView(activity, it, tagData,onAdvClickLisener) }

        }
    }

    private fun showAdPopDialog(activity: FragmentActivity, popAblest: MutableList<ADPlanItemsData>,onAdvClickLisener: AdvClickLisener?) {
        if (popAblest.size > 0) {

            GlideUtils.preloadImg(activity,popAblest[0].imgUrl,534, 668)
            AdvDialog(activity, popAblest[0].imgUrl, View.OnClickListener {
                if (!isNullOrEmptyStr(popAblest?.first()?.eventLink)) {
                    if (!FastClickUtils.isFastClick()) {
                        onAdvClickLisener?.onClick(popAblest?.first()?.eventLink, "")
//                        AppManager.getAppManager().startWeb(popAblest?.first()?.eventLink, "")
                        AdvStatisticsUtils().saveAdvStatisticsdData(activity, AdvStatisticsUtils().changeData(activity, popAblest[0]))

                    }
                }
            }).show()
            var elementPlanItems: MutableList<ADPlanItemsData> = ArrayList()
            elementPlanItems.add(popAblest[0])
            AdvStatisticsUtils().getAdvsdisplayrecord(activity, elementPlanItems)
        }


    }
    var showendAdPopDialog : Boolean = false
     var advDialog :AdvDialog? = null
    private fun showAdPopDialog(activity: FragmentActivity,onAdvClickLisener: AdvClickLisener?)  {
        var popAblest: MutableList<ADPlanItemsData> = ArrayList()
        popAblest.addAll(getAdvList(activity, FIRST_PAGE_POP_ADS))
        if (!popAblest.isNullOrEmpty()) {
            if (popAblest.size > 0) {
                GlideUtils.preloadImg(activity,popAblest[0].imgUrl,534, 668)
                advDialog  = AdvDialog(activity, popAblest[0].imgUrl, View.OnClickListener {
                    if (!isNullOrEmptyStr(popAblest?.first()?.eventLink)) {
                        if (!FastClickUtils.isFastClick()) {
                            onAdvClickLisener?.onClick(popAblest?.first()?.eventLink, "")
//                            AppManager.getAppManager().startWeb(popAblest?.first()?.eventLink, "")
                            AdvStatisticsUtils().saveAdvStatisticsdData(activity, AdvStatisticsUtils().changeData(activity, popAblest[0]))
                            advDialog?.dismiss()
                        }
                    }

                })
                advDialog?.show()
                var elementPlanItems: MutableList<ADPlanItemsData> = ArrayList()
                elementPlanItems.add(popAblest[0])
                AdvStatisticsUtils().getAdvsdisplayrecord(activity, elementPlanItems)
                showendAdPopDialog = true


            }
        }



    }
    /**
     * 首页广告添加
     * tagData：搜索 FIRST_PAGE_SEARCH_ADS、首页中间banner广告 FIRST_PAGE_BANNER_ADS、首页头部banner广告 FIRST_PAGE_TOP_CAROUSEL_ADS、热门信息广告 FIRST_PAGE_BLACK_ADS
    tagStyle :风格
     */
    private fun addAdvView(
        activity: Activity,
        advView: AdvView,
        tagData: String,
        tagStyle: String,
        onAdvClickLisener: AdvClickLisener?
    ) {
        var elementPlanItems: MutableList<ADPlanItemsData> = ArrayList()
        elementPlanItems.addAll(getAdvList(activity, tagData))

        var isRefreshADdata = AdvStatisticsUtils().isRefreshADdata(
            activity,
            elementPlanItems,
            tagData + "DisplayRecord"
        )

        if (isRefreshADdata) {
            when (tagStyle) {
                FIRST_PAGE_SEARCH_ADS -> advView.showSearchKeyData(activity, elementPlanItems,onAdvClickLisener)
                FIRST_PAGE_BANNER_ADS -> advView.showCentralBannerAdvData(activity, elementPlanItems,onAdvClickLisener)
                FIRST_PAGE_TOP_CAROUSEL_ADS -> advView.showTopBannerAdvData(activity, elementPlanItems,onAdvClickLisener)
                FIRST_PAGE_BLACK_ADS -> advView.showHotInformationAdvData(activity, elementPlanItems,onAdvClickLisener)

            }
        }
    }

    /**
     * 首页广告添加
     * tagData：搜索 FIRST_PAGE_SEARCH_ADS、首页中间banner广告 FIRST_PAGE_BANNER_ADS、首页头部banner广告 FIRST_PAGE_TOP_CAROUSEL_ADS、热门信息广告 FIRST_PAGE_BLACK_ADS
    tagStyle :风格
     */
    fun addAdvView(
        activity: Activity,
        advView: AdvView,
        tagData: String,
        onAdvClickLisener: AdvClickLisener?
    ) {
        var elementPlanItems: MutableList<ADPlanItemsData> = ArrayList()
        elementPlanItems.addAll(getAdvList(activity, tagData))

        var isRefreshADdata = AdvStatisticsUtils().isRefreshADdata(
            activity,
            elementPlanItems,
            tagData + "DisplayRecord"
        )

        if (isRefreshADdata) {
            when (tagData) {
                FIRST_PAGE_SEARCH_ADS -> advView.showSearchKeyData(activity, elementPlanItems,onAdvClickLisener)
                FIRST_PAGE_BANNER_ADS -> advView.showCentralBannerAdvData(activity, elementPlanItems,onAdvClickLisener)
                FIRST_PAGE_TOP_CAROUSEL_ADS -> advView.showTopBannerAdvData(activity, elementPlanItems,onAdvClickLisener)
                FIRST_PAGE_BLACK_ADS -> advView.showHotInformationAdvData(activity, elementPlanItems,onAdvClickLisener)

            }
        }
    }

    fun clearAdvCache(activity: Activity) {
        LocalDataUtils().setValue(activity, KeySet.CONFIGS_VERSION, "1")
        AdvStatisticsUtils().delDisplayRecordData(activity)
        AdvStatisticsUtils().delDisplayAdvCache(activity)
    }

    fun clearAdVersion(activity: Activity) {
        LocalDataUtils().setValue(activity, KeySet.CONFIGS_VERSION, "1")
        AdvStatisticsUtils().delDisplayRecordData(activity)
        AdvStatisticsUtils().delDisplayAdvCache(activity)
    }
}