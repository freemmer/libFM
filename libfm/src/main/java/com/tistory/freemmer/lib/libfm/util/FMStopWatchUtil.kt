package com.tistory.freemmer.lib.libfm.util

/**
 * Created by freemmer on 01/02/2019.
 * History
 *    - 01/02/2019 Create file
 */
class FMStopWatchUtil {
    private var sJobName: String? = null
    private var startTime: Long = 0
    private var endTime: Long = 0

    fun start(jobName: String): FMStopWatchUtil {
        sJobName = jobName
        startTime = System.nanoTime()
        return this
    }

    fun stop(): String {
        endTime = System.nanoTime()
        return sJobName + " : " + getTime().toString()
    }

    fun getTime(): Double {
        val ms = (endTime - startTime) / 1000000
        return ms.toDouble() / 1000
    }

}
