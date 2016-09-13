package com.githang.gradledoc.process;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.githang.android.snippet.adapter.BaseListAdapter;
import com.githang.gradledoc.Constants;
import com.githang.gradledoc.R;
import com.githang.gradledoc.common.ListActivity;

public class ProcessActivity extends ListActivity<Commit, ProcessPresenter> {
    private ProcessPresenter<ProcessActivity> mPresenter = new ProcessPresenter<>(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.request(this, Constants.URL_PROCESS);
    }

    @Override
    protected void onRefresh() {
        mPresenter.forceRequest(this, Constants.URL_PROCESS);
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
