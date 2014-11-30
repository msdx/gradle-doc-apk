package com.githang.gradledoc.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * HTTP请求数据缓存
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 12:24
 * FIXME
 */
public class HttpDBCache extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "http_cache_db";

    private static final String TABLE_RESPONSE = "t_response";
    private static final String COL_ID = "_id";
    private static final String COL_URL = "url";
    private static final String COL_RESPONSE = "response";
    private static final String CREATE_RESPONSE = String.format("CREATE TABLE '%s'(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
            TABLE_RESPONSE, COL_ID, COL_URL, COL_RESPONSE);
    private static final String CREATE_RESPONSE_INDEX = String.format("CREATE UNIQUE INDEX unique_index_url ON %s (%s)", TABLE_RESPONSE, COL_URL);

//    private static final String TABLE_CONFIG = "t_config";
//    private static final String CRAETE_CONFIG = "CREATE TABLE 't_config'(_id INTEGER PRIMARY KEY AUTOINCREMENT, key TEXT, value TEXT)";


    public HttpDBCache(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RESPONSE);
        db.execSQL(CREATE_RESPONSE_INDEX);
//        db.execSQL(CRAETE_CONFIG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 查询缓存的响应
     * @param url
     * @return
     */
    public String queryResponse(String url) {
        SQLiteDatabase db =getReadableDatabase();
        Cursor cursor = db.query(TABLE_RESPONSE, new String[]{COL_ID, COL_URL, COL_RESPONSE},
                COL_URL + "=?", new String[]{url}, null, null, null);
        String response = null;
        if(cursor.moveToNext()) {
            response = cursor.getString(cursor.getColumnIndex(COL_RESPONSE));
        }
        cursor.close();
        db.close();
        return response;
    }

    /**
     * 缓存请求。
     * @param url
     * @param response
     */
    public void saveResponse(String url, String response) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_URL, url);
        cv.put(COL_RESPONSE, response);
        db.replace(TABLE_RESPONSE, null, cv);
        db.close();
    }

//    /**
//     * 保存配置。
//     * @param key
//     * @param value
//     */
//    public void saveConfig(String key, String value) {
//
//    }
//
//    /**
//     * 查询配置。
//     * @param key
//     * @param defaultValue
//     * @return
//     */
//    public String queryConfig(String key, String defaultValue) {
//
//        return null;
//    }
}
