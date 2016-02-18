package com.mj.mkitodl.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mj.mkitodl.R;

/**
 * Created by Frank on 2/18/2016.
 *
 * ssk
 */

public class DownloadActivity extends AppCompatActivity {
    public static final String SONG_URL_CARRIER = "s763";
    private String url;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);


        url = getIntent().getStringExtra(SONG_URL_CARRIER);


        webView = (WebView) findViewById(R.id.webview_download);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVisibility(View.VISIBLE);
        webView.loadUrl(url);


    }

    private class MyWebViewClient extends WebViewClient {

    }
}
