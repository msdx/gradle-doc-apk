package com.githang.gradledoc.chapter;

import com.githang.gradledoc.datasource.AbstractResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 21:36
 * FIXME
 */
public abstract class ChapterHandler implements AbstractResponse {
    @Override
    public void onSuccess(String response) {
        Document doc = Jsoup.parse(response);
        Element chapter = doc.select("div.chapter").first();
        String title = chapter.select("div.titlepage").select("h1").text();
        chapter.removeClass("titlepage");
        handlerPreTag(chapter);

        onResult(title, chapter.html());
    }

    /**
     * 处理代码显示问题.
     *
     * @param chapter 章节内容的html元素。
     */
    private void handlerPreTag(Element chapter) {
        Elements preElems = chapter.select("pre");
        for (Element elem : preElems) {
            elem.html(elem.html().replaceAll("\n", "<br/>").replaceAll(" ", "&nbsp;"));
        }
    }


    public abstract void onResult(String title, String doc);

    @Override
    public void onFailure(String response, Throwable e) {

    }

    @Override
    public void onFinish() {

    }
}
