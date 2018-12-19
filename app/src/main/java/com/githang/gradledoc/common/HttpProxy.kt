package com.githang.gradledoc.common

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 12:19
 */
object HttpProxy {
    private const val TIMEOUT_SECOND = 15
    private val TAG_GENERATOR = AtomicInteger(0)
    val HTTP_CLIENT: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECOND.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECOND.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECOND.toLong(), TimeUnit.SECONDS)
            .build()

    @Throws(IOException::class)
    internal fun loadFromNetwork(url: String): String {
        val tag = TAG_GENERATOR.getAndIncrement()
        return HTTP_CLIENT.newCall(Request.Builder().url(url).tag(tag).build()).execute().body()!!.string()
    }
}
