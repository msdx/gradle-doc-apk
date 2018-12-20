package com.githang.gradledoc.common.recycler

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.githang.gradledoc.common.ImageLoader

/**
 * @author 黄浩杭 (msdx.android@qq.com)
 * @since 2018-12-20
 */
object BindingAdapters {
    @BindingAdapter(value = ["imageUrl"])
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        ImageLoader.loadImage(url, imageView)
    }
}