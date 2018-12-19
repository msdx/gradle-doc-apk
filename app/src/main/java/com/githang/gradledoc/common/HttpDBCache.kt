package com.githang.gradledoc.common

import android.arch.persistence.room.Room
import com.githang.gradledoc.GradleApplication
import com.githang.gradledoc.common.db.AppDatabase
import com.githang.gradledoc.common.db.RequestResult

/**
 * HTTP请求数据缓存
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 12:24
 */
object HttpDBCache {
    private const val DB_NAME = "http_cache_db"

    private val DB: AppDatabase by lazy {
        Room.databaseBuilder(GradleApplication.context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigrationFrom(1, 2)
                .build()
    }

    /**
     * 查询缓存的响应
     *
     * @param url
     * @return
     */
    fun queryResponse(url: String): String? {
        return DB.requestResultDao().queryByUrl(url)?.response
    }

    /**
     * 缓存请求。
     *
     * @param url
     * @param response
     */
    fun saveResponse(url: String, response: String) {
        DB.requestResultDao().save(RequestResult(url, response))
    }

}
