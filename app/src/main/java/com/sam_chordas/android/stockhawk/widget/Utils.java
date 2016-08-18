package com.sam_chordas.android.stockhawk.widget;

import com.sam_chordas.android.stockhawk.R;

/**
 * Created by Priyam on 18-08-2016.
 */
public class Utils {
    public static int getResId(String string) {
        if (string.equals("1"))
            return R.mipmap.ic_stock_up;
        else
            return R.mipmap.ic_stock_down;
    }
}
