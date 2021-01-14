package com.example.awdemo.webview;

import android.graphics.Canvas;

import org.chromium.android_webview.gfx.AwDrawFnImpl;

class DrawFnAccess implements AwDrawFnImpl.DrawFnAccess {
    private final ContainerView containerView;

    public DrawFnAccess(ContainerView containerView) {
        this.containerView = containerView;
    }

    @Override
    public void drawWebViewFunctor(Canvas canvas, int functor) {
        containerView.getHardmHardwareView().drawWebViewFunctor(functor);
    }
}