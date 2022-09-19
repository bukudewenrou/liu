package com.zcitc.advertisement.helper


import android.content.Context
import android.widget.Toast
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.zcitc.advertisement.ApiRoutes
import com.zcitc.advertisement.KeySet
import com.zcitc.advertisement.bean.Token
import com.zcitc.advertisement.utils.LocalDataUtils
import com.zcitc.httplibrary.net.JsonCallback

object RefreshTokenUtils {


    fun requestToken(context: Context,
                     success: (() -> Unit)? = null) {

        request(context, object : JsonCallback<Token>(Token::class.java) {
            override fun onSuccess(response: Response<Token>) {
                saveTokenInfo(context,response.body())
                success?.invoke()
            }

            override fun onError(response: Response<Token>) {
                Toast.makeText(context,"Token 获取失败",Toast.LENGTH_SHORT).show()
            }
        })
    }
    val BASIC = "Basic Y2xvdWRob3VzZS5uYmZjLmd6OjZEN0QzMDFDLUIwRTctNDdGNS1BNjIyLUQ0NTU4QjE2MUZGNQ=="
    val GRANT_TYPE = "client_credentials"
    val SCOPE = "sms.verify user.dynamiclogin alipay.login nbfcgz.public zjzww.public"
    val CLIENT_ID = "cloudhouse.nbfc.gz"
    val LOGIN_CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded"
    /**********************************************************************************************/
    private fun request(context: Context, t: JsonCallback<Token>) {
        OkGo.post<Token>(ApiRoutes.TOKEN)
                .tag(context)
                .headers(KeySet.AUTHORIZATION, BASIC)
                .headers(KeySet.CONTENT_TYPE, LOGIN_CONTENT_TYPE_VALUE)
                .params(KeySet.GRANT_TYPE, GRANT_TYPE)
                .params(KeySet.SCOPE, SCOPE)
                .params(KeySet.CLIENT_ID, CLIENT_ID)
                .execute(t)
    }

    private fun saveTokenInfo(context: Context,bean: Token) {
        LocalDataUtils().setValue(context,KeySet.LOGIN_TOKEN, bean.access_token)
        LocalDataUtils().setValue(context,KeySet.TOKEN_TYPE, bean.token_type)

    }



}