package com.tistory.freemmer.lib.libfm.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
//import android.support.annotation.RequiresApi
//import android.support.v4.app.NotificationCompat
import com.tistory.freemmer.lib.libfm.BuildConfig
import com.tistory.freemmer.lib.libfm.R

/**
 * Created by freemmer on 22/01/2019.
 * History
 *    - 22/01/2019 Create file
 */
/**
 * 서비스 구현시 공통적으로 필요한 기능 제공
 */
open class FMBaseService
    : Service()
{

    /**
     * 서비스 생성시 5초 이내에 Foreground 로 해야 한다.
     */
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            startMyOwnForeground()
        } else {
            startForeground(1, Notification())
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    @SuppressLint("PrivateResource")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun startMyOwnForeground() {
        val channelName = "My Background Service"
        val chan = NotificationChannel(BuildConfig.APPLICATION_ID, channelName, NotificationManager.IMPORTANCE_NONE)
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val manager = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.createNotificationChannel(chan)

        val notificationBuilder
                = NotificationCompat.Builder(this, BuildConfig.APPLICATION_ID)
        val notification = notificationBuilder.setOngoing(true)
            .setSmallIcon(R.drawable.navigation_empty_icon)
            .setContentTitle("App is running in background")
            .setPriority(NotificationManager.IMPORTANCE_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        startForeground(1, notification)
    }


    /**
     * Android 8.0 이상부터 달라진 서비스를 실행하는 방법을 관리한다.
     * startForegroundService로 실행된 서비스에서 startForegroundService를 호출하면 안된다.
     */
    companion object {
        var isRunningStartForegroundService = false

        /**
         * 서비스를 시작한다.
         */
        fun fmStartService(context: Context, intent: Intent) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                && !isRunningStartForegroundService)
            {
                isRunningStartForegroundService = true
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }

        /**
         * 서비스를 종료한다. (서비스 내부에서 호출)
         */
        fun fmStopSelf(service: FMBaseService) {
            service.stopSelf()
            isRunningStartForegroundService = false
        }

    }

}