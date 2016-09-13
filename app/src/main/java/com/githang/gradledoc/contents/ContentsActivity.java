package com.githang.gradledoc.contents;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.githang.android.snippet.adapter.BaseListAdapter;
import com.githang.gradledoc.Constants;
import com.githang.gradledoc.R;
import com.githang.gradledoc.chapter.ChapterActivity;
import com.githang.gradledoc.common.ListActivity;
import com.githang.gradledoc.others.AboutActivity;
import com.githang.gradledoc.others.ContributorsActivity;
import com.githang.gradledoc.process.ProcessActivity;
import com.umeng.update.UmengUpdateAgent;


/**
 * 目录。
 *
 * @author Geek_Soledad (msdx.android@qq.com)
 */
public class ContentsActivity extends ListActivity<ChapterUrl, ContentPresenter> {
    private ContentPresenter<ContentsActivity> mPresenter = new ContentPresenter<>(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_title);
        mPresenter.request(this, Constants.USER_GUIDE);

        UmengUpdateAgent.setUpdateAutoPopup(true);
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        UmengUpdateAgent.update(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contents, menu);
        return true;
    }

    @Override
    protected void onRefresh() {
        mPresenter.forceRequest(this, Constants.USER_GUIDE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            case R.id.action_process:
                startActivity(new Intent(this, ProcessActivity.class));
                return true;
            case R.id.action_contributors:
                startActivity(new Intent(this, ContributorsActivity.class));
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
        Intent intent = new Intent(this, ChapterActivity.class);
        intent.putExtra(Constants.TITLE, chapterUrl.getTitle());
        intent.putExtra(Constants.URL, Constants.BASE_URL + chapterUrl.getUrl());
        startActivity(intent);
    }
}
