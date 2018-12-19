package com.githang.gradledoc.common.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * @author 黄浩杭 (msdx.android@qq.com)
 * @since 2018-12-19
 */
@Database(entities = [RequestResult::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun requestResultDao(): RequestResultDao
}
