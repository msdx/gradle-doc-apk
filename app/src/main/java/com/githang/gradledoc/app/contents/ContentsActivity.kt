package com.githang.gradledoc.app.contents

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

import com.githang.android.snippet.adapter.ItemHolder
import com.githang.gradledoc.Constants
import com.githang.gradledoc.R
import com.githang.gradledoc.app.chapter.ChapterActivity
import com.githang.gradledoc.app.others.AboutActivity
import com.githang.gradledoc.app.others.ContributorsActivity
import com.githang.gradledoc.app.process.ProcessActivity
import com.githang.gradledoc.common.ListActivity
import com.umeng.update.UmengUpdateAgent

/**
 * 目录。
 *
 * @author Geek_Soledad (msdx.android@qq.com)
 */
class ContentsActivity : ListActivity<ChapterUrl>() {
    private val presenter = ContentPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.app_title)
        presenter.request(this, Constants.USER_GUIDE)

        UmengUpdateAgent.setUpdateAutoPopup(true)
        UmengUpdateAgent.setUpdateOnlyWifi(false)
        UmengUpdateAgent.update(this)
    }

    override fun displayHomeAsUp() = false

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_contents, menu)
        return true
    }

    override fun onRefresh() {
        presenter.request(this, Constants.USER_GUIDE, true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return when (id) {
            R.id.action_about -> {
                startActivity(AboutActivity::class.java)
                true
            }
            R.id.action_process -> {
                startActivity(ProcessActivity::class.java)
                true
            }
            R.id.action_contributors -> {
                startActivity(ContributorsActivity::class.java)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
    }

    override fun createHolder(position: Int, parent: ViewGroup): ItemHolder.DefaultHolder {
        val holder = ItemHolder.DefaultHolder(View.inflate(this, R.layout.item_contents, null))
        holder.hold(R.id.text)
        return holder
    }

    override fun bindData(position: Int, holder: ItemHolder.DefaultHolder, data: ChapterUrl) {
        holder.setText(R.id.text, data.title)
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val chapterUrl = parent.adapter.getItem(position) as ChapterUrl
        val intent = Intent(this, ChapterActivity::class.java)
        intent.putExtra(Constants.TITLE, chapterUrl.title)
        intent.putExtra(Constants.URL, Constants.BASE_URL + chapterUrl.url)
        startActivity(intent)
    }
}
