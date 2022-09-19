package com.zcitc.advertisement




interface KeySet {
    companion object {



        val AUTHORIZATION = "Authorization"
        val ENCRYPTED = "Encrypted"
        val GRANT_TYPE = "grant_type"
        val SCOPE = "scope"
        val CLIENT_ID = "client_id"
        val CONTENT_TYPE = "Content-Type"
        val USERID = "userId"
        val LOGIN_TOKEN = "用于登录和获取验证码"
        val API_TOKEN = "用于内部接口交互"
        val TOKEN_TYPE = "TOKEN的类型"
        val ADVSTATISTICSDATA = "广告点击统计"
        val ADVSTATISTICSDATASTATE = "广告点击统计状态"
        val BUTTONADVSTATISTICSDATASTATE = "Button点击统计状态"
        val BUTTONADVSTATISTICSDATA = "Button点击统计"
        var CONFIGS_VERSION = "广告配置版本号"
    }
}
