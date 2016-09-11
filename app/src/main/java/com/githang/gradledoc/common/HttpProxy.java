package com.githang.gradledoc.common;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 12:19
 */
public class HttpProxy {
    private static final int TIMEOUT_SECOND = 15;
    private static final AtomicInteger TAG_GENERATOR = new AtomicInteger(0);
    private static HttpDBCache CACHE;
    private static OkHttpClient HTTP_CLIENT = new OkHttpClient();

    static {
        HTTP_CLIENT.setConnectTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS);
        HTTP_CLIENT.setReadTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS);
        HTTP_CLIENT.setWriteTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS);
    }

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
