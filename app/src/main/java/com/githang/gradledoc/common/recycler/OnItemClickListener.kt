package com.githang.gradledoc.common.recycler

import android.view.View

/**
 * @author 黄浩杭 (msdx.android@qq.com)
 * @since 2018-12-19
 */
interface OnItemClickListener<T> {
    fun onItemClick(view: View, position: Int, item: T)
}