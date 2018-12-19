package com.githang.gradledoc.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem

import com.githang.gradledoc.R
import com.githang.statusbar.StatusBarCompat
import com.umeng.analytics.MobclickAgent

/**
 * @author Geek_Soledad (msdx.android@qq.com)
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarCompat.setStatusBarColor(this, ActivityCompat.getColor(this, R.color.primary), false)
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)
        val toolbar: Toolbar? = findViewById(R.id.toolbar)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }

    public override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun startActivity(cls: Class<out Activity>) {
        startActivity(Intent(this, cls))
    }
}
