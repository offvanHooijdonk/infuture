package com.willthishappen.infuture.helper;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

public class ColorsHelper {

    public static CharSequence applyColorToText(String text, int color) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        ssb.setSpan(new ForegroundColorSpan(color), 0, text.length(), 0);

        return ssb;
    }
}
