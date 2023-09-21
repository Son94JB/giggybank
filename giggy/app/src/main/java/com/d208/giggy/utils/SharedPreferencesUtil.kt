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


   fun addId(id : String){
       val editor = preferences.edit()
       editor.putString("id", id)
       editor.apply()
   }




}

