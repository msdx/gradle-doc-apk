package com.githang.gradledoc.common;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.githang.android.snippet.adapter.BaseListAdapter;
import com.githang.android.snippet.adapter.ItemCreator;
import com.githang.android.snippet.adapter.ItemHolder;
import com.githang.gradledoc.R;

import java.util.List;

/**
 * 列表界面
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2015-12-03
 * @since 2015-12-03
 */
public abstract class ListActivity<T> extends BaseRefreshActivity<List<T>> implements ItemCreator<T, ItemHolder.DefaultHolder>, AdapterView.OnItemClickListener {
    private ListView mListView;
    private BaseListAdapter<T, ItemHolder.DefaultHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mListView = (ListView) findViewById(android.R.id.list);
        mAdapter = new BaseListAdapter<>(this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    protected void update(List<T> data) {
        mAdapter.update(data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onHandle(List<T> list) {
        update(list);
    }
}
