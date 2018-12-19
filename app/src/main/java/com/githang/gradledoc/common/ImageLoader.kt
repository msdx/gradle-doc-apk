package com.githang.gradledoc.common

import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * @author 黄浩杭 (msdx.android@qq.com)
 * @since 2018-12-19
 */
object ImageLoader {
    fun loadImage(url: String?, imageView: ImageView) {
        Picasso.get().load(url).into(imageView)
    }
}