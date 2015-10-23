package com.githang.gradledoc.contents;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.githang.gradledoc.Consts;
import com.githang.gradledoc.R;
import com.githang.gradledoc.chapter.ChapterActivity;
import com.githang.gradledoc.common.BaseActivity;
import com.githang.gradledoc.datasource.HttpProxy;
import com.githang.gradledoc.others.AboutActivity;
import com.githang.gradledoc.process.ProcessActivity;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

import java.util.List;


/**
 * 目录。
 *
 * @author Geek_Soledad (msdx.android@qq.com)
 */
public class ContentsActivity extends BaseActivity {
    private static final String LOG_TAG = ContentsActivity.class.getSimpleName();

    private ProgressDialog mProgressDialog;
    private ListView mListView;

    private HttpProxy mHttpProxy;
    private Context mContext;
    private ContentsHandler mContentsHandler = new ContentsHandler() {
        @Override
        public void onResult(List<ChapterUrl> chapterUrls) {
            ArrayAdapter<ChapterUrl> adapter = new ArrayAdapter<ChapterUrl>(mContext, R.layout.item_contents, chapterUrls);
            mListView.setAdapter(adapter);
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
        mContext = this;
        mHttpProxy = HttpProxy.getInstance(this);
        getSupportActionBar().setTitle(R.string.app_title);
        setContentView(R.layout.activity_contents);
        mListView = (ListView) findViewById(R.id.contents);
        mListView.addHeaderView(new View(this));
        mListView.addFooterView(new View(this));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChapterUrl chapterUrl = (ChapterUrl) parent.getAdapter().getItem(position);
                Intent intent = new Intent(mContext, ChapterActivity.class);
                intent.putExtra(Consts.TITLE, chapterUrl.getTitle());
                intent.putExtra(Consts.URL, Consts.BASE_URL + chapterUrl.getUrl());
                startActivity(intent);
            }
        });
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mHttpProxy.cancelRequests(mContext);
            }
        });

        requestContents();

        UmengUpdateAgent.setUpdateAutoPopup(true);
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        UmengUpdateAgent.update(this);
    }

    private void requestContents() {
        mProgressDialog.show();
        HttpProxy.getInstance(this).requestUrl(this, Consts.USER_GUIDE, mContentsHandler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contents, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_refresh:
                mProgressDialog.show();
                mHttpProxy.forceRequestUrl(mContext, Consts.USER_GUIDE, mContentsHandler);
                return true;
            case R.id.action_about:
                startActivity(new Intent(mContext, AboutActivity.class));
                return true;
            case R.id.action_process:
                startActivity(new Intent(mContext, ProcessActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }

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
}
