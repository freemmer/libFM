package com.tistory.freemmer.lib.libfm.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tistory.freemmer.lib.libfm.logger.FMILog
import com.tistory.freemmer.lib.libfm.platform.FMBeanManager

class MainActivity : AppCompatActivity() {

    private val log = FMBeanManager.getClass(FMILog::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        log?.d("Hello world!")
    }

}
