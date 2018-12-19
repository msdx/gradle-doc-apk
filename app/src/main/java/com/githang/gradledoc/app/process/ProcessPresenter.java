package com.githang.gradledoc.app.process;

import com.githang.gradledoc.common.Presenter;
import com.githang.gradledoc.common.View;

import java.util.List;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-09-11
 * @since 2016-09-11
 */
class ProcessPresenter<V extends View<List<Commit>>> extends Presenter.Base<List<Commit>, V> {
    ProcessPresenter(V view) {
        super(view, new ProcessModel());
    }
}
