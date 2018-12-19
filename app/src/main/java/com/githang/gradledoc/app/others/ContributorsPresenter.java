package com.githang.gradledoc.app.others;

import com.githang.gradledoc.common.Presenter;
import com.githang.gradledoc.common.View;

import java.util.List;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-09-11
 * @since 2016-09-11
 */
class ContributorsPresenter<V extends View<List<Contributor>>> extends Presenter.Base<List<Contributor>, V> {
    ContributorsPresenter(V view) {
        super(view, new ContributorModel());
    }
}
