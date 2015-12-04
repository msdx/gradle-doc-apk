package com.githang.gradledoc.process;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.githang.android.snippet.adapter.BaseListAdapter;
import com.githang.gradledoc.R;
import com.githang.gradledoc.common.ListActivity;
import com.githang.gradledoc.datasource.HttpProxy;

import java.util.List;

public class ProcessActivity extends ListActivity<Commit> {
    private static final String URL_PROCESS = "https://github.com/msdx/gradledoc/commits/1.12";
    private Context mContext;

    private ProcessHandler mProcessHandler = new ProcessHandler() {
        @Override
        public void onResult(final List<Commit> commits) {
            update(commits);
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

    @Override
    public BaseListAdapter.Holder createHolder(int position, ViewGroup parent) {
        BaseListAdapter.Holder holder = new BaseListAdapter.Holder(View.inflate(this, R.layout.item_process, null));
        holder.hold(R.id.commit_title, R.id.commit_meta);
        return holder;
    }

    @Override
    public void bindData(int position, BaseListAdapter.Holder holder, Commit commit) {
        holder.setText(R.id.commit_title, commit.getTitle());
        holder.setText(R.id.commit_meta, commit.getMeta());
    }
}
