package com.tistory.freemmer.lib.libfm.util

import java.util.*

/**
 * Created by freemmer on 01/02/2019.
 * History
 *      - 01/02/2019 Create file
 * How use
 *      val stopWatch = FMStopWatchUtil().start("title")
 *      FMStopWatchUtil.history.add(stopWatch.stop())
 */
class FMStopWatchUtil {
    private var sJobName: String? = null
    private var startTime: Long = 0
    private var endTime: Long = 0

    companion object {
        val history = ArrayList<String>()
    }

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
