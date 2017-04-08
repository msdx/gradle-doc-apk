package com.githang.gradledoc.app.contents;

import com.githang.gradledoc.common.Presenter;
import com.githang.gradledoc.common.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-09-11
 * @since 2016-09-11
 */
class ContentPresenter<V extends View<ContentPresenter, List<ChapterUrl>>> extends Presenter.Base<List<ChapterUrl>, V> {
    ContentPresenter(V view) {
        super(view);
    }

    @Override
    public List<ChapterUrl> handleContent(String content) {
        Document doc = Jsoup.parse(content);
        Elements elements = doc.select("span.chapter");
        List<ChapterUrl> chapterUrls = new ArrayList<>();
        for (Element elem : elements) {
            ChapterUrl url = new ChapterUrl();
            url.setUrl(elem.select("a[href]").attr("href"));
            url.setTitle(elem.text());
            chapterUrls.add(url);
        }
        return chapterUrls;
    }
}
