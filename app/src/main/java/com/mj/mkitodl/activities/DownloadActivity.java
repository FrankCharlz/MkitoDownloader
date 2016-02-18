package com.mj.mkitodl.activities;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.mj.mkitodl.R;
import com.mj.mkitodl.utils.M;

/**
 * Created by Frank on 2/18/2016.
 *
 * Fuck UE
 */

public class DownloadActivity extends AppCompatActivity {
    public static final String SONG_URL_CARRIER = "s763";
    private static final String SONG_NAME_CARRIER = "638hds";
    private String song_url;
    private WebView webView;
    private String song_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        song_url = getIntent().getStringExtra(SONG_URL_CARRIER);
        song_name = getIntent().getStringExtra(SONG_NAME_CARRIER);

        webView = (WebView) findViewById(R.id.webview_download);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVisibility(View.VISIBLE);
        webView.loadUrl(song_url);

        webView.setDownloadListener(new MyWebViewClient());


    }

    private class MyWebViewClient extends WebViewClient implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String user_agent, String s2, String mimetype, long length) {
            M.log("Downloading: " + url + "\nsize: " + (length / 1024) + " KB");
            Toast.makeText(getApplicationContext(),
                    "Downloading: "+url+"\nsize: "+(length/1024)+" KB",
                    Toast.LENGTH_LONG)
                    .show();

            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, song_name);
// You can change the name of the downloads, by changing "download" to everything you want, such as the mWebview title...
            DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            dm.enqueue(request);
        }
    }
}
