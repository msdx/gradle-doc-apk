package com.githang.gradledoc.chapter;

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
        super(view);
    }

    @Override
    public String handleContent(String content) {
        Document doc = Jsoup.parse(content);
        doc.select("div.navheader").remove();
        doc.select("div.navfooter").remove();

        Element chapter = doc.select("div.chapter").first();
        chapter.select("div.titlepage").first().remove();

        return doc.html();
    }
}
