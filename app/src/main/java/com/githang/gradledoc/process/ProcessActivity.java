package com.githang.gradledoc.process;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.githang.android.snippet.adapter.BaseListAdapter;
import com.githang.gradledoc.R;
import com.githang.gradledoc.common.BaseRefreshActivity;
import com.githang.gradledoc.datasource.HttpProxy;

import java.util.List;

public class ProcessActivity extends BaseRefreshActivity {
    private static final String URL_PROCESS = "https://github.com/msdx/gradledoc/commits/1.12";
    private ListView mListView;
    private Context mContext;

    private ProcessHandler mProcessHandler = new ProcessHandler() {
        @Override
        public void onResult(final List<Commit> commits) {
            mListView.setAdapter(new BaseListAdapter<Commit>(mContext, commits, R.layout.item_process) {
                @Override
                protected void holdView(View parent, Holder holder) {
                    holder.hold(R.id.commit_title, R.id.commit_meta);
                }

                @Override
                protected void bindData(int position, Holder holder, Commit commit) {
                    holder.holdText(R.id.commit_title, commit.getTitle());
                    holder.holdText(R.id.commit_meta, commit.getMeta());
                }
            });
        }

        @Override
        public void onUIFinish() {
            dismissProgressDialog();
        }

        @Override
        public void onUIFailed(Throwable e) {
            Toast.makeText(mContext, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mListView = (ListView) findViewById(android.R.id.list);

        showProgressDialog();
        boolean isCache = HttpProxy.getInstance(this).requestUrl(this, URL_PROCESS, mProcessHandler);
        if(isCache) {
            onRefresh();
        }
    }

    @Override
    protected void onRefresh() {
        showProgressDialog();
        HttpProxy.getInstance(this).forceRequestUrl(mContext, URL_PROCESS, mProcessHandler);
    }
}
