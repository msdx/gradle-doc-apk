package com.githang.gradledoc.datasource;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 12:51
 * FIXME
 */
public abstract class AbstractResponse {
    static final int SUCCESS = 0;
    static final int FAILED = 1;
    static final int FINISH = 3;

    private final Handler mUIHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == SUCCESS) {
                onUISuccess((String) msg.obj);
            } else if (msg.what == FAILED) {
                onUIFailed((Throwable) msg.obj);
            } else if (msg.what == FINISH) {
                onUIFinish();
            } else {
                return false;
            }
            return true;
        }
    });

    public final void onSuccess(String article) {
        mUIHandler.obtainMessage(SUCCESS, article).sendToTarget();
    }

    public final void onFailure(String response, Throwable e) {
        mUIHandler.obtainMessage(FAILED, e).sendToTarget();
    }

    public final void onFinish(){
        mUIHandler.obtainMessage(FINISH).sendToTarget();
    }

    public String handleResponse(String response){
        return response;
    }

    public abstract void onUISuccess(String article);

    public abstract void onUIFailed(Throwable e);

    public abstract void onUIFinish();
}
