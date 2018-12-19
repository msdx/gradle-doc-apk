package com.githang.gradledoc.app.chapter

import com.githang.gradledoc.common.Model
import org.jsoup.Jsoup

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2017-04-08
 * @since 2017-04-08
 */

internal class ChapterModel : Model<String>() {

    override fun handleContent(content: String): String {
        val doc = Jsoup.parse(content)
        doc.select("div.navheader").remove()
        doc.select("div.navfooter").remove()

        val chapter = doc.select("div.chapter").first()
        chapter.select("div.titlepage").first().remove()

        return doc.html()
    }
}
