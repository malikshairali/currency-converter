package com.example.currencyconverter.manager

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(private val context: Context) {
    private val preferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREFERENCES_ACCOUNT, Context.MODE_PRIVATE)
    }

    fun setLastRefreshedTime(millis: Long) {
        preferences.edit().putLong(KEY_LAST_REFRESHED_TIME, millis).apply()
    }

    fun getLastRefreshedTime(): Long {
        return preferences.getLong(KEY_LAST_REFRESHED_TIME, -1L)
    }

    fun setCurrencyConversionRate(currency: String, rate: Float) {
        preferences.edit().putFloat(currency, rate).apply()
    }

    fun getCurrencyConversionRate(currency: String): Float {
        return preferences.getFloat(currency, -1F)
    }

    companion object {
        private const val PREFERENCES_ACCOUNT = "PREFERENCES_ACCOUNT"
        private const val KEY_LAST_REFRESHED_TIME = "KEY_LAST_REFRESHED_TIME"
    }
}