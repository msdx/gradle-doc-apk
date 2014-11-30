package com.githang.gradledoc.chapter;

import android.text.Editable;
import android.text.Html;

import org.xml.sax.XMLReader;

/**
 * 扩展的HTML标签处理者。
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-11-30
 * Time: 19:12
 */
public class ExtendedTagHandler implements Html.TagHandler {
    private static final String LOG_TAG = ExtendedTagHandler.class.getSimpleName();
    boolean first = true;

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if (tag.equals("li")) {
            char lastChar = 0;
            if (output.length() > 0)
                lastChar = output.charAt(output.length() - 1);
            if (first) {
                if (lastChar == '\n')
                    output.append("\t•  ");
                else
                    output.append("\n\t•  ");
                first = false;
            } else {
                first = true;
            }
        } else if (tag.equals("pre")) {
            handlerPre(output);
        } else if (tag.equals("tr")) {
            output.append("\n");
        } else if (tag.equals("td")) {
            output.append("\t");
        }
    }

    private void handlerPre(Editable output) {
//        Log.d(LOG_TAG, output.toString());
    }
}
