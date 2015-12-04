package com.githang.gradledoc.others;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.githang.android.snippet.adapter.BaseListAdapter;
import com.githang.gradledoc.Consts;
import com.githang.gradledoc.R;
import com.githang.gradledoc.common.ListActivity;
import com.githang.gradledoc.datasource.AbstractResponse;
import com.githang.gradledoc.datasource.HttpProxy;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 贡献。
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2015-12-02
 * @since 2015-12-02
 */
public class ContributorsActivity extends ListActivity<Contributor> {
    private AbstractResponse mResponse = new AbstractResponse() {
        @Override
        public void onUISuccess(String content) {
            List<Contributor> contributors = JSON.parseArray(content, Contributor.class);
            update(contributors);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showProgressDialog();
        HttpProxy.getInstance(this).requestUrl(this, Consts.URL_CONTRIBUTORS, mResponse);
    }

    @Override
    protected void onRefresh() {
        showProgressDialog();
        HttpProxy.getInstance(this).forceRequestUrl(this, Consts.URL_CONTRIBUTORS, mResponse);
    }

    @Override
    public BaseListAdapter.Holder createHolder(int position, ViewGroup parent) {
        BaseListAdapter.Holder holder = new BaseListAdapter.Holder(View.inflate(this, R.layout.item_contributor, null));
        holder.hold(R.id.name, R.id.contributions, R.id.avatar);
        return holder;
    }

    @Override
    public void bindData(int position, BaseListAdapter.Holder holder, Contributor data) {
        holder.setText(R.id.name, data.getName());
        holder.setText(R.id.contributions, data.getContributions() + " contributions");
        ImageView imageView = holder.get(R.id.avatar);
        ImageLoader.getInstance().displayImage(data.getAvatar(), imageView);
    }
}
