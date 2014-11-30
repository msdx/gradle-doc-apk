package com.githang.gradledoc.datasource;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 12:19
 * FIXME
 */
public class HttpProxy {
    private static HttpProxy instance;

    private Context mContext;
    private HttpDBCache mCache;
    private AsyncHttpClient mHttpClient;

    public static synchronized HttpProxy getInstance(Context context) {
        if (instance == null) {
            instance = new HttpProxy(context);
        }
        return instance;
    }

    public HttpProxy(Context context) {
        mContext = context;
        mCache = new HttpDBCache(context);
        mHttpClient = new AsyncHttpClient();
    }

    /**
     * 强制从互联网上请求
     *
     * @param context
     * @param url
     * @param response
     */
    public void forceRequestUrl(Context context, final String url, final AbstractResponse response) {
        mHttpClient.cancelRequests(context, true);
        mHttpClient.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                response.onFailure(responseString, throwable);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                mCache.saveResponse(url, responseString);
                response.onSuccess(responseString);
            }

            @Override
            public void onFinish() {
                response.onFinish();
            }
        });
    }

    /**
     * 请求页面。
     *
     * @param context
     * @param url
     * @param response
     */
    public void requestUrl(Context context, String url, AbstractResponse response) {
        String result = mCache.queryResponse(url);
        if (result != null) {
            response.onSuccess(result);
            response.onFinish();
        } else {
            forceRequestUrl(context, url, response);
        }
    }

    /**
     * 取消请求
     *
     * @param context
     */
    public void cancelRequests(Context context) {
        mHttpClient.cancelRequests(context, true);
    }
}
