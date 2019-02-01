package com.tistory.freemmer.lib.libfm.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager
import java.lang.ref.WeakReference

/**
 * Created by freemmer on 01/02/2019.
 * History
 *    - 01/02/2019 Create file
 */
class FMDisplayUtil private constructor(
    val context: Context
){
    companion object {
        private var weakReference: WeakReference<FMDisplayUtil>? = null
        fun instance(context: Context): FMDisplayUtil {
            if (weakReference?.get() == null) {
                weakReference = WeakReference(FMDisplayUtil(context))
            }
            return weakReference?.get()!!
        }
    }

    private var screenWidth = 0
    private var screenHeight = 0

    fun dipToPixel(dp: Int): Int {
        return Math.round(dp * Resources.getSystem().displayMetrics.density)
    }

    fun pixelToDip(value: Int): Float {
        return (value / (Resources.getSystem().displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)).toFloat()
    }

    fun getScreenHeight(): Int {
        if (screenHeight == 0) {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            screenHeight = size.y
        }
        return screenHeight
    }

    fun getScreenWidth(): Int {
        if (screenWidth == 0) {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            screenWidth = size.x
        }
        return screenWidth
    }

}

