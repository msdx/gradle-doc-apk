package com.githang.gradledoc;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-29
 * Time: 23:47
 */
public class GradleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initUmeng();
        initImageLoader();
    }

    private void initImageLoader() {
        try {
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                    .diskCache(new LruDiscCache(getCacheDir(),
                            new Md5FileNameGenerator(), 20 * 1024 * 1024))
                    .memoryCache(new LruMemoryCache(8 * 1024 * 1024))
                    .defaultDisplayImageOptions(options).build();
            ImageLoader.getInstance().init(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initUmeng() {
        MobclickAgent.setDebugMode(BuildConfig.DEBUG);
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.updateOnlineConfig(this);

        MobclickAgent.setCatchUncaughtExceptions(true);
    }
}
