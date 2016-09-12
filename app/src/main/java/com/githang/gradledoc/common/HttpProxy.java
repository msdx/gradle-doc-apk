package com.githang.gradledoc.common;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 12:19
 */
public class HttpProxy {
    private static final int TIMEOUT_SECOND = 15;
    private static final AtomicInteger TAG_GENERATOR = new AtomicInteger(0);
    private static HttpDBCache CACHE;
    private static OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS)
            .build();

    public static void init(HttpDBCache cache) {
        CACHE = cache;
    }

    static String loadFromCache(String url) {
        return CACHE.queryResponse(url);
    }

    static String loadFromNetwork(Context context, String url) throws IOException {
        final int tag = TAG_GENERATOR.getAndIncrement();
        return HTTP_CLIENT.newCall(new Request.Builder().url(url).tag(tag).build()).execute().body().string();
    }
}
