package com.example.awdemo

import android.graphics.PixelFormat
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.awdemo.utils.LogUtils
import com.example.awdemo.webview.ContainerView
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
    private lateinit var containerView: ContainerView
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
        containerView = ContainerView(this)
        containerView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f
        )
        contentContainer.addView(containerView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                val awContents = createAwContents()
                awContents.loadUrl(HOME_URL)
                mPageList.add(awContents)
            }
            R.id.dele -> {

            }
            R.id.getInfo -> {

            }
            R.id.back -> {
                containerView.awContents?.goBack()
            }
            R.id.forward -> {
                containerView.awContents?.goForward()
            }
            R.id.reload -> {
                containerView.awContents?.reload()
            }
            R.id.home -> {
                containerView.awContents?.loadUrl(HOME_URL)
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
        containerView.awContents = awContents
        containerView.invalidate()
    }

    override fun onPause() {
        super.onPause()
        containerView.onPause()
    }

    override fun onResume() {
        super.onResume()
        containerView.onResume()
    }

    private fun createAwContents(): AwContents {
        val awContentsClient: AwContentsClient = MyContentsClient(this, containerView)

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
        awSettings.displayZoomControls = false
        awSettings.useWideViewPort = true
        awSettings.loadWithOverviewMode = true
        awSettings.layoutAlgorithm = AwSettings.LAYOUT_ALGORITHM_TEXT_AUTOSIZING
        awSettings.javaScriptEnabled = true

        containerView.awContents = AwContents(
            browserContext, containerView,
            containerView.context, containerView.internalAccessDelegate,
            containerView.nativeDrawFunctorFactory, awContentsClient, awSettings
        )

        if (mDevToolsServer == null) {
            mDevToolsServer = AwDevToolsServer()
            mDevToolsServer!!.setRemoteDebuggingEnabled(true)
        }
        return containerView.awContents
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