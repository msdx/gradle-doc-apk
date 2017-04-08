package com.githang.gradledoc.common;

import java.io.IOException;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2017-04-08
 * @since 2017-04-08
 */

public abstract class Model<T> {

    public String requestFromCache(String url) {
        return HttpDBCache.get().queryResponse(url);
    }

    public String requestFromNetwork(String url) throws IOException {
        String content = HttpProxy.loadFromNetwork(url);
        HttpDBCache.get().saveResponse(url, content);
        return content;
    }

    public abstract T handleContent(String content);
}
