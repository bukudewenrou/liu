package com.zcitc.advertisement.adv

import android.content.Context
import android.net.ConnectivityManager

import com.alibaba.fastjson.JSON
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.zcitc.advertisement.ApiRoutes
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
import com.zcitc.advertisement.bean.ButtonClickRecordBean
import com.zcitc.advertisement.bean.ADItemGroupsData
import com.zcitc.advertisement.bean.ADPlanItemsData
import com.zcitc.advertisement.bean.AdvBean
import com.zcitc.advertisement.bean.ButtonClickRecordData
import com.zcitc.advertisement.utils.LocalDataUtils
import com.zcitc.advertisement.utils.getDeviceId
import com.zcitc.advertisement.utils.getTokenLogin
import com.zcitc.advertisement.utils.getTokenType
import com.zcitc.advertisement.utils.getUserId
import com.zcitc.advertisement.utils.isLogin
import com.zcitc.advertisement.utils.removeJSON
import com.zcitc.advertisement.utils.saveJSON
import com.zcitc.httplibrary.net.BRequestCallback
import com.zcitc.utilslibrary.ACache
import kotlin.collections.ArrayList


/**
 *   author : LiuJie
 *   date   : 2021/3/2312:39
 */
class AdvStatisticsUtils {

    fun changeData(mContext: Context, advStatisticsdBean: ADPlanItemsData): ADPlanItemsData {
        var data: ADPlanItemsData = advStatisticsdBean;
        try {

            if (isLogin(mContext)) {
                data.userId = getUserId(mContext)
            } else {
                data.userId = null
            }

            data.triggerTime = System.currentTimeMillis()

            data.deviceModel =
                "手机厂商：" + getDeviceBrand() + "," + "手机型号：" + getSystemModel() + "," + "Android系统版本号：" + getSystemVersion()
            data.deviceId = getDeviceId(mContext)
            data.deviceNetwork = getNetworkStr(mContext)

        } catch (e: Exception) {

        }
        return data
    }

    /**
     *曝光统计
     */
    fun getAdvsdisplayrecord(mContext: Context, elementPlanItems: MutableList<ADPlanItemsData>) {

        try {
            var params: MutableList<AdvBean> = ArrayList<AdvBean>()


            for (it in elementPlanItems) {
                var advBean: AdvBean = AdvBean()

                if (isLogin(mContext)) {
                    advBean.userId = getUserId(mContext)
                } else {
                    advBean.userId = null
                }

                advBean.triggerTime = System.currentTimeMillis()

                advBean.deviceModel =
                    "手机厂商：" + getDeviceBrand() + "," + "手机型号：" + getSystemModel() + "," + "Android系统版本号：" + getSystemVersion()
                advBean.deviceId = getDeviceId(mContext)
                advBean.deviceNetwork = getNetworkStr(mContext)
                advBean.advItemId = it.id
                advBean.advPositionId = it.advPositionId
                params.add(advBean)
            }
            val type = getTokenType(mContext)
            val token = getTokenLogin(mContext)

            OkGo.post<Boolean>(ApiRoutes.ADVSDISPLAYRECORD)
                .tag(this).upJson(JSON.toJSONString(params))
                .headers(KeySet.AUTHORIZATION, "$type $token")
                .execute(object : BRequestCallback<Boolean>() {
                    override fun onMySuccess(data: Boolean) {
                    }

                    override fun onError(response: Response<Boolean>?) {
                        super.onError(response)
                    }
                })
        } catch (e: Exception) {

        }


    }

