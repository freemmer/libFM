package com.tistory.freemmer.lib.libfm.logger.impl

import android.content.Context
import android.content.res.Configuration
import android.os.Debug
import android.util.DisplayMetrics
import android.util.Log
import com.tistory.freemmer.lib.libfm.logger.FMILog
import com.tistory.freemmer.lib.libfm.logger.FMILogDestination
import java.io.PrintWriter
import java.io.StringWriter
import java.text.DecimalFormat

/**
 * Created by freemmer on 22/01/2019.
 * History
 *    - 22/01/2019 Create file
 */
class FMLogCatImpl private constructor(
) : FMILog {

    companion object {
        fun build(printLogLevel: FMILog.LEVEL, tagName: String, dest: FMILogDestination?): FMLogCatImpl
                = FMLogCatImpl.Builder(printLogLevel, tagName, dest).build()
    }

    private lateinit var tagName: String
    private lateinit var printLevel: FMILog.LEVEL
    private var logDestination: FMILogDestination? = null

    class Builder(private val printLogLevel: FMILog.LEVEL
                  , private val tagName: String
                  , private val dest: FMILogDestination?
    ) {
        fun build(): FMLogCatImpl {
            val logImple = FMLogCatImpl()
            logImple.setPrintInfo(printLogLevel, tagName, dest)
            return logImple
        }

        companion object {
            fun build(printLogLevel: FMILog.LEVEL, tagName: String, dest: FMILogDestination?, init: Builder.() -> Unit)
                    = Builder(printLogLevel, tagName, dest).build()
        }
    }

    override fun setPrintInfo(printLogLevel: FMILog.LEVEL, tagName: String, dest: FMILogDestination?) {
        this.tagName = tagName
        this.printLevel = printLogLevel
        this.logDestination = dest
    }

    override fun print(logLevel: FMILog.LEVEL, vararg args: Any?) {
        if (printLevel.code > logLevel.code) return

        val argsCopy = arrayOfNulls<Any>(args.size - 1)
        System.arraycopy(args, 1, argsCopy, 0, argsCopy.size)
        val strMsg = try {
            String.format(args[0] as String, *argsCopy)
        } catch (e: Exception) {
            args[0] as String
        }
        when (logLevel) {
            FMILog.LEVEL.VERBOSE    -> Log.v(tagName, strMsg)
            FMILog.LEVEL.DEBUG      -> Log.d(tagName, strMsg)
            FMILog.LEVEL.INFO       -> Log.i(tagName, strMsg)
            FMILog.LEVEL.WARN       -> Log.w(tagName, strMsg)
            FMILog.LEVEL.ERROR      -> Log.e(tagName, strMsg)
            else -> { }
        }
    }


    private fun printDetail(logLevel: FMILog.LEVEL, vararg args: Any) {
        val thread: Thread = Thread.currentThread()
        val strLog: String = String.format("[(%s:%d):%s() on %s] %s"
            , thread.stackTrace[FMILog.STRACK_TRACE_POS].fileName
            , thread.stackTrace[FMILog.STRACK_TRACE_POS].lineNumber
            , thread.stackTrace[FMILog.STRACK_TRACE_POS].methodName
            , thread.name
            , args[0])
        val argsCopy = arrayOfNulls<Any>(args.size)
        argsCopy[0] = strLog
        System.arraycopy(args, 1, argsCopy, 1, argsCopy.size - 1)
        print(logLevel, *argsCopy)
    }

    override fun v(vararg args: Any) = printDetail(FMILog.LEVEL.VERBOSE, *args)
    override fun d(vararg args: Any) = printDetail(FMILog.LEVEL.DEBUG, *args)
    override fun i(vararg args: Any) = printDetail(FMILog.LEVEL.INFO, *args)
    override fun w(vararg args: Any) = printDetail(FMILog.LEVEL.WARN, *args)
    override fun e(vararg args: Any) = printDetail(FMILog.LEVEL.ERROR, *args)
    override fun exception(e: Exception) = printException(e)

    override fun printDeviceInfo(context: Context) {
        val density = context.resources.displayMetrics.densityDpi
        val strScreenType = when (density) {
            DisplayMetrics.DENSITY_LOW      -> "LDPI"
            DisplayMetrics.DENSITY_MEDIUM   -> "MDPI"
            DisplayMetrics.DENSITY_HIGH     -> "HDPI"
            DisplayMetrics.DENSITY_XHIGH    -> "XHDPI"
            DisplayMetrics.DENSITY_XXHIGH   -> "XXHDPI"
            else -> "default"
        }

        val strScreenLayout = when (
            context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) {
            Configuration.SCREENLAYOUT_SIZE_SMALL   -> "Small"
            Configuration.SCREENLAYOUT_SIZE_NORMAL  -> "Normal"
            Configuration.SCREENLAYOUT_SIZE_LARGE   -> "Large"
            Configuration.SCREENLAYOUT_SIZE_XLARGE  -> "Xlarge"
            else -> "Default"
        }
        print(FMILog.LEVEL.DEBUG, "╓─ Device Information ──────────────────────────────────────")
        print(FMILog.LEVEL.DEBUG, "║ Screen Density Type : %s, Density(%d)", strScreenType, density)
        print(FMILog.LEVEL.DEBUG, "║ Screen Layout Size : %s size", strScreenLayout)
        print(FMILog.LEVEL.DEBUG, "╙──────────────────────────────────────────────────────────")
    }

    override fun printMemory() {
        val MB = 1024.0 * 1024.0
        val dFormat = DecimalFormat("##0.00")
        print(FMILog.LEVEL.DEBUG, "╓─ MEMORY ────────────────────────────────────────────")
        print(FMILog.LEVEL.DEBUG, "║ Max : " + dFormat.format(Runtime.getRuntime().maxMemory() / MB))
        print(FMILog.LEVEL.DEBUG, "║ Total : " + dFormat.format(Runtime.getRuntime().totalMemory() / MB))
        print(FMILog.LEVEL.DEBUG, "║ Free : " + dFormat.format(Runtime.getRuntime().freeMemory() / MB))
        print(FMILog.LEVEL.DEBUG, "║ NativeHeepTotal : " + dFormat.format(Debug.getNativeHeapSize() / MB))
        print(FMILog.LEVEL.DEBUG, "║ NativeHeepAllocated : " + dFormat.format(Debug.getNativeHeapAllocatedSize() / MB))
        print(FMILog.LEVEL.DEBUG, "║ NativeHeepFree : " + dFormat.format(Debug.getNativeHeapFreeSize() / MB))
        print(FMILog.LEVEL.DEBUG, "╙─────────────────────────────────────────────────────")
    }

    override fun printArray(logLevel: FMILog.LEVEL, title: String, container: Array<Any>) {
        printDetail(FMILog.LEVEL.DEBUG, "╓─ $title ────────────────────────────────────────────────────")
        for (obj in container) {
            printDetail(FMILog.LEVEL.DEBUG, "║ $obj")
        }
        var sAddLine = ""
        for (i in 0 until title.length + 2) {
            sAddLine += "─"
        }
        printDetail(FMILog.LEVEL.DEBUG, "╙───────────────────────────────────────────────────── $sAddLine")
    }

    override fun printMap(logLevel: FMILog.LEVEL, title: String, map: Map<String, String>) {
        printDetail(FMILog.LEVEL.DEBUG, "╓─ $title ────────────────────────────────────────────────────")
        for ((key, value) in map) {
            printDetail(FMILog.LEVEL.DEBUG, "║ $key : $value")
        }
        var sAddLine = ""
        for (i in 0 until title.length + 2) {
            sAddLine += "─"
        }
        printDetail(FMILog.LEVEL.DEBUG, "╙───────────────────────────────────────────────────── $sAddLine")
    }

    override fun printException(e: Exception) {
        val sw = StringWriter()
        e.printStackTrace(PrintWriter(sw))
        printDetail(FMILog.LEVEL.ERROR, sw.toString())
    }

}
