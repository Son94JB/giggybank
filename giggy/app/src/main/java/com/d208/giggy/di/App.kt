package com.d208.giggy.di

import android.app.Application
import com.d208.giggy.R
import com.google.firebase.FirebaseApp
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    companion object {
        private lateinit var application: App
        fun getInstance() : App = application
    }


    override fun onCreate(){
        super.onCreate()
        application = this

        FirebaseApp.initializeApp(this)

        val appKey = getString(R.string.kakao_native_key)
        KakaoSdk.init(this, "$appKey")
    }
}