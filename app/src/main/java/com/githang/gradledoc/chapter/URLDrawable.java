package com.githang.gradledoc.chapter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-30
 * Time: 00:09
 * FIXME
 */
public class URLDrawable extends BitmapDrawable {
//    protected Drawable drawable;
    protected Bitmap bitmap;

    @Override
    public void draw(Canvas canvas) {
//        if (drawable != null) {
//            drawable.draw(canvas);
//        }
        if(bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, getPaint());
        }

    }


}
