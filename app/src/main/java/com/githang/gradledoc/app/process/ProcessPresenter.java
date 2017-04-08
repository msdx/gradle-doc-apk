package com.githang.gradledoc.app.process;

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
class ProcessPresenter<V extends View<ProcessPresenter, List<Commit>>> extends Presenter.Base<List<Commit>, V> {
    ProcessPresenter(V view) {
        super(view);
    }

    @Override
    public List<Commit> handleContent(String content) {
        Document doc = Jsoup.parse(content);
        Elements elements = doc.select("div[class=table-list-cell]");
        List<Commit> commits = new ArrayList<>(elements.size());
        for (Element elem : elements) {
            Commit commit = new Commit();
            commit.setTitle(elem.select("p.commit-title").text());
            commit.setMeta(elem.select("div.commit-meta").text());
            commits.add(commit);
        }
        return commits;
    }
}
