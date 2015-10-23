package com.githang.gradledoc.datasource;

import android.content.Context;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 12:19
 * FIXME
 */
public class HttpProxy {
    private static final AtomicInteger TAG_GENERATOR = new AtomicInteger(0);
    private static HttpProxy instance;

    private HttpDBCache mCache;
    private OkHttpClient mHttpClient;
    private WeakHashMap<Context, List<Integer>> mRequestTags;

    public static synchronized HttpProxy getInstance(Context context) {
        if (instance == null) {
            instance = new HttpProxy(context);
        }
        return instance;
    }

    public HttpProxy(Context context) {
        mHttpClient = new OkHttpClient();
        mHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);
        mHttpClient.setReadTimeout(15, TimeUnit.SECONDS);
        mHttpClient.setWriteTimeout(15, TimeUnit.SECONDS);
        mRequestTags = new WeakHashMap<>();
        mCache = HttpDBCache.getInstance(context);
    }

    /**
     * 强制从互联网上请求
     *
     * @param context
     * @param url
     * @param resp
     */
    public void forceRequestUrl(final Context context, final String url, final AbstractResponse resp) {
        final int tag = TAG_GENERATOR.getAndIncrement();
        saveTag(context, tag);
        mHttpClient.newCall(new Request.Builder().url(url).tag(tag).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                String msg = e.getMessage().toUpperCase(Locale.US);
                if (!msg.contains("CANCELED") && !msg.contains("SOCKET CLOSED")) {
                    resp.onFailure("", e);
                }
                resp.onFinish();
                removeTag(context, tag);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String body = response.body().string();
                        String handled = resp.handleResponse(body);
                        mCache.saveResponse(url, handled);
                        resp.onSuccess(handled);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        resp.onFinish();
                    }
                } else {
                    onFailure(null, new IOException("请求失败"));
                }
                removeTag(context, tag);
            }
        });
    }

    /**
     * 请求页面。
     *
     * @param context
     * @param url
     * @param response
     * @return true 表示从缓存当中获取内容，false表示缓存当在没有内容，通过网络获取。
     */
    public boolean requestUrl(Context context, String url, AbstractResponse response) {
        String result = mCache.queryResponse(url);
        if (result != null) {
            response.onSuccess(result);
            response.onFinish();
            return true;
        } else {
            forceRequestUrl(context, url, response);
            return false;
        }
    }

    /**
     * 取消请求
     *
     * @param context
     */
    public void cancelRequests(Context context) {
        synchronized (mRequestTags) {
            List<Integer> tags = mRequestTags.get(context);
            if (tags != null) {
                for(Integer tag : tags) {
                    mHttpClient.cancel(tag);
                }
                tags.clear();
            }
        }
    }

    private void saveTag(Context context, Integer tag) {
        synchronized (mRequestTags) {
            List<Integer> tags = mRequestTags.get(context);
            if (tags == null) {
                tags = new ArrayList<>();
                mRequestTags.put(context, tags);
            }
            tags.add(tag);
        }
    }

    private synchronized void removeTag(Context context, Integer tag) {
        synchronized (mRequestTags) {
            List<Integer> tags = mRequestTags.get(context);
            if (tags != null) {
                tags.remove(tag);
            }
        }
    }
}
