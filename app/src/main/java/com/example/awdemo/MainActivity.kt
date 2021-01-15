package com.example.awdemo

import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.view.*
import android.webkit.WebSettings
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.awdemo.utils.LogUtils
import com.example.awdemo.webview.WebView
import com.example.awdemo.webview.MyContentsClient
import org.chromium.android_webview.*
import org.chromium.android_webview.gfx.AwDrawFnImpl
import org.chromium.android_webview.shell.AwShellResourceProvider
import org.chromium.android_webview.shell.AwShellSwitches
import org.chromium.android_webview.shell.DrawFn
import org.chromium.base.CommandLine
import org.chromium.base.Log
import org.chromium.base.TraceEvent
import java.util.concurrent.CopyOnWriteArrayList

class MainActivity : AppCompatActivity() {
    private var mDevToolsServer: AwDevToolsServer? = null
    private lateinit var webView: WebView
    private val mPageList = CopyOnWriteArrayList<AwContents>()

    companion object {
        private const val TAG = "AwShellActivity"
        private const val PREFERENCES_NAME = "AwShellPrefs"
        private const val HOME_URL = "https://www.bing.com/?mkt=zh-CN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun init() {
        AwShellResourceProvider.registerResources(this)
        AwBrowserProcess.loadLibrary(null)
        AwBrowserProcess.start()
        AwDrawFnImpl.setDrawFnFunctionTable(DrawFn.getDrawFnFunctionTable())
        if (CommandLine.getInstance().hasSwitch(AwShellSwitches.ENABLE_ATRACE)) {
            Log.e(TAG, "Enabling Android trace.")
            TraceEvent.setATraceEnabled(true)
        }
    }

    private fun initView() {
        window.setFormat(PixelFormat.TRANSLUCENT)

        val toolBar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)

        val contentContainer = findViewById<FrameLayout>(R.id.content_container)
        webView = WebView(this)
        webView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f
        )
        contentContainer.addView(webView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                val awContents = createAwContents()
                awContents.loadUrl(HOME_URL)
                mPageList.add(awContents)
            }
            R.id.back -> {
                webView.awContents?.goBack()
            }
            R.id.forward -> {
                webView.awContents?.goForward()
            }
            R.id.reload -> {
                webView.awContents?.reload()
            }
            R.id.home -> {
                webView.awContents?.loadUrl(HOME_URL)
            }
            R.id.tab0 -> {
                tab(0)
            }
            R.id.tab1 -> {
                tab(1)
            }
            R.id.tab2 -> {
                tab(2)
            }
            R.id.tab3 -> {
                tab(3)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tab(index: Int) {
        if (mPageList.size <= index) {
            LogUtils.e("Need to add first")
            return
        }
        val awContents = mPageList[index]
        webView.awContents = awContents
        webView.invalidate()
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    private fun createAwContents(): AwContents {
        val awContentsClient: AwContentsClient = MyContentsClient(this, webView)

        val sharedPreferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
        val browserContext =
            AwBrowserContext(sharedPreferences, AwBrowserContext.getDefault().nativePointer, true)

        val awSettings = AwSettings(
            this,
            false,
            false,
            false,
            true,
            false
        )
        awSettings.mediaPlaybackRequiresUserGesture = false
        awSettings.builtInZoomControls = true
        awSettings.loadWithOverviewMode = true
        awSettings.layoutAlgorithm = AwSettings.LAYOUT_ALGORITHM_TEXT_AUTOSIZING
        awSettings.javaScriptEnabled = true
        awSettings.userAgentString = "Mozilla/5.0 (Linux; U; Android 4.0.4; en-US; MK16i Build/4.1.B.0.587) AppleWebKit/528.5+ (KHTML, like Gecko) Version/3.1.2 Mobile Safari/525.20.1 UCBrowser/8.9.2.373 Mobile"
        awSettings.setSupportMultipleWindows(false)
        awSettings.useWideViewPort = true
        awSettings.setSupportZoom(true)
        awSettings.displayZoomControls = false
        awSettings.allowContentAccess = true
        awSettings.allowFileAccess = true
        awSettings.databaseEnabled = true
        awSettings.setGeolocationEnabled(true)
        awSettings.javaScriptCanOpenWindowsAutomatically = true
        awSettings.allowFileAccessFromFileURLs = false
        awSettings.allowUniversalAccessFromFileURLs = false
        awSettings.saveFormData = true
        awSettings.domStorageEnabled = true
        awSettings.setAppCacheEnabled(true)
        awSettings.databaseEnabled = true
        awSettings.cacheMode = WebSettings.LOAD_DEFAULT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            awSettings.mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
        }

        webView.awContents = AwContents(
            browserContext, webView,
            webView.context, webView.internalAccessDelegate,
            webView.nativeDrawFunctorFactory, awContentsClient, awSettings
        )

        if (mDevToolsServer == null) {
            mDevToolsServer = AwDevToolsServer()
            mDevToolsServer!!.setRemoteDebuggingEnabled(true)
        }
        return webView.awContents
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_control, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onDestroy() {
        if (mDevToolsServer != null) {
            mDevToolsServer!!.destroy()
            mDevToolsServer = null
        }
        super.onDestroy()
    }
}