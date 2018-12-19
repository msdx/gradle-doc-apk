package com.githang.gradledoc.app.process

import android.os.Bundle
import android.view.View
import android.view.ViewGroup

import com.githang.android.snippet.adapter.ItemHolder
import com.githang.gradledoc.Constants
import com.githang.gradledoc.R
import com.githang.gradledoc.common.ListActivity

class ProcessActivity : ListActivity<Commit>() {
    private val presenter = ProcessPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.request(this, Constants.URL_PROCESS)
    }

    override fun onRefresh() {
        presenter.request(this, Constants.URL_PROCESS, true)
    }

    override fun createHolder(position: Int, parent: ViewGroup): ItemHolder.DefaultHolder {
        val holder = ItemHolder.DefaultHolder(View.inflate(this, R.layout.item_process, null))
        holder.hold(R.id.commit_title, R.id.commit_meta)
        return holder
    }

    override fun bindData(position: Int, holder: ItemHolder.DefaultHolder, commit: Commit) {
        holder.setText(R.id.commit_title, commit.title)
        holder.setText(R.id.commit_meta, commit.meta)
    }
}
