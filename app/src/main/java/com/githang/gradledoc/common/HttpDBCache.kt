package com.githang.gradledoc.common

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.githang.gradledoc.GradleApplication
import java.util.Locale

/**
 * HTTP请求数据缓存
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 12:24
 */
class HttpDBCache private constructor(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_RESPONSE)
        db.execSQL(CREATE_RESPONSE_INDEX)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    /**
     * 查询缓存的响应
     *
     * @param url
     * @return
     */
    fun queryResponse(url: String): String? {
        val db = readableDatabase
        val cursor = db.query(TABLE_RESPONSE, arrayOf(COL_ID, COL_URL, COL_RESPONSE),
                "$COL_URL=?", arrayOf(url), null, null, null)
        var response: String? = null
        if (cursor.moveToNext()) {
            response = cursor.getString(cursor.getColumnIndex(COL_RESPONSE))
        }
        cursor.close()
        db.close()
        return response
    }

    /**
     * 缓存请求。
     *
     * @param url
     * @param response
     */
    fun saveResponse(url: String, response: String) {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(COL_URL, url)
        cv.put(COL_RESPONSE, response)
        db.replace(TABLE_RESPONSE, null, cv)
        logMaxId(db)
        db.close()
    }

    private fun logMaxId(db: SQLiteDatabase) {
        val sql = String.format(Locale.US, "select max(%s) AS maxId from %s", COL_ID, TABLE_RESPONSE)
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToNext()) {
            val maxId = cursor.getInt(cursor.getColumnIndex("maxId"))
            Log.d("Cache", "id ...$maxId")
        }
    }

    companion object {

        val INSTANCE: HttpDBCache by lazy { HttpDBCache(GradleApplication.context) }

        private const val VERSION = 2
        private const val DB_NAME = "http_cache_db"

        private const val TABLE_RESPONSE = "t_response"
        private const val COL_ID = "_id"
        private const val COL_URL = "url"
        private const val COL_RESPONSE = "response"
        private val CREATE_RESPONSE = String.format(
                "CREATE TABLE '%s'(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
                TABLE_RESPONSE, COL_ID, COL_URL, COL_RESPONSE)
        private val CREATE_RESPONSE_INDEX = String.format("CREATE UNIQUE INDEX unique_index_url ON %s (%s)", TABLE_RESPONSE, COL_URL)
    }
}
