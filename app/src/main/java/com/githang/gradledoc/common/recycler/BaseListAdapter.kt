package com.githang.gradledoc.common.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * @author 黄浩杭 (msdx.android@qq.com)
 * @since 2018-12-19
 */
class BaseListAdapter<D, VH : RecyclerView.ViewHolder>(private val itemCreator: ItemCreator<D, VH>)
    : RecyclerView.Adapter<VH>() {
    private val list = ArrayList<D>()

    var onItemClickListener: OnItemClickListener<D>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return itemCreator.createHolder(parent).apply {
            itemView.setOnClickListener {
                val position = adapterPosition
                onItemClickListener?.onItemClick(it, position, list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        itemCreator.bindData(position, holder, list[position])
    }

    fun update(list: List<D>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}