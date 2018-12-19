package com.githang.gradledoc.app.chapter;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.githang.gradledoc.Constants;
import com.githang.gradledoc.R;
import com.githang.gradledoc.common.BaseRefreshActivity;

/**
 * @author Geek_Soledad (msdx.android@qq.com)
 *         文章内容
 */
public class ChapterActivity extends BaseRefreshActivity<String> {
    private String mUrl;

    private WebView mDocView;

    private ChapterPresenter<ChapterActivity> mPresenter = new ChapterPresenter<>(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        Bundle bundle = getIntent().getExtras();
        mUrl = bundle.getString(Constants.URL);
        setTitle(bundle.getString(Constants.TITLE));
        initView();

        mPresenter.request(this, mUrl);
    }

    private void initView() {
        mDocView = (WebView) findViewById(R.id.doc);
        WebSettings settings = mDocView.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mDocView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            mDocView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
    }

    @Override
    protected void onRefresh() {
        mPresenter.request(this, mUrl, true);
    }

    @Override
    public void onHandle(String content) {
        mDocView.loadDataWithBaseURL(Constants.BASE_URL, content, "text/html", "charset=UTF-8", null);
    }
}
