package com.tistory.freemmer.lib.libfm.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import com.tistory.freemmer.lib.libfm.R
import kotlinx.android.synthetic.main.dialog_alert_layout.*

/**
 * Created by freemmer on 31/01/2019.
 * History
 *    - 31/01/2019 Create file
 */
class FMAlertDialog constructor(
    context: Context
) : Dialog(context) {

    companion object {
        fun build(context: Context, title: String, message: String, init: FMAlertDialog.Builder.() -> Unit): FMAlertDialog
                = FMAlertDialog.Builder(context, title, message, init).build()
    }

    class Builder(private val context: Context
                  , private val title: String
                  , private val message: String
                  , init: Builder.() -> Unit
    ) {
        var pOkButton: ((dialog: Dialog)->Unit)? = null
        var pCancelButton: ((dialog: Dialog)->Unit)? = null

        init {
            init()
        }

        fun build(): FMAlertDialog {
            val alert = FMAlertDialog(context, title, message)
            alert.pOkButton = this.pOkButton
            alert.pCancelButton = this.pCancelButton
            return alert
        }

        companion object {
            fun build(context: Context, title: String, desc: String, init: Builder.() -> Unit)
                    = Builder(context, title, desc, init).build()
        }
    }

    private lateinit var title: String
    private lateinit var message: String
    var pOkButton: ((dialog: Dialog)->Unit)? = null
    var pCancelButton: ((dialog: Dialog)->Unit)? = null

    constructor(context: Context, title: String, message: String) : this(context) {
        this.title = title
        this.message = message
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawableResource(R.color.dialogBackground)
        setContentView(R.layout.dialog_alert_layout)

        fmAlertTitle.text = title
        fmAlertMessage.text = message
        fmAlertBtnOK.setOnClickListener { pOkButton?.invoke(this) ?: dismiss()}
        if (pCancelButton == null) fmAlertBtnCancel.visibility = View.GONE
        fmAlertBtnCancel.setOnClickListener { pCancelButton?.invoke(this) ?: dismiss()}
    }

    override fun onBackPressed() {
        pCancelButton?.invoke(this) ?: dismiss()
    }
}

