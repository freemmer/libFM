package com.tistory.freemmer.lib.libfm.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import java.lang.ref.WeakReference
import android.content.Context.POWER_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.os.Build.VERSION_CODES
import android.os.Build.VERSION
import android.view.Display
import android.content.Context.POWER_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.hardware.display.DisplayManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.support.v4.content.ContextCompat.getSystemService








/**
 * Created by freemmer on 29/01/2019.
 * History
 *    - 29/01/2019 Create file
 */
class FMDeviceUtil private constructor(
    private val context: Context
){
    companion object {
        private var weakReference: WeakReference<FMDeviceUtil>? = null

        fun instance(context: Context): FMDeviceUtil {
            if (weakReference?.get() == null) {
                weakReference = WeakReference(FMDeviceUtil(context))
            }
            return weakReference?.get()!!
        }
    }

    fun getOSVersion(): String {
        return Build.VERSION.RELEASE
    }

    fun isScreenOn() : Boolean {
        val dm = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT_WATCH) {
            for (display in dm.displays) {
                if (display.state != Display.STATE_OFF) {
                    return true
                }
            }
            return false
        } else {
            val powerManager = context.getSystemService(POWER_SERVICE) as PowerManager?
            if (powerManager!!.isScreenOn) {
                return true
            }
            return false
        }
    }

    @SuppressLint("MissingPermission")
    fun isWiFiConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
        } else {
            connectivityManager.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
        }
    }

    @SuppressLint("MissingPermission")
    fun isOnline() {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null) {
            // connected to the internet
            when (activeNetwork.type) {
                ConnectivityManager.TYPE_WIFI -> {
                }
                ConnectivityManager.TYPE_MOBILE -> {
                }
                else -> {
                }
            }// connected to wifi
            // connected to mobile data
        } else {
            // not connected to the internet
        }
    }



    fun getPackageName(): String {
        return context.applicationContext.packageName
    }

    fun getApplicationInfo(): ApplicationInfo {
        return context.applicationContext.applicationInfo
    }

    fun getAppLabel() : String {
        return getApplicationInfo().loadLabel(context.applicationContext.packageManager).toString()
    }

    fun getMetaData(): Bundle {
        return context.applicationContext.packageManager
            .getApplicationInfo(getPackageName() , PackageManager.GET_META_DATA).metaData
    }

    fun getLaunchIntent(): Intent {
        return context.packageManager.getLaunchIntentForPackage(getPackageName()) as Intent
    }

    fun getResourceID(variableName:String, resourceType: String): Int {
        return context.resources.getIdentifier(variableName, resourceType, getPackageName())
    }

}


