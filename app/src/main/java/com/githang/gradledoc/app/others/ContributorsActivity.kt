package com.githang.gradledoc.app.others

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.githang.android.snippet.adapter.ItemHolder
import com.githang.gradledoc.Constants
import com.githang.gradledoc.R
import com.githang.gradledoc.common.ImageLoader
import com.githang.gradledoc.common.ListActivity

/**
 * 贡献。
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2015-12-02
 * @since 2015-12-02
 */
class ContributorsActivity : ListActivity<Contributor>() {

    private val presenter = ContributorsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.request(this, Constants.URL_CONTRIBUTORS)
    }

    override fun onRefresh() {
        showProgressDialog()
        presenter.request(this, Constants.URL_CONTRIBUTORS, true)
    }

    override fun createHolder(position: Int, parent: ViewGroup): ItemHolder.DefaultHolder {
        val holder = ItemHolder.DefaultHolder(View.inflate(this, R.layout.item_contributor, null))
        holder.hold(R.id.name, R.id.contributions, R.id.avatar)
        return holder
    }

    override fun bindData(position: Int, holder: ItemHolder.DefaultHolder, data: Contributor) {
        holder.setText(R.id.name, data.name)
        holder.setText(R.id.contributions, data.contributions.toString() + " contributions")
        val imageView = holder.get<ImageView>(R.id.avatar)
        ImageLoader.loadImage(data.avatar, imageView)
    }
}
