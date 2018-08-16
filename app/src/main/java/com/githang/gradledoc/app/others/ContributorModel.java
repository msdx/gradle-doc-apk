package com.githang.gradledoc.app.others;

import com.alibaba.fastjson.JSON;
import com.githang.gradledoc.common.Model;

import java.util.List;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2017-04-08
 * @since 2017-04-08
 */

class ContributorModel extends Model<List<Contributor>> {
    @Override
    public List<Contributor> handleContent(String content) {
        return JSON.parseArray(content, Contributor.class);
    }
}