    /**
     * 点击统计
     */
    @Synchronized
    fun getAdvsclickrecord(context: Context,data: ADItemGroupsData) {
        try {
            var state = LocalDataUtils().getValue(context,KeySet.ADVSTATISTICSDATASTATE)
            if (state.equals("")) {
                LocalDataUtils().setValue(context,KeySet.ADVSTATISTICSDATASTATE, "1")
            } else {
                return
            }
            var elementPlanItems: MutableList<ADPlanItemsData> = ArrayList<ADPlanItemsData>()

            if (!data.advItems.isNullOrEmpty()) {
                for (it in data.advItems) {
                    elementPlanItems.add(it)
                }
            } else {
                return
            }
            var params: MutableList<AdvBean> = ArrayList<AdvBean>()
            for (it in elementPlanItems) {
                val advBean: AdvBean = AdvBean()

                if (isLogin(context)) {
                    advBean.userId = getUserId(context)
                } else {
                    advBean.userId = null
                }
//            advBean.triggerTime = it.triggerTime
                advBean.triggerTime = System.currentTimeMillis()
                advBean.deviceModel = it.deviceModel
                advBean.deviceId = it.deviceId
                advBean.deviceNetwork = it.deviceNetwork
                advBean.advItemId = it.id
                advBean.advPositionId = it.advPositionId

                params.add(advBean)
            }
            if (params.size <= 0) {
                return
            }
            val type = getTokenType(context)
            val token = getTokenLogin(context)

            OkGo.post<Boolean>(ApiRoutes.ADVSCLICKRECORD)
                .tag(this).upJson(JSON.toJSONString(params))
                .headers(KeySet.AUTHORIZATION, "$type $token")
                .execute(object : BRequestCallback<Boolean>() {
                    override fun onMySuccess(data: Boolean) {
                        if (data) {
                            LocalDataUtils().setValue(context,KeySet.ADVSTATISTICSDATA, "")
                        }
                        LocalDataUtils().setValue(context,KeySet.ADVSTATISTICSDATASTATE, "")
                    }

                    override fun onSuccessNullData() {
                        LocalDataUtils().setValue(context,KeySet.ADVSTATISTICSDATASTATE, "")
                    }

                    override fun onError(response: Response<Boolean>?) {
                        super.onError(response)
                        LocalDataUtils().setValue(context,KeySet.ADVSTATISTICSDATASTATE, "")
                    }
                })
        } catch (e: Exception) {

        }
    }

    /**
     * 保存点击数据
     */
    fun saveAdvStatisticsdData(activity: Context,advStatisticsdBean: ADPlanItemsData) {
        try {

            var elementPlanItemGroupsBean: ADItemGroupsData = ADItemGroupsData()
            elementPlanItemGroupsBean.advItems?.add(advStatisticsdBean)
            getAdvsclickrecord(activity,elementPlanItemGroupsBean)

        } catch (e: Exception) {

        }
    }


    /**
     * 点击统计
     */
    @Synchronized
    fun getButtonClickRecord(context:Context,data: ButtonClickRecordBean) {
        try {
            var state = LocalDataUtils().getValue(context,KeySet.BUTTONADVSTATISTICSDATASTATE)
            if (state.equals("")) {
                LocalDataUtils().setValue(context,KeySet.BUTTONADVSTATISTICSDATASTATE, "1")
            } else {
                return
            }


            if (data.buttonClickRecordData.isNullOrEmpty()) {
                return
            }

            val type = getTokenType(context)
            val token = getTokenLogin(context)

            OkGo.post<Boolean>(ApiRoutes.BUTTONCLICKRECORD)
                .tag(this).upJson(JSON.toJSONString(data.buttonClickRecordData))
                .headers(KeySet.AUTHORIZATION, "$type $token")
                .execute(object : BRequestCallback<Boolean>() {
                    override fun onMySuccess(data: Boolean) {
                        if (data) {
                            LocalDataUtils().setValue(context,KeySet.BUTTONADVSTATISTICSDATA, "")
                        }
                        LocalDataUtils().setValue(context,KeySet.BUTTONADVSTATISTICSDATASTATE, "")
                    }

                    override fun onSuccessNullData() {
                        LocalDataUtils().setValue(context,KeySet.BUTTONADVSTATISTICSDATA, "")
                        LocalDataUtils().setValue(context,KeySet.BUTTONADVSTATISTICSDATASTATE, "")
                    }

                    override fun onError(response: Response<Boolean>?) {
                        super.onError(response)
                        LocalDataUtils().setValue(context,KeySet.BUTTONADVSTATISTICSDATASTATE, "")
                    }
                })
        } catch (e: Exception) {

        }
    }

