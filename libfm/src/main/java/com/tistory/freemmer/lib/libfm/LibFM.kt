package com.tistory.freemmer.lib.libfm

import com.tistory.freemmer.lib.libfm.logger.FMILog
import com.tistory.freemmer.lib.libfm.logger.impl.FMLogCatImpl

/**
 * Created by freemmer on 22/01/2019.
 * History
 *    - 22/01/2019 Create file
 */
object LibFM {
    var log: FMILog? = null

    fun initialize(): LibFM {
        return this
    }

    fun enableDebugLog(): LibFM {
        log = FMLogCatImpl.initialize(FMILog.LEVEL.DEBUG, BuildConfig.APPLICATION_ID, null)
        return this
    }
}

