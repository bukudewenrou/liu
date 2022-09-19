package com.zcitc.advertisement.utils

import android.content.Context
import com.zcitc.utilslibrary.ACache

class LocalDataUtils {
    fun  getValue(context: Context,name : String) : String{
        if(ACache.get(context).getAsString(name).isNullOrEmpty()){
            return ""
        }
        return ACache.get(context).getAsString(name)
    }

    fun  setValue(context: Context,name : String, value: String) {
        ACache.get(context).put(name, value)
    }


}