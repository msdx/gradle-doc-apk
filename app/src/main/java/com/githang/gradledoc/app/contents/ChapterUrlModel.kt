package com.githang.gradledoc.app.contents

import com.githang.gradledoc.common.Model
import org.jsoup.Jsoup
import java.util.ArrayList

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2017-04-08
 * @since 2017-04-08
 */
internal class ChapterUrlModel : Model<List<ChapterUrl>>() {
    override fun handleContent(content: String): List<ChapterUrl> {
        val doc = Jsoup.parse(content)
        val elements = doc.select("span.chapter")
        val chapterUrls = ArrayList<ChapterUrl>()
        for (elem in elements) {
            val url = ChapterUrl(elem.text(), elem.select("a[href]").attr("href"))
            chapterUrls.add(url)
        }
        return chapterUrls
    }
}
