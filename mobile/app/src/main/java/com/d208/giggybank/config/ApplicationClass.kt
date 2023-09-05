package com.d208.giggybank.config

import android.app.Application
import com.d208.giggybank.util.SharedPreferencesUtil

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ssafy.template.config.AddCookiesInterceptor
import com.ssafy.template.config.ReceivedCookiesInterceptor
import com.ssafy.template.config.XAccessTokenInterceptor

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApplicationClass : Application() {


    companion object {


        lateinit var sharedPreferences: SharedPreferencesUtil


        lateinit var retrofit: Retrofit

        //쿠키 관련
        const val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"
        const val SHARED_PREFERENCES_NAME = "SSAFY_TEMPLATE_APP"
        const val COOKIES_KEY_NAME = "cookies"

        //서버 URL

        const val API_URL = ""

        // 화면 사이즈 정보
        var dpHeight = 0.0F
        var dpWidth = 0.0F


    }


    override fun onCreate() {
        super.onCreate()

        sharedPreferences = SharedPreferencesUtil(applicationContext)

        // 레트로핏 인스턴스 생성
        initRetrofitInstance()


        // 화면 사이즈 계산
        val density = resources.displayMetrics.density
        dpHeight = resources.displayMetrics.heightPixels / density
        dpWidth = resources.displayMetrics.widthPixels / density
    }

    private fun initRetrofitInstance() {
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
//            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
            .addInterceptor(AddCookiesInterceptor())  //쿠키 전송
            .addInterceptor(ReceivedCookiesInterceptor()) //쿠키 추출
            .build()

        // retrofit 이라는 전역변수에 API url, 인터셉터, Gson을 넣어주고 빌드해주는 코드
        // 이 전역변수로 http 요청을 서버로 보내면 됩니다.
        retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val gson : Gson = GsonBuilder()
        .setLenient()
        .create()


}