    fun changeData(
        context: Context,
        advStatisticsdBean: ButtonClickRecordData
    ): ButtonClickRecordData {
        var data: ButtonClickRecordData = advStatisticsdBean;
        try {

            if (isLogin(context)) {
                data.userId = getUserId(context)
            } else {
                data.userId = null;
            }

            data.triggerTime = System.currentTimeMillis()
//        data.deviceModel = Constants.DEVICE
            data.deviceModel =
                "手机厂商：" + getDeviceBrand() + "," + "手机型号：" + getSystemModel() + "," + "Android系统版本号：" + getSystemVersion()
            data.deviceId = getDeviceId(context)
            data.deviceNetwork = getNetworkStr(context)
        } catch (e: Exception) {

        }
        return data
    }

    /**
     * 保存点击数据
     */
    fun saveAdvStatisticsdData(context:Context,advStatisticsdBean: ButtonClickRecordData) {
        try {

            var elementPlanItemGroupsBean: ButtonClickRecordBean =
                ButtonClickRecordBean()
            elementPlanItemGroupsBean.buttonClickRecordData.add(advStatisticsdBean)
            getButtonClickRecord(context,elementPlanItemGroupsBean)

        } catch (e: Exception) {

        }
    }

    /**
     * 保存曝光数据
     */
    fun saveDisplayRecordData(
        mContext: Context,
        elementPlanItems: MutableList<ADPlanItemsData>,
        name: String
    ) {

        try {
            var displayRecordItems: MutableList<ADPlanItemsData> = ArrayList()
            displayRecordItems.addAll(elementPlanItems)
            ACache.get(mContext).getAsString(name )?.let {
                JSON.parseObject(it, ADItemGroupsData::class.java)?.let {
                    if (!it.advItems.isNullOrEmpty()) {
                        for (data in it.advItems) {
                            for (index in displayRecordItems.size - 1 downTo 0) {
                                if (displayRecordItems[index].getAdvItemId()
                                        .equals(data.getAdvItemId())
                                ) {
                                    displayRecordItems.removeAt(index)
                                }
                            }
                        }
                    }

                }
            }
//        Log.d("saveDisplayRecordData","displayRecordItems=="+displayRecordItems.size)
            if (displayRecordItems.size > 0) {
                AdvStatisticsUtils().getAdvsdisplayrecord(mContext, displayRecordItems)
            }
            var aDItemGroupsData: ADItemGroupsData = ADItemGroupsData()
            aDItemGroupsData.advItems?.addAll(elementPlanItems)
            saveJSON(mContext, name, Gson().toJson(aDItemGroupsData))
        } catch (e: Exception) {

        }
    }

    /**
     * 是否刷新广告数据
     */
    fun isRefreshADdata(
        mContext: Context,
        elementPlanItems: MutableList<ADPlanItemsData>,
        name: String
    ): Boolean {
        try {
            if (elementPlanItems.isEmpty()) {
                return true
            }
            var displayRecordItems: MutableList<ADPlanItemsData> = ArrayList()
            displayRecordItems.addAll(elementPlanItems)
            ACache.get(mContext).getAsString(name)?.let {
                JSON.parseObject(it, ADItemGroupsData::class.java)?.let {
                    if (!it.advItems.isNullOrEmpty()) {
                        for (data in it.advItems) {
                            for (index in displayRecordItems.size - 1 downTo 0) {
                                if (displayRecordItems[index].getId().equals(data.getId())) {
                                    displayRecordItems.removeAt(index)
                                }
                            }
                        }
                    }

                }
            }
            var aDItemGroupsData: ADItemGroupsData = ADItemGroupsData()
            aDItemGroupsData.advItems?.addAll(elementPlanItems)
            saveJSON(mContext, name, Gson().toJson(aDItemGroupsData))
            if (displayRecordItems.size > 0) {
                AdvStatisticsUtils().getAdvsdisplayrecord(mContext, displayRecordItems)
                return true
            }

            return false
        } catch (e: Exception) {
            return true
        }
    }

//    fun getDataString(): String {
//        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
//        val date = Date(System.currentTimeMillis())
//        return simpleDateFormat.format(date)
//    }

