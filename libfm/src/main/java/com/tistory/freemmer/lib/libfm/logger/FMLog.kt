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
        private var weakReference: WeakReference<FMILog>? = null

        fun initialze(printLogLevel: FMILog.LEVEL, tagName: String) {
            weakReference = WeakReference(FMLogCatImpl.build(printLogLevel, tagName, null))
        }

        fun v(vararg args: Any) = weakReference?.get()?.v(args)
        fun d(vararg args: Any) = weakReference?.get()?.d(args)
        fun i(vararg args: Any) = weakReference?.get()?.i(args)
        fun w(vararg args: Any) = weakReference?.get()?.w(args)
        fun e(vararg args: Any) = weakReference?.get()?.e(args)
        fun exception(e: Exception) = weakReference?.get()?.exception(e)

        fun printDeviceInfo(context: Context) = weakReference?.get()?.printDeviceInfo(context)
        fun printMemory() = weakReference?.get()?.printMemory()
        fun printArray(logLevel: FMILog.LEVEL, title: String, container: Array<Any>)
                = weakReference?.get()?.printArray(FMILog.LEVEL.DEBUG, title, container)
        fun printMap(logLevel: FMILog.LEVEL, title: String, map: Map<String, String>)
                = weakReference?.get()?.printMap(FMILog.LEVEL.DEBUG, title, map)
    }

}
