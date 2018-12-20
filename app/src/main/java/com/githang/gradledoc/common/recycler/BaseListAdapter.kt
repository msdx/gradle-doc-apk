package com.githang.gradledoc.common.recycler

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * @author 黄浩杭 (msdx.android@qq.com)
 * @since 2018-12-19
 */
class BaseListAdapter<D>(
        private val layoutId: Int,
        private val brId: Int
) : RecyclerView.Adapter<BindingHolder>() {
    private val list = ArrayList<D>()

    var onItemClickListener: OnItemClickListener<D>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, layoutId, parent, false)
        val holder = BindingHolder(binding)
        return holder.apply {
            itemView.setOnClickListener {
                val position = adapterPosition
                onItemClickListener?.onItemClick(it, position, list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.binding.setVariable(brId, list[position])
        holder.binding.executePendingBindings()
    }

    fun update(list: List<D>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}