package com.d208.giggybank.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import com.d103.asaf.common.config.BaseActivity
import com.d208.giggybank.R
import com.d208.giggybank.databinding.ActivityMainBinding
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val keyHash = Utility.getKeyHash(this)
        Log.e("Key", "keyHash: $keyHash")
//        /** KakaoSDK init */
////        KakaoSdk.init(this, this.getString(R.string.kakao_app_key))

        // 액티비티를 세로모드로 고정
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setupNavHost()
    }
    private fun setupNavHost() {
        // NavHostFragment를 가져와서 설정합니다.
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
    }
}