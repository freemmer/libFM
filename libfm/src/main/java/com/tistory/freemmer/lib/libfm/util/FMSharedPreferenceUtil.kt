package com.tistory.freemmer.lib.libfm.util

import android.content.Context
import android.preference.PreferenceManager
import java.lang.ref.WeakReference

/**
 * Created by freemmer on 01/02/2019.
 * History
 *    - 01/02/2019 Create file
 */
class FMSharedPreferenceUtil private constructor(
    val context: Context
){

    companion object {
        private var weakReference: WeakReference<FMSharedPreferenceUtil>? = null
        fun instance(context: Context): FMSharedPreferenceUtil {
            if (weakReference?.get() == null) {
                weakReference = WeakReference(FMSharedPreferenceUtil(context))
            }
            return weakReference?.get()!!
        }
    }

    fun putSharedPreference(key: String, value: String?) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun putSharedPreference(key: String, value: Boolean) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun putSharedPreference(key: String, value: Int) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun putSharedPreference(key: String, value: Float) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    fun getSharedPreference(key: String): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(key, null)
    }

    fun getBooleanSharedPreference(key: String): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getBoolean(key, false)
    }

    fun getIntSharedPreference(key: String): Int {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getInt(key, 0)
    }

    fun getFloatSharedPreference(key: String): Float {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getFloat(key, 0f)
    }

}

