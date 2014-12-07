package com.githang.gradledoc.chapter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-30
 * Time: 00:09
 * FIXME
 */
public class URLDrawable extends BitmapDrawable {
    protected Bitmap mBitmap;
    protected Rect mSrc;

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
        mSrc = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
    }

    @Override
    public void draw(Canvas canvas) {
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, mSrc, getBounds(), getPaint());
        }
    }
}
