package com.githang.gradledoc.app.others;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.githang.android.snippet.adapter.BaseListAdapter;
import com.githang.android.snippet.adapter.ItemHolder;
import com.githang.gradledoc.Constants;
import com.githang.gradledoc.R;
import com.githang.gradledoc.common.ListActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 贡献。
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2015-12-02
 * @since 2015-12-02
 */
public class ContributorsActivity extends ListActivity<Contributor, ContributorsPresenter> {

    private ContributorsPresenter<ContributorsActivity> mPresenter = new ContributorsPresenter<>(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.request(this, Constants.URL_CONTRIBUTORS);
    }

    @Override
    protected void onRefresh() {
        showProgressDialog();
        mPresenter.request(this, Constants.URL_CONTRIBUTORS, true);
    }

    @Override
    public ItemHolder.DefaultHolder createHolder(int position, ViewGroup parent) {
        ItemHolder.DefaultHolder holder = new ItemHolder.DefaultHolder(View.inflate(this, R.layout.item_contributor, null));
        holder.hold(R.id.name, R.id.contributions, R.id.avatar);
        return holder;
    }

    @Override
    public void bindData(int position, ItemHolder.DefaultHolder holder, Contributor data) {
        holder.setText(R.id.name, data.getName());
        holder.setText(R.id.contributions, data.getContributions() + " contributions");
        ImageView imageView = holder.get(R.id.avatar);
        ImageLoader.getInstance().displayImage(data.getAvatar(), imageView);
    }
}
