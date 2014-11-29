package com.githang.gradledoc.contents;

import com.githang.gradledoc.datasource.AbstractResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 13:03
 * FIXME
 */
public abstract class ContentsHandler implements AbstractResponse{
    private static final String LOG_TAG = ContentsHandler.class.getSimpleName();
    @Override
    public void onSuccess(String response) {
//        Log.d(LOG_TAG, response);
        Document doc = Jsoup.parse(response);
        Elements elements = doc.select("span#chapter");
        List<ChapterUrl> chapterUrls = new ArrayList<>();
        for(Element elem : elements) {
            ChapterUrl url = new ChapterUrl();
            url.setUrl(elem.select("a[href]").attr("href"));
            url.setChapter(elem.text());
            chapterUrls.add(url);
        }
    }

    public abstract void onResult(List<ChapterUrl> chapterUrls);

    @Override
    public void onFailure(String response, Throwable e) {

    }

    @Override
    public void onFinish() {

    }
}
