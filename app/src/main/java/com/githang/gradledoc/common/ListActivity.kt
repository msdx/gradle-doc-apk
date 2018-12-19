package com.githang.gradledoc.common

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView

import com.githang.android.snippet.adapter.BaseListAdapter
import com.githang.android.snippet.adapter.ItemCreator
import com.githang.android.snippet.adapter.ItemHolder
import com.githang.gradledoc.R

/**
 * 列表界面
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2015-12-03
 * @since 2015-12-03
 */
abstract class ListActivity<T> : BaseRefreshActivity<List<T>>(), ItemCreator<T, ItemHolder.DefaultHolder>, AdapterView.OnItemClickListener {
    private lateinit var listView: ListView
    private lateinit var adapter: BaseListAdapter<T, ItemHolder.DefaultHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        listView = findViewById<View>(android.R.id.list) as ListView
        adapter = BaseListAdapter<T, ItemHolder.DefaultHolder>(this)
        listView.adapter = adapter
        listView.onItemClickListener = this
    }

    protected fun update(data: List<T>) {
        adapter.update(data)
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {}

    override fun onHandle(data: List<T>) {
        update(data)
    }
}
