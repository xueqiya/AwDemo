package com.example.awdemo.webview;

import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.gfx.AwDrawFnImpl;

class NativeDrawFunctorFactory implements AwContents.NativeDrawFunctorFactory {
    private WebView webView;

    public NativeDrawFunctorFactory(WebView webView) {
        this.webView = webView;
    }

    @Override
    public AwContents.NativeDrawGLFunctor createGLFunctor(long context) {
        return null;
    }

    @Override
    public AwDrawFnImpl.DrawFnAccess getDrawFnAccess() {
        return new DrawFnAccess(webView);
    }
}
