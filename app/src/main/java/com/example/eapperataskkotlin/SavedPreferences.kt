package com.example.eapperataskkotlin

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


object SavedPreferences {
    private const val PREF_USER_NAME = "userName"
    private const val PREF_USER_PASSWORD = "password"
    private const val PREF_USER_GPS = "gps"


    private fun getSharedPreferences(ctx: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun setUserName(ctx: Context, name: String) {
        val editor = getSharedPreferences(ctx).edit()
        editor.putString(PREF_USER_NAME, name)
        editor.apply()
    }

    fun getUserName(ctx: Context): String? {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, null)
    }


    fun setGPS(ctx: Context, state: String) {
        val editor = getSharedPreferences(ctx).edit()
        editor.putString(PREF_USER_GPS, state)
        editor.apply()
    }

    fun getGPS(ctx: Context): String? {
        return getSharedPreferences(ctx).getString(PREF_USER_GPS, "off")
    }
    fun setUserPassword(ctx: Context, userPass: String) {
        val editor = getSharedPreferences(ctx).edit()
        editor.putString(PREF_USER_PASSWORD, userPass)
        editor.apply()
    }

    fun getUserPass(ctx: Context): String? {
        return getSharedPreferences(ctx).getString(PREF_USER_PASSWORD, null)
    }

}