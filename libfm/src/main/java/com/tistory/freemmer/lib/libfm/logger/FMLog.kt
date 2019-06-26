package com.tistory.freemmer.lib.libfm.logger

import android.content.Context
import com.tistory.freemmer.lib.libfm.logger.impl.FMLogCatImpl
import java.lang.ref.WeakReference

/**
 * Created by freemmer on 28/03/2019.
 * History
 *    - 28/03/2019 Create file
 */
class FMLog {

    companion object {
        private val STRACK_TRACE_POS = 4
        //private var weakReference: WeakReference<FMILog>? = null
        private var log: FMILog? = null

        fun initialze(printLogLevel: FMILog.LEVEL, tagName: String) {
            //weakReference = WeakReference(FMLogCatImpl.build(printLogLevel, tagName, null))
            log = FMLogCatImpl.build(printLogLevel, tagName, null)
        }

        fun v(vararg args: Any) = run { log?.vTag(getInfo(), *args)}
        fun d(vararg args: Any) = run { log?.dTag(getInfo(), *args)}
        fun i(vararg args: Any) = run { log?.iTag(getInfo(), *args)}
        fun w(vararg args: Any) = run { log?.wTag(getInfo(), *args)}
        fun e(vararg args: Any) = run { log?.eTag(getInfo(), *args)}
        fun exception(e: Exception) = run { log?.exceptionTag(getInfo(), e)}

        fun printDeviceInfo(context: Context) = log?.printDeviceInfo(context)
        fun printMemory() = log?.printMemory()
        fun printArray(logLevel: FMILog.LEVEL, title: String, container: Array<Any>)
                = log?.printArray(FMILog.LEVEL.DEBUG, title, container)
        fun printMap(logLevel: FMILog.LEVEL, title: String, map: Map<String, String>)
                = log?.printMap(FMILog.LEVEL.DEBUG, title, map)

        /*fun v(vararg args: Any) = run { weakReference?.get()?.vTag(getInfo(), *args)}
        fun d(vararg args: Any) = run {weakReference?.get()?.dTag(getInfo(), *args)}
        fun i(vararg args: Any) = run {weakReference?.get()?.iTag(getInfo(), *args)}
        fun w(vararg args: Any) = run {weakReference?.get()?.wTag(getInfo(), *args)}
        fun e(vararg args: Any) = run {weakReference?.get()?.eTag(getInfo(), *args)}
        fun exception(e: Exception) = run {weakReference?.get()?.exceptionTag(getInfo(), e)}

        fun printDeviceInfo(context: Context) = weakReference?.get()?.printDeviceInfo(context)
        fun printMemory() = weakReference?.get()?.printMemory()
        fun printArray(logLevel: FMILog.LEVEL, title: String, container: Array<Any>)
                = weakReference?.get()?.printArray(FMILog.LEVEL.DEBUG, title, container)
        fun printMap(logLevel: FMILog.LEVEL, title: String, map: Map<String, String>)
                = weakReference?.get()?.printMap(FMILog.LEVEL.DEBUG, title, map)*/

        private fun getInfo(): String {
            val thread: Thread = Thread.currentThread()
            return String.format("(%s:%d):%s() on %s"
                , thread.stackTrace[STRACK_TRACE_POS].fileName
                , thread.stackTrace[STRACK_TRACE_POS].lineNumber
                , thread.stackTrace[STRACK_TRACE_POS].methodName
                , thread.name )
        }
    }
}
