package com.tistory.freemmer.lib.libfm.demo

import android.app.Application
import com.tistory.freemmer.lib.libfm.LibFM
import com.tistory.freemmer.lib.libfm.logger.FMILog
import com.tistory.freemmer.lib.libfm.logger.FMLog
import com.tistory.freemmer.lib.libfm.notification.FMNotification

/**
 * Created by freemmer on 22/01/2019.
 * History
 *    - 22/01/2019 Create file
 */
class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // When only payload type is used like 'GCM'
        //FMNotification.PAYLOAD_TITLE_KEY = "title"
        //FMNotification.PAYLOAD_BODY_KEY = "body"
        // In case automatically create default channel
        FMNotification.initialize(this)
        // In case manually create default channel
        //FMNotification.instance(this).createChannel("default_channel_id", "Default Channel")

        if (BuildConfig.DEBUG) {
            LibFM.initialize().enableDebugLog()
            /*FMBeanManager.registerClass(FMILog::class.java, FMLogCatImpl
                .build(FMILog.LEVEL.DEBUG, this.resources.getString(R.string.app_name), null))
            val log: FMILog? = FMBeanManager.getClass(FMILog::class.java)
            log?.printDeviceInfo(this)
            log?.printMemory()*/
            FMLog.initialze(FMILog.LEVEL.DEBUG, this.resources.getString(R.string.app_name))
            FMLog.printDeviceInfo(this)
            FMLog.printMemory()
        }

    }

}
