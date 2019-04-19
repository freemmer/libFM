package com.tistory.freemmer.lib.libfm.logger

import android.content.Context

/**
 * Created by freemmer on 22/01/2019.
 * History
 *    - 22/01/2019 Create file
 */
interface FMILog {

//    companion object {
//        // Thread, Class, Function정보를 가지고 있는 Stack Trace의 위치.
//        val STRACK_TRACE_POS = 4
//    }

    enum class LEVEL (code: Int, internal var sName: String) {
        OFF     (100, "LOG OFF"),
        VERBOSE (1, "VERBOSE"),
        DEBUG   (2, "DEBUG"),
        INFO    (3, "INFO"),
        WARN    (4, "WARN"),
        ERROR   (5, "ERROR");

        companion object {
            fun findByCode(nCode: Int): LEVEL? {
                for (v in values()) {
                    if (v.code == nCode) return v
                }
                return null
            }
        }

        var code: Int = 0
            internal set

        init {
            this.code = code
        }

        override fun toString(): String {
            return sName
        }
    }


    fun setPrintInfo(printLogLevel: LEVEL, tagName: String, dest: FMILogDestination?)
    fun print(logLevel: LEVEL, vararg args: Any?)

    fun v(vararg args: Any)
    fun v(info: String, vararg args: Any)
    fun d(vararg args: Any)
    fun d(info: String, vararg args: Any)
    fun i(vararg args: Any)
    fun i(info: String, vararg args: Any)
    fun w(vararg args: Any)
    fun w(info: String, vararg args: Any)
    fun e(vararg args: Any)
    fun e(info: String, vararg args: Any)
    fun exception(e: Exception)
    fun exception(info: String, e: Exception)


    fun printDeviceInfo(context: Context)
    fun printMemory()
    fun printArray(logLevel: LEVEL, title: String, container: Array<Any>)
    fun printMap(logLevel: LEVEL, title: String, map: Map<String, String>)
    fun printException(e: Exception)
}
