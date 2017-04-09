package com.githang.gradledoc.app.others;

import android.os.Bundle;
import android.widget.TextView;

import com.githang.gradledoc.BuildConfig;
import com.githang.gradledoc.R;
import com.githang.gradledoc.common.BaseActivity;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        final TextView version = (TextView) findViewById(R.id.about_version);
        version.setText("版本: " + BuildConfig.VERSION_NAME + "_" + BuildConfig.REVISION);
    }
}
