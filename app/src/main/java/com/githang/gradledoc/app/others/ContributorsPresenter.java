package com.githang.gradledoc.app.others;

import com.alibaba.fastjson.JSON;
import com.githang.gradledoc.common.Presenter;
import com.githang.gradledoc.common.View;

import java.util.List;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-09-11
 * @since 2016-09-11
 */
class ContributorsPresenter<V extends View<ContributorsPresenter, List<Contributor>>> extends Presenter.Base<List<Contributor>, V> {
    ContributorsPresenter(V view) {
        super(view);
    }

    @Override
    public List<Contributor> handleContent(String content) {
        return JSON.parseArray(content, Contributor.class);
    }
}
