package com.githang.gradledoc.app.contents;

import com.githang.gradledoc.common.Presenter;
import com.githang.gradledoc.common.View;

import java.util.List;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-09-11
 * @since 2016-09-11
 */
class ContentPresenter<V extends View<List<ChapterUrl>>> extends Presenter.Base<List<ChapterUrl>, V> {
    ContentPresenter(V view) {
        super(view, new ChapterUrlModel());
    }
}
