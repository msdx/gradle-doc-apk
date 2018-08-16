package com.githang.gradledoc.app.contents;

import com.githang.gradledoc.common.Model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2017-04-08
 * @since 2017-04-08
 */

class ChapterUrlModel extends Model<List<ChapterUrl>> {
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
