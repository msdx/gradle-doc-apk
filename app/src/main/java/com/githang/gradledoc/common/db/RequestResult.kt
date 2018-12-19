package com.githang.gradledoc.common.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author 黄浩杭 (msdx.android@qq.com)
 * @since 2018-12-19
 */
@Entity(tableName = "t_response")
class RequestResult(
        @ColumnInfo(name = "url", index = true)
        val url: String?,
        @ColumnInfo(name = "response")
        val response: String?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}