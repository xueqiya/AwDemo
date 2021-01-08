package com.example.awdemo.webview;

import android.graphics.Canvas;

import com.example.awdemo.BuildConfig;

import org.chromium.android_webview.gfx.AwDrawFnImpl;

class DrawFnAccess implements AwDrawFnImpl.DrawFnAccess {
    private final ContainerView containerView;

    public DrawFnAccess(ContainerView containerView) {
        this.containerView = containerView;
    }

    @Override
    public void drawWebViewFunctor(Canvas canvas, int functor) {
        if (BuildConfig.DEBUG && !containerView.isBackedByHardwareView()) {
            throw new AssertionError("Assertion failed");
        }
        containerView.getHardmHardwareView().drawWebViewFunctor(functor);
    }
}