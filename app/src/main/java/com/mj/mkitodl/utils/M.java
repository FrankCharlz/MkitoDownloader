package com.mj.mkitodl.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Frank on 1/4/2016.
 */
public class M {

    public static void log(String message) {
        Log.e("007", message);
    }

    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
