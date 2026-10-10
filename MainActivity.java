package com.vicanhdan.haitrinhhuyenthoai;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    // If the server is running in Termux on THIS phone, keep localhost.
    // If hosted elsewhere, replace this with the server's reachable URL.
    private static final String GAME_URL = "http://127.0.0.1:8080/";
    private WebView webView;
    private TextView errorView;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(8, 11, 20));
        getWindow().setNavigationBarColor(Color.rgb(8, 11, 20));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        FrameLayout root = new FrameLayout(this);
        root.setBackgroundColor(Color.rgb(8, 11, 20));
        webView = new WebView(this);
        errorView = new TextView(this);
        errorView.setTextColor(Color.WHITE);
        errorView.setTextSize(16);
        errorView.setGravity(Gravity.CENTER);
        errorView.setPadding(32, 32, 32, 32);
        errorView.setText("Không kết nối được server game.\n\nHãy mở server trong Termux rồi chạm vào đây để thử lại.\n\nĐịa chỉ: " + GAME_URL);
        errorView.setVisibility(View.GONE);
        errorView.setOnClickListener(v -> loadGame());
        root.addView(webView, new FrameLayout.LayoutParams(-1, -1));
        root.addView(errorView, new FrameLayout.LayoutParams(-1, -1));
        setContentView(root);

        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setMediaPlaybackRequiresUserGesture(false);
        s.setLoadsImagesAutomatically(true);
        s.setAllowFileAccess(false);
        s.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                // Keep game navigation inside the app; external links are also kept in WebView for simplicity.
                return false;
            }
            @Override public void onPageFinished(WebView view, String url) { errorView.setVisibility(View.GONE); }
            @Override public void onReceivedError(WebView view, WebResourceRequest request, android.webkit.WebResourceError error) {
                if (request.isForMainFrame()) errorView.setVisibility(View.VISIBLE);
            }
        });
        loadGame();
    }

    private void loadGame() {
        errorView.setVisibility(View.GONE);
        webView.loadUrl(GAME_URL);
    }

    @Override public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }

    @Override protected void onDestroy() {
        if (webView != null) { webView.loadUrl("about:blank"); webView.destroy(); }
        super.onDestroy();
    }
}
