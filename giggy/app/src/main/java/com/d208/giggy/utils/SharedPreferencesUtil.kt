package com.d208.giggy.utils

import android.content.Context
import android.content.SharedPreferences
import com.d208.giggy.di.App


class SharedPreferencesUtil(context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(App.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun getString(key: String): String {
        return preferences.getString(key, null) ?: ""
    }
    fun getLong(key: String): Long {
        return preferences.getLong(key, -1)
    }

    fun removeUser() {
        preferences.edit().clear().apply()
    }


    fun addAccessToken(access : String){
        val editor = preferences.edit()
        editor.putString("accessToken", access)
        editor.apply()
    }

    fun addRefreshToken(refresh : String){
        val editor = preferences.edit()
        editor.putString("refreshToken", refresh)
        editor.apply()
    }

    fun addFCMToken(fcm : String){
        val editor = preferences.edit()
        editor.putString("fcmToken", fcm)
        editor.apply()
    }

    fun addFCMFlag(flag : Boolean){
        val editor = preferences.edit()
        editor.putBoolean("fcmFlag", flag)
        editor.apply()
    }

    fun getBoolean(key : String) : Boolean {
        return preferences.getBoolean(key, true)
    }


}

