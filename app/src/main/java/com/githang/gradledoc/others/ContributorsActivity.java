package com.githang.gradledoc.others;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.githang.android.snippet.adapter.BaseListAdapter;
import com.githang.gradledoc.Consts;
import com.githang.gradledoc.R;
import com.githang.gradledoc.common.BaseBackActivity;
import com.githang.gradledoc.datasource.AbstractResponse;
import com.githang.gradledoc.datasource.HttpProxy;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * 贡献。
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2015-12-02
 * @since 2015-12-02
 */
public class ContributorsActivity extends BaseBackActivity {
    private AbstractResponse mResponse = new AbstractResponse() {
        @Override
        public void onUISuccess(String content) {
            List<Contributor> contributors = JSON.parseArray(content, Contributor.class);
            mAdapter.update(contributors);
        }

        @Override
        public void onUIFailed(Throwable e) {
            Toast.makeText(ContributorsActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUIFinish() {
            dismissProgressDialog();
        }
    };

    private ListView mListView;
    private BaseListAdapter<Contributor> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_list);
        mListView = (ListView) findViewById(android.R.id.list);
        mAdapter = new BaseListAdapter<Contributor>(this, new ArrayList<Contributor>(),
                R.layout.item_contributor) {
            @Override
            protected void holdView(View parent, Holder holder) {
                holder.hold(R.id.avatar, R.id.name, R.id.contributions);
            }

            @Override
            protected void bindData(int position, Holder holder, Contributor data) {
                holder.holdText(R.id.name, data.getName());
                holder.holdText(R.id.contributions, data.getContributions() + " contributions");
                ImageView imageView = holder.get(R.id.avatar);
                ImageLoader.getInstance().displayImage(data.getAvatar(), imageView);
            }

        };
        mListView.setAdapter(mAdapter);
        onRefresh();
    }

    @Override
    protected void onRefresh() {
        showProgressDialog();
        HttpProxy.getInstance(this).requestUrl(this, Consts.URL_CONTRIBUTORS, mResponse);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
}
