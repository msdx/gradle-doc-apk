package com.githang.gradledoc.app.others

import android.os.Bundle
import com.githang.gradledoc.BR
import com.githang.gradledoc.Constants
import com.githang.gradledoc.R
import com.githang.gradledoc.common.ListActivity

/**
 * 贡献。
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2015-12-02
 * @since 2015-12-02
 */
class ContributorsActivity : ListActivity<Contributor>() {
    override val itemLayoutId: Int = R.layout.item_contributor
    override val itemBrId: Int = BR.contributor

    private val presenter = ContributorsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.request(this, Constants.URL_CONTRIBUTORS)
    }

    override fun onRefresh() {
        showProgressDialog()
        presenter.request(this, Constants.URL_CONTRIBUTORS, true)
    }
}
