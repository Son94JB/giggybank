package com.ssafy.template.config

import com.d208.giggy.di.App.Companion.sharedPreferences
import com.d208.giggy.utils.Utils.COOKIES_KEY_NAME

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class XAccessTokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val jwtToken: String? = sharedPreferences.getString(COOKIES_KEY_NAME)
        if (jwtToken != null) {
            builder.addHeader("X-ACCESS-TOKEN", jwtToken)
        }
        return chain.proceed(builder.build())
    }
}