    /**
     * 删除缓存的广告数据
     *
     */
    fun delDisplayRecordData(mContext: Context) {
        try {
            removeJSON(mContext, FIRST_PAGE_BANNER_ADS+"DisplayRecord")
            removeJSON(mContext, FIRST_PAGE_START_ADS+"DisplayRecord")
            removeJSON(mContext, FIRST_PAGE_POP_ADS+"DisplayRecord")
            removeJSON(mContext, FIRST_PAGE_BLACK_ADS+"DisplayRecord")
            removeJSON(mContext, FIRST_PAGE_CONSULTANT_ADS+"DisplayRecord")
            removeJSON(mContext, FIRST_PAGE_AGENT_ADS+"DisplayRecord")
            removeJSON(mContext, FIRST_PAGE_AGENCY_ADS+"DisplayRecord")
            removeJSON(mContext, FIRST_PAGE_FINANCIAL_INSTITUTION_ADS+"DisplayRecord")
            removeJSON(mContext, FIRST_PAGE_SEARCH_ADS+"DisplayRecord")
            removeJSON(mContext, FIRST_PAGE_TOP_CAROUSEL_ADS+"DisplayRecord")
        } catch (e: Exception) {
        }
    }
    /**
     * 删除缓存的广告数据
     *
     */
    fun delDisplayAdvCache(mContext: Context) {
        try {
            removeJSON(mContext, FIRST_PAGE_BANNER_ADS)
            removeJSON(mContext, FIRST_PAGE_START_ADS)
            removeJSON(mContext, FIRST_PAGE_POP_ADS)
            removeJSON(mContext, FIRST_PAGE_BLACK_ADS)
            removeJSON(mContext, FIRST_PAGE_CONSULTANT_ADS)
            removeJSON(mContext, FIRST_PAGE_AGENT_ADS)
            removeJSON(mContext, FIRST_PAGE_AGENCY_ADS)
            removeJSON(mContext, FIRST_PAGE_FINANCIAL_INSTITUTION_ADS)
            removeJSON(mContext, FIRST_PAGE_SEARCH_ADS)
            removeJSON(mContext, FIRST_PAGE_TOP_CAROUSEL_ADS)



        } catch (e: Exception) {
        }
    }
    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    fun getSystemVersion(): String {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    fun getSystemModel(): String {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    fun getDeviceBrand(): String {
        return android.os.Build.BRAND;
    }

    /**
     * 获取当前网络状态
     */
    fun getNetworkStr(mContext: Context): String {
        var conMann: ConnectivityManager =
            mContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;
        mContext?.getSystemService(Context.CONNECTIVITY_SERVICE);
        mContext?.getSystemService(Context.CONNECTIVITY_SERVICE);
        var mobileNetworkInfo = conMann.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        var wifiNetworkInfo = conMann.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        var device_network: String = ""
        if (mobileNetworkInfo!!.isConnected()) {
            device_network = "移动网络"
        } else if (wifiNetworkInfo!!.isConnected()) {
            device_network = "WIFI网络"
        } else {
            device_network = "无网络"
        }
        return device_network
    }


    /**
     * 广告点击和曝光统计
     * 获取广告行为 triggerTime为时间，type：1是展示，2是点击,其他为广告默认属性
     * triggerTime: Date, advPositionId:string, advItemId: string, elementBoxConfigId: string,type:number
     */
     fun setAdBehavior(mContext: Context,str: String) {
        var aDPlanItemsData =
            com.alibaba.fastjson.JSONObject.parseObject(str, ADPlanItemsData::class.java)
        if (aDPlanItemsData.type == 1) {
            var params: MutableList<ADPlanItemsData> = ArrayList<ADPlanItemsData>()
            params.add(aDPlanItemsData)
            AdvStatisticsUtils().getAdvsdisplayrecord(mContext, params)

        } else {
            AdvStatisticsUtils().saveAdvStatisticsdData(mContext,
                AdvStatisticsUtils().changeData(
                    mContext,
                    aDPlanItemsData
                )
            )
        }

    }

    /**
     * 按钮点击统计
     */
     fun setButtonClickRecord(mContext: Context,str: String) {
        var buttonClickRecordData =
            com.alibaba.fastjson.JSONObject.parseObject(str, ButtonClickRecordData::class.java)
        AdvStatisticsUtils().saveAdvStatisticsdData(mContext,
            AdvStatisticsUtils().changeData(mContext,
                buttonClickRecordData
            )
        )
    }

}