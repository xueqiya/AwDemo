package com.example.awdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.apkmatrix.components.webview.WebView

class MainActivity : AppCompatActivity() {
    private val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    private lateinit var webViewContainer: FrameLayout
    private lateinit var currentWebView: WebView
    private lateinit var webView1: WebView
    private lateinit var webView2: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        findViewById<Button>(R.id.tab1).setOnClickListener {
            if (currentWebView == webView1) return@setOnClickListener
            webView2.onPause()
            webViewContainer.removeView(webView2)
            currentWebView = webView1
            webViewContainer.addView(currentWebView, params)
            currentWebView.onResume()
        }
        findViewById<Button>(R.id.tab2).setOnClickListener {
            if (currentWebView == webView2) return@setOnClickListener
            webView1.onPause()
            webViewContainer.removeView(webView1)
            currentWebView = webView2
            webViewContainer.addView(currentWebView, params)
            currentWebView.onResume()
        }
    }

    private fun initView() {
        webViewContainer = findViewById(R.id.web_view_container)
        webView1 = WebView(this)
        webView1.loadUrl("https://www.google.com")
        webView2 = WebView(this)
        webView2.loadUrl("https://www.google.com")

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