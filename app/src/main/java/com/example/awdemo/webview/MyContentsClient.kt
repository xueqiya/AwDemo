package com.example.awdemo.webview

import android.app.Activity
import android.app.AlertDialog
import android.view.*
import android.widget.FrameLayout
import com.example.awdemo.utils.LogUtils
import org.chromium.android_webview.AwGeolocationPermissions
import org.chromium.android_webview.JsPromptResultReceiver
import org.chromium.android_webview.JsResultReceiver
import org.chromium.android_webview.permission.AwPermissionRequest
import java.net.MalformedURLException
import java.net.URL

class MyContentsClient(
    private val activity: Activity,
    private val webView: WebView
) : ContentsClient() {
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
        AlertDialog.Builder(webView.context)
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

    override fun handleJsAlert(url: String?, message: String?, receiver: JsResultReceiver?) {
        AlertDialog.Builder(webView.context)
            .setTitle(url)
            .setMessage(message)
            .setPositiveButton(
                "OK"
            ) { dialogInterface, i -> receiver?.confirm() }
            .setNegativeButton(
                "Cancel"
            ) { dialogInterface, i -> receiver?.cancel() }
            .create()
            .show()
    }

    override fun handleJsPrompt(
        url: String?,
        message: String?,
        defaultValue: String?,
        receiver: JsPromptResultReceiver?
    ) {
        AlertDialog.Builder(webView.context)
            .setTitle(url)
            .setMessage(message)
            .setPositiveButton(
                "OK"
            ) { dialogInterface, i -> receiver?.confirm("asd") }
            .setNegativeButton(
                "Cancel"
            ) { dialogInterface, i -> receiver?.cancel() }
            .create()
            .show()
    }

    override fun onDownloadStart(
        url: String?,
        userAgent: String?,
        contentDisposition: String?,
        mimeType: String?,
        contentLength: Long
    ) {
        super.onDownloadStart(url, userAgent, contentDisposition, mimeType, contentLength)
        LogUtils.d("onDownloadStart$url")
    }

    override fun shouldOverrideUrlLoading(request: AwWebResourceRequest?): Boolean {
        val url = request?.url!!
        return if (url.startsWith("http://") || url.startsWith("https://")) {
            webView.awContents.loadUrl(url)
            true
        } else {
            false
        }
    }

    override fun onProgressChanged(progress: Int) {
        super.onProgressChanged(progress)
        LogUtils.d("onProgressChanged$progress")
    }

    override fun onPageStarted(url: String) {

    }

    override fun onShowCustomView(view: View, callback: CustomViewCallback) {
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        activity.window.addContentView(
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
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val decorView = activity.window.decorView as FrameLayout
        decorView.removeView(mCustomView)
        mCustomView = null
    }

    override fun shouldOverrideKeyEvent(event: KeyEvent): Boolean {
        return event.keyCode == KeyEvent.KEYCODE_BACK
    }

    override fun onGeolocationPermissionsShowPrompt(
        origin: String, callback: AwGeolocationPermissions.Callback
    ) {
        callback.invoke(origin, true, true)
    }

    override fun onPermissionRequest(awPermissionRequest: AwPermissionRequest?) {
        awPermissionRequest?.grant()
    }
}