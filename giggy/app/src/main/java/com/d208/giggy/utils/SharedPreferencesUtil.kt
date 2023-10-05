package com.d208.giggy.utils

import android.content.Context
import android.content.SharedPreferences
import com.d208.giggy.di.App
import com.d208.giggy.utils.Utils.COOKIES_KEY_NAME


class SharedPreferencesUtil(context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(App.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun getString(key: String): String {
        return preferences.getString(key, null) ?: ""
    }
    fun getLong(key: String): Long {
        return preferences.getLong(key, -1)
    }

    fun getInt(key : String) : Int {
        return preferences.getInt(key, 0)
    }

    fun removeUser() {
        preferences.edit().clear().apply()
    }


   fun addId(id : String){
       val editor = preferences.edit()
       editor.putString("id", id)
       editor.apply()
   }
    fun addAccount(account : String){
        val editor = preferences.edit()
        editor.putString("account", account)
        editor.apply()
    }
    fun updateMoney(money : Int){
        val editor = preferences.edit()
        editor.putInt("money", money)
        editor.apply()
    }


    fun addUserCookie(token : String) {
        val editor = preferences.edit()
        editor.putString(COOKIES_KEY_NAME, token)
        editor.apply()
    }
    fun getUserCookie(): MutableSet<String>? {
        return preferences.getStringSet(COOKIES_KEY_NAME, HashSet())
    }





}

