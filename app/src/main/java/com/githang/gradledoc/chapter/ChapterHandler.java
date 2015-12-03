package com.githang.gradledoc.chapter;

import com.githang.gradledoc.datasource.AbstractResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 21:36
 * 文章处理
 */
public abstract class ChapterHandler extends AbstractResponse {
    @Override
    public String preHandleResponse(String response) {
        Document doc = Jsoup.parse(response);
        doc.select("div.navheader").remove();
        doc.select("div.navfooter").remove();

        Element chapter = doc.select("div.chapter").first();
        chapter.select("div.titlepage").first().remove();

        return doc.html();
    }
}
