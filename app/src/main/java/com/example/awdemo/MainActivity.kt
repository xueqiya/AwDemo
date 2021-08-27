package com.example.awdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {
    private lateinit var webViewContainer1: FrameLayout
    private lateinit var webViewContainer2: FrameLayout
    private lateinit var webView1: WebView
    private lateinit var webView2: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WebView.initialize(application)

        setContentView(R.layout.activity_main)
        webViewContainer1 = findViewById(R.id.web_view_container1)
        webViewContainer2 = findViewById(R.id.web_view_container2)

        loadWebView()
    }

    private fun loadWebView() {
        webView1 = WebView(this)
        webViewContainer1.addView(
            webView1,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        )
        webView1.loadUrl("https://www.bing.com")

        webView2 = WebView(this)
        webViewContainer2.addView(
            webView2,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        )
        webView2.loadUrl("https://www.bing.com")
    }

    override fun onBackPressed() {
        if (webView1.canGoBack()) {
            webView1.goBack()
        } else if (webView2.canGoBack()) {
            webView2.goBack()
        } else {
            super.onBackPressed()
        }
    }
}