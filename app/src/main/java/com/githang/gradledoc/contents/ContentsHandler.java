package com.githang.gradledoc.contents;

import android.util.Log;

import com.githang.gradledoc.datasource.AbstractResponse;

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 13:03
 * FIXME
 */
public class ContentsHandler implements AbstractResponse{
    private static final String LOG_TAG = ContentsHandler.class.getSimpleName();
    @Override
    public void onSuccess(String response) {
        Log.d(LOG_TAG, response);
    }

    @Override
    public void onFailure(String response, Throwable e) {

    }

    @Override
    public void onFinish() {

    }
}
