package com.githang.gradledoc.common.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * @author 黄浩杭 (msdx.android@qq.com)
 * @since 2018-12-19
 */
@Dao
interface RequestResultDao {
    @Query("SELECT * FROM t_response WHERE url = :url LIMIT 1")
    fun queryByUrl(url: String): RequestResult?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(result: RequestResult)
}