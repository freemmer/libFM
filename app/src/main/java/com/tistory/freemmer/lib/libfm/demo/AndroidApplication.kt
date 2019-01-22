package com.tistory.freemmer.lib.libfm.demo

import android.app.Application
import com.tistory.freemmer.lib.libfm.libFM
import com.tistory.freemmer.lib.libfm.logger.FMILog
import com.tistory.freemmer.lib.libfm.logger.impl.FMLogCatImpl
import com.tistory.freemmer.lib.libfm.platform.FMBeanManager

/**
 * Created by freemmer on 22/01/2019.
 * History
 *    - 22/01/2019 Create file
 */
class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            libFM.initialize().enableDebugLog()
            FMBeanManager.registerClass(FMILog::class.java, FMLogCatImpl.initialize(FMILog.LEVEL.DEBUG
                , this.resources.getString(R.string.app_name), null))
            val log: FMILog? = FMBeanManager.getClass(FMILog::class.java)
            log?.printDeviceInfo(this)
            log?.printMemory()
        }

    }

}
