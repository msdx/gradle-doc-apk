package com.githang.gradledoc.app.others

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.githang.gradledoc.common.ImageLoader
import com.squareup.moshi.Json

/**
 * 贡献者
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2015-12-02
 * @since 2015-12-02
 */
class Contributor(
        @Json(name = "login") val name: String? = null,
        @Json(name = "avatar_url") val avatar: String? = null,
        @Json(name = "contributions") val contributions: Int = 0
) {
    companion object {
        @BindingAdapter(value = ["imageUrl"])
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String) {
            ImageLoader.loadImage(url, imageView)
        }
    }
}
