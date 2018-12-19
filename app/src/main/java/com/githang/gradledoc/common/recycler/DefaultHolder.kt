package com.githang.gradledoc.common.recycler

import android.content.Context
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Checkable
import android.widget.TextView

/**
 * @author 黄浩杭 (msdx.android@qq.com)
 * @since 2018-12-19
 */
class DefaultHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val holderViews: SparseArray<View> = SparseArray()

    constructor(context: Context, @LayoutRes layoutId: Int, parent: ViewGroup)
            : this(LayoutInflater.from(context).inflate(layoutId, parent, false))

    fun hold(vararg resIds: Int) {
        for (id in resIds) {
            holderViews.put(id, itemView.findViewById(id))
        }
    }

    operator fun <V> get(id: Int): V {
        return holderViews.get(id) as V
    }

    fun setText(@IdRes id: Int, text: String?) {
        get<TextView>(id).text = text
    }

    fun setChecked(@IdRes id: Int, checked: Boolean) {
        get<Checkable>(id).isChecked = checked
    }
}