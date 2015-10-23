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
 * 文章处理
 */
public abstract class ChapterHandler extends AbstractResponse {
    @Override
    public String handleResponse(String response) {
        Document doc = Jsoup.parse(response);
        Element chapter = doc.select("div.chapter").first();
        String title = chapter.select("div.titlepage").select("h1").text();
        chapter.removeClass("titlepage");
        Elements preElems = chapter.select("pre");
        for (Element elem : preElems) {
            elem.html(elem.html().replaceAll("\n", "<br/>").replaceAll(" ", "&nbsp;"));
        }
        return chapter.html();
    }
}
