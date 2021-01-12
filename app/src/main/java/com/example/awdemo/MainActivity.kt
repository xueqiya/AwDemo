package com.example.awdemo

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.awdemo.utils.LogUtils
import com.example.awdemo.webview.ContainerView
import com.example.awdemo.webview.ContentsClient
import org.chromium.android_webview.*
import org.chromium.android_webview.shell.AwShellResourceProvider
import org.chromium.android_webview.shell.AwShellSwitches
import org.chromium.base.CommandLine
import org.chromium.base.Log
import org.chromium.base.TraceEvent
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.CopyOnWriteArrayList

class MainActivity : AppCompatActivity() {
    private var mBrowserContext: AwBrowserContext? = null
    private var mDevToolsServer: AwDevToolsServer? = null
    private var currentContainerView: ContainerView? = null
    private var content_container: FrameLayout? = null
    private var mUrlTextView: EditText? = null
    private val mPageList = CopyOnWriteArrayList<ContainerView>()

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
        if (CommandLine.getInstance().hasSwitch(AwShellSwitches.ENABLE_ATRACE)) {
            Log.e(TAG, "Enabling Android trace.")
            TraceEvent.setATraceEnabled(true)
        }
    }

    private fun initView() {
        val tool_bar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(tool_bar)
        content_container = findViewById(R.id.content_container)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                val containerView = createAwTestContainerView()
                containerView.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f
                )
                containerView.awContents?.loadUrl(HOME_URL)
                mPageList.add(containerView)
            }
            R.id.dele -> {

            }
            R.id.getInfo -> {

            }
            R.id.back -> {
                currentContainerView?.awContents?.goBack()
            }
            R.id.forward -> {
                currentContainerView?.awContents?.goForward()
            }
            R.id.reload -> {
                currentContainerView?.awContents?.reload()
            }
            R.id.home -> {
                currentContainerView?.awContents?.loadUrl(HOME_URL)
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
        currentContainerView?.onPause()
        currentContainerView = mPageList[index]
        currentContainerView?.onResume()
        content_container!!.removeAllViews()
        content_container!!.addView(currentContainerView)
    }

    private fun createAwTestContainerView(): ContainerView {
        val testContainerView = ContainerView(this, false)
        val awContentsClient: AwContentsClient = object : ContentsClient() {
            private var mCustomView: View? = null
            override fun handleJsConfirm(url: String, message: String, receiver: JsResultReceiver) {
                var title = "From "
                try {
                    val javaUrl = URL(url)
                    title += javaUrl.protocol + "://" + javaUrl.host
                    if (javaUrl.port != -1) {
                        title += ":" + javaUrl.port
                    }
                } catch (e: MalformedURLException) {
                    title += url
                }
                AlertDialog.Builder(testContainerView.context)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(
                        "OK"
                    ) { dialogInterface, i -> receiver.confirm() }
                    .setNegativeButton(
                        "Cancel"
                    ) { dialogInterface, i -> receiver.cancel() }
                    .create()
                    .show()
            }

            override fun onPageStarted(url: String) {
                if (mUrlTextView != null) {
                    mUrlTextView!!.setText(url)
                }
            }

            override fun onShowCustomView(view: View, callback: CustomViewCallback) {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
                window.addContentView(
                    view,
                    FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        Gravity.CENTER
                    )
                )
                mCustomView = view
            }

            override fun onHideCustomView() {
                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                val decorView = window.decorView as FrameLayout
                decorView.removeView(mCustomView)
                mCustomView = null
            }

            override fun shouldOverrideKeyEvent(event: KeyEvent): Boolean {
                return event.keyCode == KeyEvent.KEYCODE_BACK
            }

            override fun onGeolocationPermissionsShowPrompt(
                origin: String, callback: AwGeolocationPermissions.Callback
            ) {
                callback.invoke(origin, false, false)
            }
        }
        val sharedPreferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
        if (mBrowserContext == null) {
            mBrowserContext = AwBrowserContext(
                sharedPreferences, AwBrowserContext.getDefault().nativePointer, true
            )
        }
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
        awSettings.displayZoomControls = false
        awSettings.useWideViewPort = true
        awSettings.loadWithOverviewMode = true
        awSettings.layoutAlgorithm = AwSettings.LAYOUT_ALGORITHM_TEXT_AUTOSIZING
        testContainerView.initialize(
            AwContents(
                mBrowserContext, testContainerView,
                testContainerView.context, testContainerView.internalAccessDelegate,
                testContainerView.nativeDrawFunctorFactory, awContentsClient, awSettings
            )
        )
        testContainerView.awContents.settings.javaScriptEnabled = true
        if (mDevToolsServer == null) {
            mDevToolsServer = AwDevToolsServer()
            mDevToolsServer!!.setRemoteDebuggingEnabled(true)
        }
        return testContainerView
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