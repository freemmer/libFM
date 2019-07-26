package com.tistory.freemmer.lib.libfm.util

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.Context.POWER_SERVICE
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.os.Build.VERSION_CODES
import android.os.Build.VERSION
import java.lang.ref.WeakReference
import android.view.Display
import android.hardware.display.DisplayManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
//import android.support.annotation.IntRange
import android.provider.Settings
//import android.support.v4.app.ActivityCompat
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat


/**
 * Created by freemmer on 29/01/2019.
 * History
 *    - 29/01/2019 Create file
 */
class FMDeviceUtil private constructor(
    private val context: Context
) {

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

    fun getManufacture(): String {
        return Build.MANUFACTURER
    }

    fun getBrand(): String {
        return Build.BRAND
    }

    fun getProductName(): String {
        return Build.PRODUCT
    }

    fun getModelName(): String {
        return Build.MODEL
    }

    @SuppressLint("HardwareIds")
    fun getUDID(): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun getPackageName(): String {
        return context.applicationContext.packageName
    }

    fun getApplicationInfo(): ApplicationInfo {
        return context.applicationContext.applicationInfo
    }

    fun getAppVersion(): String {
        return context.packageManager.getPackageInfo(context.packageName, 0).versionName
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

    fun getFirmware(): String {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
            !== PackageManager.PERMISSION_GRANTED) {
            ""
        } else telephonyManager.deviceSoftwareVersion
    }


    /**
     * @param resourceType  'drawable' etc
     */
    fun getResourceID(variableName:String, resourceType: String): Int {
        return context.resources.getIdentifier(variableName, resourceType, getPackageName())
    }

    @SuppressLint("HardwareIds")
    fun getPhoneNum(): String? {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        var strPhoneNO: String? = null
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            strPhoneNO = telephonyManager.line1Number
        }
        return strPhoneNO
    }

    fun getPhoneIMEI(): String? {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        var strPhoneIMEI: String? = null
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            if (VERSION.SDK_INT >= VERSION_CODES.O) {
                strPhoneIMEI = telephonyManager.imei
            } else {
                strPhoneIMEI = telephonyManager.deviceId
            }
        }
        return strPhoneIMEI
    }

    fun isTabletDevice(): Boolean {
        val conf = context.resources.configuration
        val screenSize = conf.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
        return screenSize > Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    fun isScreenOn() : Boolean {
        val dm = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT_WATCH) {
            for (display in dm.displays) {
                if (display.state != Display.STATE_OFF) return true
            }
            return false
        } else {
            val powerManager = context.getSystemService(POWER_SERVICE) as PowerManager?
            if (powerManager!!.isScreenOn) return true
            return false
        }
    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    //@IntRange(from = 0, to = 2)
    fun getNetworkConnectionType(): Int {
        var result = 0 // Returns connection type. 0: none; 1: mobile data; 2: wifi
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = 2
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = 1
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = 2
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = 1
                    }
                }
            }
        }
        return result
    }




}


