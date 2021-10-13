package com.example.currency.sharedprefs

import android.content.SharedPreferences


object SharedPrefsUtils {

    lateinit var sharedPrefs: SharedPreferences

    fun putString(key: String, value: String){

        sharedPrefs.edit().putString(key, value).apply()
    }
}