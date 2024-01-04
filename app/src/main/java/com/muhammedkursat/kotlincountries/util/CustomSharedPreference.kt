package com.muhammedkursat.kotlincountries.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomSharedPreference {

    companion object {
        private val PREFERENCE_NAME = "preference_time"
        private var sharedPreference: SharedPreferences? = null

        @Volatile private var instance: CustomSharedPreference? = null

        private val lock = Any()
        operator fun invoke(context:Context) = instance ?: synchronized(lock){
            instance ?: createCustomShPr(context).also {
                instance = it
            }
        }

        fun createCustomShPr(context: Context): CustomSharedPreference {
            sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreference()
        }
    }

    fun saveTime(time: Long){
        sharedPreference?.edit(commit = true){
            putLong(PREFERENCE_NAME,time)
        }
    }
    fun getTime() = sharedPreference?.getLong(PREFERENCE_NAME,0)
}

