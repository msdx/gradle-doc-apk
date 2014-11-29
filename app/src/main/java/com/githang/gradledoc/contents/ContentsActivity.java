package com.githang.gradledoc.contents;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.githang.gradledoc.Consts;
import com.githang.gradledoc.R;
import com.githang.gradledoc.datasource.HttpProxy;

import java.util.List;


public class ContentsActivity extends ActionBarActivity {

    private ActionBar mActionBar;
    private ProgressDialog mProgressDialog;
    private ListView mListView;

    private HttpProxy mHttpProxy;
    private Context mContext;
    private ContentsHandler mContentsHandler = new ContentsHandler(){
        @Override
        public void onResult(List<ChapterUrl> chapterUrls) {
            ArrayAdapter<ChapterUrl> adapter = new ArrayAdapter<ChapterUrl>(mContext, R.layout.item_contents, chapterUrls);
            mListView.setAdapter(adapter);
        }

        @Override
        public void onFinish() {
            mProgressDialog.dismiss();
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mHttpProxy = HttpProxy.getInstance(this);
        setContentView(R.layout.activity_contents);
        mListView = (ListView) findViewById(R.id.contents);
        mListView.addHeaderView(new View(this));
        mListView.addFooterView(new View(this));
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(R.string.app_title);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setMessage(getString(R.string.refreshing));
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mHttpProxy.cancelRequests(mContext);
            }
        });

        requestContents();
    }

    private void requestContents() {
        HttpProxy.getInstance(this).requestUrl(this, Consts.USER_GUIDE, mContentsHandler);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contents, menu);
        return true;
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
                mProgressDialog.show();
                mHttpProxy.forceRequestUrl(mContext, Consts.USER_GUIDE, mContentsHandler);
                return true;
            case R.id.action_about:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
