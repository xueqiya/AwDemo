package com.example.awdemo.webview

import android.app.Activity
import android.app.AlertDialog
import android.view.*
import android.widget.FrameLayout
import org.chromium.android_webview.AwGeolocationPermissions
import org.chromium.android_webview.JsResultReceiver
import java.net.MalformedURLException
import java.net.URL

class MyContentsClient(
    private val activity: Activity,
    private val containerView: ContainerView
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
        AlertDialog.Builder(containerView.context)
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
        callback.invoke(origin, false, false)
    }
}