package com.githang.gradledoc.chapter;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.githang.gradledoc.Constants;
import com.githang.gradledoc.R;
import com.githang.gradledoc.common.BaseRefreshActivity;
import com.githang.gradledoc.datasource.AbstractResponse;
import com.githang.gradledoc.datasource.HttpProxy;
import com.umeng.analytics.MobclickAgent;

public class ChapterActivity extends BaseRefreshActivity {
    private String url;

    private HttpProxy mHttpProxy;

    private Context mContext;

    private WebView mDocView;

    private AbstractResponse mChapterHandler = new ChapterHandler() {
        @Override
        public void onUISuccess(String content) {
            mDocView.loadDataWithBaseURL(Constants.BASE_URL, content, "text/html", "charset=UTF-8", null);
        }

        @Override
        public void onUIFailed(Throwable e) {
            Toast.makeText(mContext, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUIFinish() {
            dismissProgressDialog();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        mContext = this;
        mHttpProxy = HttpProxy.getInstance(this);

        Bundle bundle = getIntent().getExtras();
        url = bundle.getString(Constants.URL);
        setTitle(bundle.getString(Constants.TITLE));
        initView();

        requestContents();
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

    private void requestContents() {
        showProgressDialog();
        HttpProxy.getInstance(this).requestUrl(this, url, mChapterHandler);
    }

    @Override
    protected void onRefresh() {
        showProgressDialog();
        mHttpProxy.forceRequestUrl(mContext, url, mChapterHandler);
    }
}
