package com.githang.gradledoc.app.others;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 贡献者
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2015-12-02
 * @since 2015-12-02
 */
public class Contributor {
    private String name;
    private String avatar;
    private int contributions;

    public String getName() {
        return name;
    }

    @JSONField(name = "login")
    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    @JSONField(name = "avatar_url")
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getContributions() {
        return contributions;
    }

    @JSONField(name = "contributions")
    public void setContributions(int contributions) {
        this.contributions = contributions;
    }
}
