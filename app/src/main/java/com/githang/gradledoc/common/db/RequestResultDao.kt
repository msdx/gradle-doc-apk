package com.githang.gradledoc.common.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

/**
 * @author 黄浩杭 (msdx.android@qq.com)
 * @since 2018-12-19
 */
@Dao
interface RequestResultDao {
    @Query("SELECT * FROM t_response WHERE url = :url LIMIT 1")
    fun queryByUrl(url: String): RequestResult?

    @Update
    fun save(result: RequestResult)
}