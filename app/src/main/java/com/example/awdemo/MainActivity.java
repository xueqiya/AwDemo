package com.example.awdemo;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.awdemo.webview.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout content_container = findViewById(R.id.content_container);
        WebView webView = new WebView(this);
        content_container.addView(webView);
        webView.loadUrl("http://www.bing.com");
    }
}
