package com.tistory.freemmer.lib.libfm

import com.tistory.freemmer.lib.libfm.logger.FMILog
import com.tistory.freemmer.lib.libfm.logger.impl.FMLogCatImpl

/**
 * Created by freemmer on 22/01/2019.
 * History
 *    - 22/01/2019 Create file
 */
object libFM {
    var log: FMILog? = null

    fun initialize(): libFM {
        return this
    }

    fun enableDebugLog(): libFM {
        log = FMLogCatImpl.initialize(FMILog.LEVEL.DEBUG, BuildConfig.APPLICATION_ID, null)
        return this
    }
}

