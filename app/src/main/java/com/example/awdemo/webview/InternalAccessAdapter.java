package com.example.awdemo.webview;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.MotionEvent;

import org.chromium.android_webview.AwContents;

class InternalAccessAdapter implements AwContents.InternalAccessDelegate {
    private final ContainerView containerView;
    private final AwContents awContents;
    private final HardwareView hardwareView;

    public InternalAccessAdapter(ContainerView containerView, AwContents awContents, HardwareView hardwareView) {
        this.containerView = containerView;
        this.awContents = awContents;
        this.hardwareView = hardwareView;
    }

    @Override
    public boolean super_onKeyUp(int keyCode, KeyEvent event) {
        return awContents.onKeyUp(keyCode, event);
    }

    @Override
    public boolean super_dispatchKeyEvent(KeyEvent event) {
        return awContents.dispatchKeyEvent(event);
    }

    @Override
    public boolean super_onGenericMotionEvent(MotionEvent event) {
        return awContents.onGenericMotionEvent(event);
    }

    @Override
    public void super_onConfigurationChanged(Configuration newConfig) {
        awContents.onConfigurationChanged(newConfig);
    }

    @Override
    public void super_scrollTo(int scrollX, int scrollY) {
        // We're intentionally not calling super.scrollTo here to make testing easier.
        containerView.scrollTo(scrollX, scrollY);
        // Undo the scroll that will be applied because of mHardwareView
        // being a child of |this|.
        hardwareView.setTranslationX(scrollX);
        hardwareView.setTranslationY(scrollY);
    }

    @Override
    public void overScrollBy(int deltaX, int deltaY,
                             int scrollX, int scrollY,
                             int scrollRangeX, int scrollRangeY,
                             int maxOverScrollX, int maxOverScrollY,
                             boolean isTouchEvent) {
        // We're intentionally not calling super.scrollTo here to make testing easier.
        containerView.overScrollBy(deltaX, deltaY, scrollX, scrollY,
                scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        containerView.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public void setMeasuredDimension(int measuredWidth, int measuredHeight) {
        containerView.setMeasuredDimension2(measuredWidth, measuredHeight);
    }

    @Override
    public int super_getScrollBarStyle() {
        return containerView.getScrollBarStyle();
    }

    @Override
    public void super_startActivityForResult(Intent intent, int requestCode) {
    }
}