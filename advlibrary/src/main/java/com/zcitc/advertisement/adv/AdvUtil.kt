package com.zcitc.advertisement.adv

import android.app.Activity
import com.alibaba.fastjson.JSON
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.zcitc.advertisement.ApiRoutes
import com.zcitc.advertisement.KeySet
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_BANNER_ADS
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_BLACK_ADS
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_SEARCH_ADS
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_TOP_CAROUSEL_ADS
import com.zcitc.advertisement.bean.ADGroupsData
import com.zcitc.advertisement.bean.ADItemGroupsData
import com.zcitc.advertisement.bean.ADPlanItemsData
import com.zcitc.advertisement.bean.AdvertisementRecommendationData


import com.zcitc.advertisement.utils.ListUtils
import com.zcitc.advertisement.utils.LocalDataUtils
import com.zcitc.advertisement.utils.getTokenLogin
import com.zcitc.advertisement.utils.getTokenType
import com.zcitc.httplibrary.net.BRequestCallback
import com.zcitc.httplibrary.net.RequestException
import com.zcitc.utilslibrary.ACache
import kotlin.collections.ArrayList


/**
 * 获取筛选后广告数据（一级筛选）
 * 1、在固定显示内的数据
 */
fun getAdvList(activity: Activity,
               tag : String,) : MutableList<ADPlanItemsData>{
    var elementPlanItems: MutableList<ADPlanItemsData> = ArrayList()
    ACache.get(activity).getAsString(tag)?.let { s ->
        JSON.parseObject(s, ADItemGroupsData::class.java)?.let {
            var currentElementPlanItems: MutableList<ADPlanItemsData> = ArrayList()
            if(!it.advItems.isNullOrEmpty()) {
                for (data in it.advItems) {
                    if (data.isShow()) {
                        currentElementPlanItems.add(data)
                    }
                }

                elementPlanItems.addAll(
                    getNewAdvList(
                        it.maxDisplays,
                        it.orderMode,
                        currentElementPlanItems
                    )
                )

            }
        }
    }
    return elementPlanItems
}
/**
 * 获取筛选后广告数据（二次筛选）
 *  1、根据接口指定的最大条数返回列表数量
 * 2、根据接口指示 随机获取\顺序获取
 */
fun getNewAdvList(
    maxDisplays: Int,
    orderMode: Int,
    currentElementPlanItems: MutableList<ADPlanItemsData>
): MutableList<ADPlanItemsData> {
    var elementPlanItems: MutableList<ADPlanItemsData> = ArrayList()
    if (orderMode != 4) {
        //随机 maxDisplays最大显示数量
        var allElementPlanItems: MutableList<ADPlanItemsData> = ArrayList()
        allElementPlanItems = ListUtils.createADPlanItemsDataList(
            currentElementPlanItems,
            currentElementPlanItems.size
        )
        if (maxDisplays >= allElementPlanItems.size) {
            for (data in allElementPlanItems) {
                elementPlanItems.add(data)
            }
        } else {
            for (index in 0 until maxDisplays) {
                elementPlanItems.add(allElementPlanItems[index])
            }

        }
    } else {

        if (maxDisplays >= currentElementPlanItems.size) {
            for (data in currentElementPlanItems) {
                elementPlanItems.add(data)
            }
        } else {
            for (index in 0 until maxDisplays) {
                elementPlanItems.add(currentElementPlanItems[index])
            }

        }

    }

    return elementPlanItems;
}


fun isExistHomeAdv(activity: Activity) : Boolean{
    if(getAdvList(activity, FIRST_PAGE_SEARCH_ADS).size>0){
        return true
    }
    if(getAdvList(activity, FIRST_PAGE_BANNER_ADS).size>0){
        return true
    }
    if(getAdvList(activity, FIRST_PAGE_TOP_CAROUSEL_ADS).size>0){
        return true
    }
    if(getAdvList(activity, FIRST_PAGE_BLACK_ADS).size>0){
        return true
    }


    return false
}

//fun getDataString() : String {
//    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
//    val date = Date(System.currentTimeMillis())
//    return simpleDateFormat.format(date)
//}

/**
 * 首页推荐广告tab配置
 */
fun getAdvMenuList(
    activity: Activity,
    success: ((data: AdvertisementRecommendationData) -> Unit)? = null,
    nullData: (() -> Unit)? = null,
    error: (() -> Unit)? = null,
    finish: (() -> Unit)? = null
) {
    val type = getTokenType(activity)
    val token = getTokenLogin(activity)
    OkGo.get<AdvertisementRecommendationData>(ApiRoutes.ADVFIRSTPAGEPARTY)
        .tag("getAdvMenuList")
        .headers(KeySet.AUTHORIZATION, "$type $token")
        .execute(object : BRequestCallback<AdvertisementRecommendationData>() {
            override fun onMySuccess(data: AdvertisementRecommendationData) {
                success?.invoke(data)
            }

            override fun onSuccessNullData() {
                nullData?.invoke()

            }

            override fun onError(response: Response<AdvertisementRecommendationData>?) {
                error?.invoke()


            }

            override fun onFinish() {
                super.onFinish()
                finish?.invoke()
            }
        })
}

/**
 * 获取全部广告数据
 */
fun getAdAllData(activity: Activity,
                 configsVersion:String,
                 success: (() -> Unit)? = null,
                 successNull: (() -> Unit)? = null,
                 onError: (() -> Unit)? = null,
                 onFinish: (() -> Unit)? = null) {
    val type = getTokenType(activity)
    val token = getTokenLogin(activity)
    var version :String = ""
    if(!configsVersion.isNullOrEmpty()){
        version = if (isExistHomeAdv(activity)) configsVersion else ""
    }

    OkGo.get<ADGroupsData>(ApiRoutes.ALLAD + version)
        .tag(activity)
        .headers(KeySet.AUTHORIZATION, "$type $token")
        .execute(object : BRequestCallback<ADGroupsData>() {
            override fun onMySuccess(data: ADGroupsData) {


                AdvStatisticsUtils().delDisplayRecordData(activity)
                if(!data.advConfigs.isNullOrEmpty()){
                    LocalDataUtils().setValue(activity,KeySet.CONFIGS_VERSION, "" + data.version)
                    ADItemGroupsUtils.saveADItemGroup(activity, data.advConfigs)
                }

                success?.invoke()


            }

            override fun onSuccessNullData() {

                LocalDataUtils().setValue(activity,KeySet.CONFIGS_VERSION, "" )
                ADItemGroupsUtils.removeADItemGroup(activity)
                AdvStatisticsUtils().delDisplayRecordData(activity)
                successNull?.invoke()

            }

            override fun onError(response: Response<ADGroupsData>) {
                
                try {

                    if (response.exception is RequestException) {
                        if ((response.exception as RequestException).statusCode == 304) {
                            onError?.invoke()
                            return
                        } else if ((response.exception as RequestException).statusCode == 401 || (response.exception as RequestException).statusCode == 403) {
                            onError?.invoke()
                            return
                        }
                    }

                } catch (e: Exception) {

                }


            }

            override fun onFinish() {
                super.onFinish()
                onFinish?.invoke()
            }
        })
}
