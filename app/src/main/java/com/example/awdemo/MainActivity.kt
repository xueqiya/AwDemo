package com.example.awdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {
    private lateinit var webViewContainer: FrameLayout
    private lateinit var currentWebView: WebView
    private lateinit var webView1: WebView
    private lateinit var webView2: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        findViewById<Button>(R.id.tab1).setOnClickListener {
            currentWebView = webView1
            webView2.onPause()
            webViewContainer.removeView(webView2)
            webViewContainer.addView(currentWebView)
            webView1.onResume()
        }
        findViewById<Button>(R.id.tab2).setOnClickListener {
            currentWebView = webView2
            webView1.onPause()
            webViewContainer.removeView(webView1)
            webViewContainer.addView(currentWebView)
            webView2.onResume()
        }
    }

    private fun initView() {
        webViewContainer = findViewById(R.id.web_view_container)
        webView1 = WebView(this)
        webView1.loadUrl("https://www.bing.com")
        webView2 = WebView(this)
        webView2.loadUrl("https://www.bing.com")

        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        currentWebView = webView1
        webViewContainer.addView(currentWebView, params)
    }

    override fun onBackPressed() {
        if (currentWebView.canGoBack()) {
            currentWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}