package com.githang.gradledoc.app.process

import android.os.Bundle
import android.view.ViewGroup
import com.githang.gradledoc.Constants
import com.githang.gradledoc.R
import com.githang.gradledoc.common.ListActivity
import com.githang.gradledoc.common.recycler.DefaultHolder

class ProcessActivity : ListActivity<Commit>() {
    private val presenter = ProcessPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.request(this, Constants.URL_PROCESS)
    }

    override fun onRefresh() {
        presenter.request(this, Constants.URL_PROCESS, true)
    }

    override fun createHolder(parent: ViewGroup): DefaultHolder {
        val holder = DefaultHolder(this, R.layout.item_process, parent)
        holder.hold(R.id.commit_title, R.id.commit_meta)
        return holder
    }

    override fun bindData(position: Int, holder: DefaultHolder, commit: Commit) {
        holder.setText(R.id.commit_title, commit.title)
        holder.setText(R.id.commit_meta, commit.meta)
    }
}
