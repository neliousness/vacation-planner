package com.pulsar.vacationplanner.util

import android.content.Context
import android.content.SharedPreferences
import com.pulsar.vacationplanner.util.Constants.ONBOARDED

class PreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(ONBOARDED, Context.MODE_PRIVATE)


    fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }
}
