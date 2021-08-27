package com.example.awdemo

import android.app.Application
import com.apkmatrix.components.webview.WebView

/**
 * @Author xueqi
 * @Date 2021/8/27 3:29 下午
 * @Description: TODO
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        WebView.initialize(this)
    }
}