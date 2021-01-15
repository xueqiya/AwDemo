package com.example.awdemo.webview;

import android.graphics.Canvas;

import org.chromium.android_webview.gfx.AwDrawFnImpl;

class DrawFnAccess implements AwDrawFnImpl.DrawFnAccess {
    private final WebView webView;

    public DrawFnAccess(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void drawWebViewFunctor(Canvas canvas, int functor) {
        webView.getHardmHardwareView().drawWebViewFunctor(functor);
    }
}