package com.githang.gradledoc.common.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * @author 黄浩杭 (msdx.android@qq.com)
 * @since 2018-12-19
 */
interface ItemCreator<T, H : RecyclerView.ViewHolder> {

    /**
     * 创建指定位置的 ViewHolder
     * @param parent item 父布局
     * @return ViewHolder
     */
    fun createHolder(parent: ViewGroup): H

    /**
     * 设置列表里的视图内容
     *
     * @param position 在列表中的位置
     * @param holder   该位置对应的视图
     * @param data     对应位置的数据
     */
    fun bindData(position: Int, holder: H, data: T)
}