package com.githang.gradledoc.app.others

import android.os.Bundle
import android.widget.TextView

import com.githang.gradledoc.BuildConfig
import com.githang.gradledoc.R
import com.githang.gradledoc.common.BaseActivity

class AboutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val version: TextView = findViewById(R.id.about_version)
        version.text = "版本: " + BuildConfig.VERSION_NAME + "_" + BuildConfig.REVISION
    }
}
