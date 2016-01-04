package com.mj.mkitodl.activities;

import android.content.Context;

import com.mj.mkitodl.utils.Constants;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;

/**
 * Created by Frank on 12/31/2015.
 */
public enum HttpClient {
    INSTANCE;

    private final OkHttpClient client;

    HttpClient() {
        client = new OkHttpClient();
    }

    public OkHttpClient getClient() {
        return client;
    }
}
