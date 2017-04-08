package com.githang.gradledoc.app.others;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.githang.gradledoc.R;
import com.githang.gradledoc.common.BaseActivity;

public class AboutActivity extends BaseActivity {
    private TextView mVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mVersion = (TextView) findViewById(R.id.about_version);
        setVersion();
    }

    private void setVersion() {
        ApplicationInfo info = this.getApplicationInfo();
        mVersion.setText("版本: ");
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(info.packageName, 0);
            mVersion.append(" V");
            mVersion.append(packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
