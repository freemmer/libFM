package com.tistory.freemmer.lib.libfm.demo

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.tistory.freemmer.lib.libfm.logger.FMLog
import com.tistory.freemmer.lib.libfm.notification.FMINotification
import com.tistory.freemmer.lib.libfm.notification.FMNotification
import com.tistory.freemmer.lib.libfm.permission.FMCheckPermissionAppCompatActivity
import com.tistory.freemmer.lib.libfm.widget.FMAlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FMCheckPermissionAppCompatActivity(), FMINotification {

    //private val log = FMBeanManager.getClass(FMILog::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FMLog.d("Hello world!")

        // for FMINotification::onClickedNotification
        intent.extras?.let {
            onClickedNotification(it)
        }


        checkPushTokenButton.setOnClickListener {
            FMNotification.getPushToken { isSuccessful: Boolean, token: String? ->
                if (!isSuccessful) {
                    FMLog.d("getInstanceId failed")
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
                Manifest.permission.SYSTEM_ALERT_WINDOW)
                , pAllowedFunc = {
                    // All Permissions requested are allowed
                    Snackbar.make(checkPermissionButton
                        , "OK!!", Snackbar.LENGTH_SHORT).show()
                }
                , pDeniedFunc = { checkedDoNotAskPermissions, permissions ->
                    // Requested Permission denied
                    if (checkedDoNotAskPermissions.isNotEmpty()) {
                        Snackbar.make(checkPermissionButton
                            , "Requested Permissions denied with 'Don't ask again' : $checkedDoNotAskPermissions"
                            , Snackbar.LENGTH_LONG)
                            .setAction("move setting") { movePermissionSetting() }.show()
                    } else {
                        Snackbar.make(checkPermissionButton
                            , "Requested Permission denied : $permissions", Snackbar.LENGTH_SHORT).show()
                    }
                })
        }

        showAlertButton.setOnClickListener {
            FMAlertDialog.build(this, "title", "message") {
                pOkButton = {
                    Snackbar.make(showAlertButton
                            , "Clicked OK!", Snackbar.LENGTH_SHORT).show()
                    it.dismiss()
                }
                pCancelButton = {
                    Snackbar.make(showAlertButton
                            , "Clicked CANCEL!", Snackbar.LENGTH_SHORT).show()
                    it.dismiss()
                }
            }.show()
        }


    }




    override fun onClickedNotification(bundle: Bundle) {
        // In case a notification message is tapped.
        // Handle possible data accompanying notification message.
        for (key in bundle.keySet()) {
            FMLog.d("Key: %s Value: %s", key, bundle.get(key) ?: "empty")
        }
    }

}
