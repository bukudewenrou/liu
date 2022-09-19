package com.zcitc.advertisement.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.view.View
import android.view.WindowManager
import com.zcitc.advertisement.KeySet
import com.zcitc.advertisement.adv.AdvStatisticsUtils
import com.zcitc.baselibrary.StatusBarUtil
import com.zcitc.utilslibrary.ACache
import com.zcitc.utilslibrary.GetDeviceId
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


fun saveJSON(context: Context, name: String, data: String) {
    try{
        ACache.get(context).put(name, data, 1 * ACache.TIME_DAY)
    }catch (e: Exception) {

    }

}
fun removeJSON(context: Context, name: String) {
    try{
        ACache.get(context).remove(name)
    }catch (e: Exception) {

    }

}
fun isExistJsonStr(context: Context, name: String): Boolean {
    try{
        if (ACache.get(context).getAsString(name).isNullOrEmpty()) {
            return false
        }
    }catch (e: Exception) {
        return false
    }

    return true
}
/**
 * 是否显示倒计时广告
 */
fun isShowAd(startTimeTicks: String, endTimeTicks: String): Boolean {

    var isShow = false
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    try {
        val startTimeLong = if (startTimeTicks.length > 10) startTimeTicks.toLong() else (startTimeTicks + "000").toLong()
        val endTimeLong = if (endTimeTicks.length > 10) endTimeTicks.toLong() else (endTimeTicks + "000").toLong()
        val currentTime = simpleDateFormat.parse(simpleDateFormat.format(Date()))

        if (currentTime.time in (startTimeLong + 1) until endTimeLong) {
            isShow = true
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return isShow
}


/**
 * 将dp转为px
 *
 * @param context 上下文
 * @param dpValue 需要转换的dp数值
 * @return px数值
 */
fun dip2px(context: Context, dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}
fun dp2px(context: Context, dps: Int): Int = (context.resources.displayMetrics.density * dps).roundToInt()

fun isNullOrEmptyStr(str: String?) :Boolean{
    if(getNullStr(str).isNullOrEmpty()){
        return true
    }
    return false
}


fun getNullStr(str: String?):String{
    if(str.isNullOrEmpty()) return "" else return str
}

fun getNullStr(str: Int?):Int{
    if(str == null) return 0 else return str
}

fun getNullStr(str: Long?):Long{
    if(str == null) return 0 else return str
}

fun <T> getNullStr(str: MutableList<T>?):MutableList<T>{
    if(str == null) return ArrayList<T>() else return str
}

fun getDeviceParams(context:Context) : HashMap<String, String>{
    val params = HashMap<String, String>()
    //deviceModel, deviceId ,deviceNetwork
    params["deviceModel"] =  "手机厂商：" + android.os.Build.BRAND + "," + "手机型号：" + android.os.Build.MODEL + "," + "Android系统版本号：" + android.os.Build.VERSION.RELEASE
    params["deviceId"] = getDeviceId(context)
    params["deviceNetwork"] = AdvStatisticsUtils().getNetworkStr(context)
    return params

}

/**
 * 获取设备唯一标识符
 *
 * @param context
 * @return
 */
fun getDeviceId(context: Context?): String {
    var deviceId = LocalDataUtils().getValue(context!!, "DEVICEID")
    if (deviceId != null && "" != deviceId) {
        return deviceId
    }
    val s = StringBuffer()
    //如果以上搜没有获取相应的则自己生成相应的UUID作为相应设备唯一标识符
//        if (s == null || s.length() <= 0) {
    val uuid = UUID.randomUUID()
    deviceId = uuid.toString().replace("-", "")
    s.append(deviceId)
    //        }
    //为了统一格式对设备的唯一标识进行md5加密 最终生成32位字符串
    val md5: String = GetDeviceId.getMD5(s.toString(), false)
    if (s.length > 0) {
        //持久化操作, 进行保存到SD卡中
//            saveDeviceID(md5, context);
        LocalDataUtils().setValue(context, "DEVICEID", md5)
    }
    return md5
}

fun getTokenType(context: Context) : String?{
  return  LocalDataUtils().getValue(context,KeySet.TOKEN_TYPE)
}

fun getTokenLogin(context: Context) : String?{
  return  LocalDataUtils().getValue(context,KeySet.LOGIN_TOKEN)
}

fun getUserId(context: Context) :String?{
    return  LocalDataUtils().getValue(context,KeySet.USERID)
}

 fun takePhone(phone: String,context: Activity) {
    val uri = Uri.parse("tel:$phone") //设置要操作的路径
    val it = Intent()
    it.action = Intent.ACTION_DIAL
    it.data = uri
     context.startActivity(it)
//    AppManager.getAppManager().startActivity(it)

}
fun getApiToken(context: Context) : String?{
    return  LocalDataUtils().getValue(context,KeySet.API_TOKEN)
}
fun isLogin(context: Context) : Boolean{
    if(!getApiToken(context).isNullOrEmpty()){
        return  true
    }
    return  false
}



fun setStatusBarHeight(toolbarView: View,context:Context,windowManager: WindowManager) {
    val height = StatusBarUtil.getStatusBarHeight(context)
    val display = windowManager.defaultDisplay
    val layoutParams = toolbarView.layoutParams
    layoutParams.height = height
    layoutParams.width = display.width
    toolbarView.layoutParams = layoutParams
}







