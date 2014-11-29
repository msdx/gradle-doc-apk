package com.githang.gradledoc.datasource;

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 12:51
 * FIXME
 */
public interface AbstractResponse {
    public void onSuccess(String response);
    public void onFailure(String response, Throwable e);
    public void onFinish();
}
