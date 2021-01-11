package com.example.awdemo.utils

import android.util.Log

object LogUtils {
    var isDebug: Boolean = true
    private const val TAG: String = "BROWSER_DEMO_LOG"

    @JvmStatic
    fun d(msg: String) {
        if (isDebug) {
            Log.d(TAG, msg)
        }
    }

    @JvmStatic
    fun e(msg: String) {
        if (isDebug) {
            Log.e(TAG, msg)
        }
    }

    @JvmStatic
    fun v(msg: String) {
        if (isDebug) {
            Log.v(TAG, msg)
        }
    }
}