package com.githang.gradledoc.contents;

import com.githang.gradledoc.datasource.AbstractResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 目录。
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 13:03
 */
public abstract class ContentsHandler extends AbstractResponse {
    @Override
    public void onUISuccess(String response) {
        Document doc = Jsoup.parse(response);
        Elements elements = doc.select("span.chapter");
        List<ChapterUrl> chapterUrls = new ArrayList<>();
        for (Element elem : elements) {
            ChapterUrl url = new ChapterUrl();
            url.setUrl(elem.select("a[href]").attr("href"));
            url.setTitle(elem.text());
            chapterUrls.add(url);
        }
        onResult(chapterUrls);
    }

    public abstract void onResult(List<ChapterUrl> chapterUrls);
}
