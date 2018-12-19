package com.githang.gradledoc.common

import java.io.IOException

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2017-04-08
 * @since 2017-04-08
 */

abstract class Model<T> {

    fun requestFromCache(url: String): String? {
        return HttpDBCache.queryResponse(url)
    }

    @Throws(IOException::class)
    fun requestFromNetwork(url: String): String {
        val content = HttpProxy.loadFromNetwork(url)
        HttpDBCache.saveResponse(url, content)
        return content
    }

    abstract fun handleContent(content: String): T
}
