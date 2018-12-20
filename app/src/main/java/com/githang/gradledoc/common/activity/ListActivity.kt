package com.githang.gradledoc.common.activity

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.githang.gradledoc.R
import com.githang.gradledoc.common.recycler.BaseListAdapter
import com.githang.gradledoc.common.recycler.OnItemClickListener

/**
 * 列表界面
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2015-12-03
 * @since 2015-12-03
 */
abstract class ListActivity<T> : BaseRefreshActivity<List<T>>(), OnItemClickListener<T> {

    protected abstract val itemLayoutId: Int
    protected abstract val itemBrId: Int

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BaseListAdapter<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        recyclerView = findViewById(android.R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dividerHeight = resources.getDimensionPixelSize(R.dimen.divider_height)
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View?, parent: RecyclerView, state: RecyclerView.State?) {
                if (parent.getChildAdapterPosition(view) != 0) {
                    outRect.set(0, dividerHeight, 0, 0)
                }
            }
        })
        adapter = BaseListAdapter(itemLayoutId, itemBrId)
        adapter.onItemClickListener = this
        recyclerView.adapter = adapter
    }

    override fun onItemClick(view: View, position: Int, item: T) {}

    override fun onHandle(data: List<T>) {
        adapter.update(data)
    }
}
