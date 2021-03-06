package com.githang.gradledoc.app.chapter;

import com.githang.gradledoc.common.Presenter;
import com.githang.gradledoc.common.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-09-11
 * @since 2016-09-11
 */
class ChapterPresenter<V extends View<ChapterPresenter, String>> extends Presenter.Base<String, V> {
    ChapterPresenter(V view) {
        super(view, new ChapterModel());
    }
}
