package com.githang.gradledoc.chapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.githang.gradledoc.Consts;
import com.githang.gradledoc.R;
import com.githang.gradledoc.common.BaseBackActivity;
import com.githang.gradledoc.datasource.HttpProxy;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.umeng.analytics.MobclickAgent;

public class ChapterActivity extends BaseBackActivity {
    private static final String LOG_TAG = ChapterActivity.class.getSimpleName();
    private String url;

    private HttpProxy mHttpProxy;

    private Context mContext;

    private TextView mDocView;
    private ProgressDialog mProgressDialog;

    private ChapterHandler mChapterHandler = new ChapterHandler() {
        @Override
        public void onUISuccess(String content) {
            mDocView.setText(Html.fromHtml(content, new URLImageParser(mDocView), new ExtendedTagHandler()));
        }

        @Override
        public void onUIFailed(Throwable e) {
            Toast.makeText(mContext, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUIFinish() {
            mProgressDialog.dismiss();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        mContext = this;
        mHttpProxy = HttpProxy.getInstance(this);

        Bundle bundle = getIntent().getExtras();
        url = bundle.getString(Consts.URL);
        getSupportActionBar().setTitle(bundle.getString(Consts.TITLE));
        initViews();

        requestContents();
    }


    private void requestContents() {
        mProgressDialog.show();
        HttpProxy.getInstance(this).requestUrl(this, url, mChapterHandler);
    }


    private void initViews() {
        mDocView = (TextView) findViewById(R.id.doc);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mHttpProxy.cancelRequests(mContext);
            }
        });
    }


    @Override
     public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chapter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            mProgressDialog.show();
            mHttpProxy.forceRequestUrl(mContext, url, mChapterHandler);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(LOG_TAG);
        MobclickAgent.onPause(mContext);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(LOG_TAG);
        MobclickAgent.onResume(mContext);
    }


    public class URLImageParser implements Html.ImageGetter {
        TextView mTextView;

        public URLImageParser(TextView textView) {
            this.mTextView = textView;
        }

        @Override
        public Drawable getDrawable(String source) {
            final URLDrawable urlDrawable = new URLDrawable();
            Log.d("ChapterActivity", Consts.BASE_URL + source);
            ImageLoader.getInstance().loadImage(Consts.BASE_URL + source, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    urlDrawable.setBitmap(loadedImage);
                    int width =  mTextView.getWidth() - 2 * mTextView.getTotalPaddingRight();
                    int height = loadedImage.getHeight() * width / loadedImage.getWidth();
                    urlDrawable.setBounds(0, 0, width, height);
                    mTextView.invalidate();
                    mTextView.setText(mTextView.getText());
                }
            });
            return urlDrawable;
        }
    }
}
