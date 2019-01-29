package com.tistory.freemmer.lib.libfm.demo

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.tistory.freemmer.lib.libfm.logger.FMILog
import com.tistory.freemmer.lib.libfm.notification.FMINotification
import com.tistory.freemmer.lib.libfm.notification.FMNotification
import com.tistory.freemmer.lib.libfm.permission.FMCheckPermission
import com.tistory.freemmer.lib.libfm.permission.FMICheckPermission
import com.tistory.freemmer.lib.libfm.platform.FMBeanManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FMICheckPermission, FMINotification {

    private lateinit var checker: FMCheckPermission
    private val log = FMBeanManager.getClass(FMILog::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        log?.d("Hello world!")

        // for FMINotification::onClickedNotification
        intent.extras?.let {
            onClickedNotification(it)
        }


        checkPushTokenButton.setOnClickListener {
            FMNotification.getPushToken { isSuccessful: Boolean, token: String? ->
                if (!isSuccessful) {
                    log?.d("getInstanceId failed")
                } else {
                    Toast.makeText(baseContext, "Push Token: $token", Toast.LENGTH_SHORT).show()
                }
            }
        }

        checkPermissionButton.setOnClickListener {
            checkPermission(arrayOf(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.SYSTEM_ALERT_WINDOW
            ), this.packageName)
        }

    }



    override fun onClickedNotification(bundle: Bundle) {
        // In case a notification message is tapped.
        // Handle possible data accompanying notification message.
        for (key in bundle.keySet()) {
            log?.d("Key: %s Value: %s", key, bundle.get(key) ?: "empty")
        }
    }

    override fun onRequestPermissionRationale(permissions: List<String>) {
        log?.d("Request Permission Rationale(In case. checked 'Don't ask again') : $permissions")
        Snackbar
            .make(checkPermissionButton
                , "Request Permission Rationale(In case. checked 'Don't ask again') : $permissions"
                , Snackbar.LENGTH_LONG)
            .setAction("move setting") { movePermissionSetting(this.packageName) }
            .show()
    }

    override fun onDeniedRequestPermission(permissions: List<String>) {
        log?.d("Denied Request Permission : $permissions")
        Snackbar
            .make(checkPermissionButton, "Denied Request Permission : $permissions", Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun onGrantedRequestPermission() {
        log?.d("Granted Request Permission")
        Snackbar
            .make(checkPermissionButton, "Granted Request Permission", Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun onDeniedSystemAlertWindow() {
        log?.d("Denied SystemAlertWindow")
        Snackbar
            .make(checkPermissionButton, "Denied SystemAlertWindow", Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun onGrantedSystemAlertWindow() {
        log?.d("Granted SystemAlertWindow")
        Snackbar
            .make(checkPermissionButton, "Granted SystemAlertWindow", Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        checker.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        checker.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }



    private fun checkLateInit() {
        if (!this::checker.isInitialized) {
            checker = FMCheckPermission(this, this)
        }
    }

    private fun checkPermission(permissions: Array<String>, packageName: String? = null) {
        checkLateInit()
        checker.execute(permissions, packageName)
    }

    private fun movePermissionSetting(packageName: String) {
        checkLateInit()
        checker.moveSetting(packageName)
    }

}
