package com.example.myhomework.homework13.sharedprefs

import android.content.SharedPreferences


object SharedPrefsUtils {

    lateinit var sharedPrefs: SharedPreferences

    fun putBoolen(key: String, value: Boolean){

        sharedPrefs.edit().putBoolean(key, value).apply()
    }

    fun getBoolen(key: String) = sharedPrefs.getString(key, null)
}