package com.githang.gradledoc.process;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.githang.gradledoc.R;
import com.githang.gradledoc.common.BaseBackActivity;
import com.githang.gradledoc.common.BaseListAdapter;
import com.githang.gradledoc.datasource.HttpProxy;

import java.util.List;

public class ProcessActivity extends BaseBackActivity {
    private static final String URL_PROCESS = "https://github.com/msdx/gradledoc/commits/1.12";
    private ListView mListView;
    private Context mContext;

    private ProcessHandler mProcessHandler = new ProcessHandler() {
        @Override
        public void onResult(List<Commit> commits) {
            mListView.setAdapter(new BaseListAdapter<Commit>(mContext, commits, R.layout.item_process) {
                @Override
                public void initItemView(int position, View itemView) {
                    Commit commit = getItem(position);
                    ((TextView)itemView.findViewById(R.id.commit_title)).setText(commit.getTitle());
                    ((TextView)itemView.findViewById(R.id.commit_meta)).setText(commit.getMeta());
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
            showProgressDialog();
            HttpProxy.getInstance(this).forceRequestUrl(this, URL_PROCESS, mProcessHandler);
        }
    }

    @Override
    protected void onRefresh() {
        showProgressDialog();
        HttpProxy.getInstance(this).forceRequestUrl(mContext, URL_PROCESS, mProcessHandler);
    }
}
