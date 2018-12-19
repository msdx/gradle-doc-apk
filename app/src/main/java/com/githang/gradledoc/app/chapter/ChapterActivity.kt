package com.githang.gradledoc.app.chapter

import android.os.Build
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView

import com.githang.gradledoc.Constants
import com.githang.gradledoc.R
import com.githang.gradledoc.common.BaseRefreshActivity

/**
 * 文章内容
 * @author Geek_Soledad (msdx.android@qq.com)
 */
class ChapterActivity : BaseRefreshActivity<String>() {
    private var url: String? = null

    private lateinit var docView: WebView

    private val presenter = ChapterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)
        initView()

        val bundle = intent?.extras ?: return
        url = bundle.getString(Constants.URL)
        title = bundle.getString(Constants.TITLE)

        presenter.request(this, url ?: return)
    }

    private fun initView() {
        docView = findViewById(R.id.doc)
        val settings = docView.settings
        settings.loadWithOverviewMode = true
        settings.setSupportZoom(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        } else {
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        }
    }

    override fun onRefresh() {
        presenter.request(this, url ?: return, true)
    }

    override fun onHandle(data: String) {
        docView.loadDataWithBaseURL(Constants.BASE_URL, data, "text/html", "charset=UTF-8", null)
    }

    override fun onDestroy() {
        docView.stopLoading()
        docView.destroy()
        super.onDestroy()
    }
}
