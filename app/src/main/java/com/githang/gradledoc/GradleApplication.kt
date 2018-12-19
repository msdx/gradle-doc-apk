package com.githang.gradledoc

import android.app.Application
import com.githang.gradledoc.common.HttpDBCache
import com.umeng.analytics.MobclickAgent

/**
 * @author Geek_Soledad(msdx.android@qq.com)
 * @since 2014-11-29 23:47
 */
class GradleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initUmeng()
        HttpDBCache.init(this)
    }

    private fun initUmeng() {
        MobclickAgent.setDebugMode(BuildConfig.DEBUG)
        MobclickAgent.setCatchUncaughtExceptions(true)
    }
}
