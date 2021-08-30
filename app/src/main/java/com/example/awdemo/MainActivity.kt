package com.example.awdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.apkmatrix.components.webview.WebView
import com.example.awdemo.utils.LogUtils
import org.chromium.android_webview.JsPromptResultReceiver
import org.chromium.android_webview.JsResultReceiver
import org.chromium.android_webview.permission.AwPermissionRequest

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var url = "https://www.google.com"
    private val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var urlView: EditText

        @SuppressLint("StaticFieldLeak")
        private lateinit var progressView: TextView
    }

    private lateinit var webViewContainer: FrameLayout
    private lateinit var currentWebView: WebView
    private lateinit var webView1: WebView
    private lateinit var webView2: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        urlView = findViewById(R.id.url)
        progressView = findViewById(R.id.progress)
        webViewContainer = findViewById(R.id.web_view_container)
        findViewById<Button>(R.id.back).setOnClickListener(this)
        findViewById<Button>(R.id.home).setOnClickListener(this)
        findViewById<Button>(R.id.forward).setOnClickListener(this)
        findViewById<Button>(R.id.reload).setOnClickListener(this)
        findViewById<Button>(R.id.tab1).setOnClickListener(this)
        findViewById<Button>(R.id.tab2).setOnClickListener(this)

        webView1 = WebView(this, WebViewClient())
        webView2 = WebView(this, WebViewClient())
        webView1.loadUrl(url)
        webView2.loadUrl(url)
        webViewContainer.addView(webView1, params)
        webViewContainer.addView(webView2, params)

        currentWebView = webView1
        webView2.visibility = View.GONE
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.back -> {
                if (currentWebView.canGoBack()) {
                    currentWebView.goBack()
                }
            }
            R.id.home -> {
                currentWebView.loadUrl(url)
            }
            R.id.forward -> {
                currentWebView.goForward()
            }
            R.id.reload -> {
                currentWebView.reload()
            }
            R.id.tab1 -> {
                if (currentWebView == webView1) return
                webView2.onPause()
                webView2.visibility = View.GONE
                webView1.visibility = View.VISIBLE
                currentWebView = webView1
                currentWebView.onResume()
            }
            R.id.tab2 -> {
                if (currentWebView == webView2) return
                webView1.onPause()
                webView1.visibility = View.GONE
                webView2.visibility = View.VISIBLE
                currentWebView = webView2
                currentWebView.onResume()
            }
        }
    }

    class WebViewClient() : com.apkmatrix.components.webview.WebViewClient() {
        override fun onProgressChanged(progress: Int) {
            super.onProgressChanged(progress)
            LogUtils.d("onProgressChanged:$progress")
            progressView.text = progress.toString()
        }

        override fun onPageStarted(url: String?) {
            super.onPageStarted(url)
            url?.let {
                urlView.setText(url)
            }
        }

        override fun onPermissionRequest(awPermissionRequest: AwPermissionRequest?) {
            awPermissionRequest?.grant()
        }

        override fun handleJsAlert(url: String?, message: String?, receiver: JsResultReceiver?) {
            receiver?.confirm()
        }

        override fun handleJsBeforeUnload(url: String?, message: String?, receiver: JsResultReceiver?) {
            receiver?.confirm()
        }

        override fun handleJsConfirm(url: String?, message: String?, receiver: JsResultReceiver?) {
            receiver?.confirm()
        }

        override fun handleJsPrompt(url: String?, message: String?, defaultValue: String?, receiver: JsPromptResultReceiver?) {
            receiver?.confirm("aaaa")
        }

        override fun onCreateWindow(isDialog: Boolean, isUserGesture: Boolean): Boolean {
            return true
        }
    }


    override fun onResume() {
        super.onResume()
        currentWebView.onResume()
    }

    override fun onPause() {
        super.onPause()
        currentWebView.onPause()
    }

    override fun onBackPressed() {
        if (currentWebView.canGoBack()) {
            currentWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}