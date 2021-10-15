package com.example.myhomework.homework13.sharedprefs

import android.content.SharedPreferences


object SharedPrefsUtilss {

    lateinit var sharedPrefs: SharedPreferences

    fun putString(key: String, value: Boolean){

        sharedPrefs.edit().putBoolean(key, value).apply()
    }
}