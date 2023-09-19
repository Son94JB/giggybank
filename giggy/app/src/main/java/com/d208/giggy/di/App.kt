package com.d208.giggy.di

import android.app.Application
import com.d208.giggy.R
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        private lateinit var application: App
        fun getInstance() : App = application
    }

    override fun onCreate(){
        super.onCreate()
        application = this
        val appKey = getString(R.string.kakao_native_key)
        KakaoSdk.init(this, "$appKey")
    }
}