package com.githang.gradledoc.contents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.githang.android.snippet.adapter.BaseListAdapter;
import com.githang.gradledoc.Constants;
import com.githang.gradledoc.R;
import com.githang.gradledoc.chapter.ChapterActivity;
import com.githang.gradledoc.common.ListActivity;
import com.githang.gradledoc.datasource.HttpProxy;
import com.githang.gradledoc.others.AboutActivity;
import com.githang.gradledoc.others.ContributorsActivity;
import com.githang.gradledoc.process.ProcessActivity;
import com.umeng.update.UmengUpdateAgent;

import java.util.List;


/**
 * 目录。
 *
 * @author Geek_Soledad (msdx.android@qq.com)
 */
public class ContentsActivity extends ListActivity<ChapterUrl> {
    private HttpProxy mHttpProxy;
    private Context mContext;
    private ContentsHandler mContentsHandler = new ContentsHandler() {
        @Override
        public void onResult(List<ChapterUrl> chapterUrls) {
            update(chapterUrls);
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
        mContext = this;
        mHttpProxy = HttpProxy.getInstance(this);
        setTitle(R.string.app_title);

        requestContents();

        UmengUpdateAgent.setUpdateAutoPopup(true);
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        UmengUpdateAgent.update(this);
    }

    private void requestContents() {
        showProgressDialog();
        HttpProxy.getInstance(this).requestUrl(this, Constants.USER_GUIDE, mContentsHandler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contents, menu);
        return true;
    }

    @Override
    @SuppressWarnings("unused")
    protected void onRefresh() {
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
                showProgressDialog();
                mHttpProxy.forceRequestUrl(mContext, Constants.USER_GUIDE, mContentsHandler);
                return true;
            case R.id.action_about:
                startActivity(new Intent(mContext, AboutActivity.class));
                return true;
            case R.id.action_process:
                startActivity(new Intent(mContext, ProcessActivity.class));
                return true;
            case R.id.action_contributors:
                startActivity(new Intent(mContext, ContributorsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public BaseListAdapter.Holder createHolder(int position, ViewGroup parent) {
        BaseListAdapter.Holder holder = new BaseListAdapter.Holder(View.inflate(this, R.layout.item_contents, null));
        holder.hold(R.id.text);
        return holder;
    }

    @Override
    public void bindData(int position, BaseListAdapter.Holder holder, ChapterUrl data) {
        holder.setText(R.id.text, data.getTitle());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ChapterUrl chapterUrl = (ChapterUrl) parent.getAdapter().getItem(position);
        Intent intent = new Intent(mContext, ChapterActivity.class);
        intent.putExtra(Constants.TITLE, chapterUrl.getTitle());
        intent.putExtra(Constants.URL, Constants.BASE_URL + chapterUrl.getUrl());
        startActivity(intent);
    }
}
