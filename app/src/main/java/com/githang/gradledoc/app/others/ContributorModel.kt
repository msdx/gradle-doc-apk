package com.githang.gradledoc.app.others

import com.githang.gradledoc.common.Json
import com.githang.gradledoc.common.Model

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2017-04-08
 * @since 2017-04-08
 */

internal class ContributorModel : Model<List<Contributor>>() {
    override fun handleContent(content: String): List<Contributor> {
        return Json.parseArray(content)?:ArrayList()
    }
}