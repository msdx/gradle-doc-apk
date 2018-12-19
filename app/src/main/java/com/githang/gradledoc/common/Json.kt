package com.githang.gradledoc.common

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * @author 黄浩杭 (msdx.android@qq.com)
 * @since 2018-12-19
 */
object Json {
    val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    inline fun <reified T> parseArray(json: String): List<T>? {
        val type = Types.newParameterizedType(List::class.java, T::class.java)
        return moshi.adapter<List<T>>(type).fromJson(json)
    }
}