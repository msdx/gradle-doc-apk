package com.githang.gradledoc.app.process

import android.os.Bundle
import com.githang.gradledoc.BR
import com.githang.gradledoc.Constants
import com.githang.gradledoc.R
import com.githang.gradledoc.common.activity.ListActivity

class ProcessActivity : ListActivity<Commit>() {
    override val itemLayoutId: Int = R.layout.item_process
    override val itemBrId: Int = BR.commit

    private val presenter = ProcessPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.request(this, Constants.URL_PROCESS)
    }

    override fun onRefresh() {
        presenter.request(this, Constants.URL_PROCESS, true)
    }
}
