package com.githang.gradledoc.app.others

import com.alibaba.fastjson.annotation.JSONField

/**
 * 贡献者
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2015-12-02
 * @since 2015-12-02
 */
class Contributor {
    @set:JSONField(name = "login")
    var name: String? = null
    @set:JSONField(name = "avatar_url")
    var avatar: String? = null
    @set:JSONField(name = "contributions")
    var contributions: Int = 0
}
