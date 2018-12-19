package com.githang.gradledoc.common

import android.widget.ImageView
import com.githang.gradledoc.GradleApplication
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

/**
 * @author 黄浩杭 (msdx.android@qq.com)
 * @since 2018-12-19
 */
object ImageLoader {
    init {
        val picasso = Picasso.Builder(GradleApplication.context)
                .downloader(OkHttp3Downloader(HttpProxy.HTTP_CLIENT))
                .build()
        Picasso.setSingletonInstance(picasso)
    }

    fun loadImage(url: String?, imageView: ImageView) {
        Picasso.get().load(url).into(imageView)
    }
}