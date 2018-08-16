package com.githang.gradledoc.app.chapter;

import com.githang.gradledoc.common.Model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2017-04-08
 * @since 2017-04-08
 */

class ChapterModel extends Model<String> {

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
