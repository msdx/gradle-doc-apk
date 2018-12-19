package com.githang.gradledoc.app.process

import com.githang.gradledoc.common.Model
import org.jsoup.Jsoup
import java.util.ArrayList

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2017-04-08
 * @since 2017-04-08
 */

internal class ProcessModel : Model<List<Commit>>() {
    override fun handleContent(content: String): List<Commit> {
        val doc = Jsoup.parse(content)
        val elements = doc.select("div[class=table-list-cell]")
        val commits = ArrayList<Commit>(elements.size)
        for (elem in elements) {
            val commit = Commit()
            commit.title = elem.select("p.commit-title").text()
            commit.meta = elem.select("div.commit-meta").text()
            commits.add(commit)
        }
        return commits
    }
}
