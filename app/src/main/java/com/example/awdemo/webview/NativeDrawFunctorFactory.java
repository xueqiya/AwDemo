package com.example.awdemo.webview;

import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.gfx.AwDrawFnImpl;

class NativeDrawFunctorFactory implements AwContents.NativeDrawFunctorFactory {
    private ContainerView containerView;

    public NativeDrawFunctorFactory(ContainerView containerView) {
        this.containerView = containerView;
    }

    @Override
    public AwContents.NativeDrawGLFunctor createGLFunctor(long context) {
        return null;
    }

    @Override
    public AwDrawFnImpl.DrawFnAccess getDrawFnAccess() {
        return new DrawFnAccess(containerView);
    }
}
