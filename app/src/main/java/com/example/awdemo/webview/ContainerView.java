// Copyright 2012 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package com.example.awdemo.webview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;

import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.gfx.AwDrawFnImpl;
import org.chromium.android_webview.shell.DrawFn;
import org.chromium.base.Callback;
import org.chromium.content_public.browser.WebContents;

import java.nio.ByteBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ContainerView extends FrameLayout {
    private AwContents mAwContents;
    private final AwContents.InternalAccessDelegate mInternalAccessDelegate;

    private HardwareView mHardwareView;
    private boolean mAttachedContents;

    private Rect mWindowVisibleDisplayFrameOverride;

    private static boolean sCreatedOnce;

    private HardwareView createHardwareViewOnlyOnce(Context context) {
        if (sCreatedOnce) return null;
        sCreatedOnce = true;
        return new HardwareView(context);
    }

    public ContainerView(Context context, boolean allowHardwareAcceleration) {
        super(context);
        if (allowHardwareAcceleration) {
            mHardwareView = createHardwareViewOnlyOnce(context);
        }
        if (isBackedByHardwareView()) {
            addView(mHardwareView,
                    new LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            LayoutParams.MATCH_PARENT));
        } else {
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }
        mInternalAccessDelegate = new InternalAccessAdapter(this, mAwContents, mHardwareView);
        setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    public void initialize(AwContents awContents) {
        mAwContents = awContents;
        if (isBackedByHardwareView()) {
            AwDrawFnImpl.setDrawFnFunctionTable(DrawFn.getDrawFnFunctionTable());
        }
    }

    public void setWindowVisibleDisplayFrameOverride(Rect rect) {
        mWindowVisibleDisplayFrameOverride = rect;
    }

    @Override
    public void getWindowVisibleDisplayFrame(Rect outRect) {
        if (mWindowVisibleDisplayFrameOverride != null) {
            outRect.set(mWindowVisibleDisplayFrameOverride);
        } else {
            super.getWindowVisibleDisplayFrame(outRect);
        }
    }

    public boolean isBackedByHardwareView() {
        return mHardwareView != null;
    }

    /**
     * Use glReadPixels to get 4 pixels from center of 4 quadrants. Result is in row-major order.
     */
    public void readbackQuadrantColors(Callback<int[]> callback) {
        assert isBackedByHardwareView();
        mHardwareView.readbackQuadrantColors(callback);
    }

    public WebContents getWebContents() {
        return mAwContents.getWebContents();
    }

    public AwContents getAwContents() {
        return mAwContents;
    }

    public AwContents.NativeDrawFunctorFactory getNativeDrawFunctorFactory() {
        return new NativeDrawFunctorFactory(this);
    }

    public AwContents.InternalAccessDelegate getInternalAccessDelegate() {
        return mInternalAccessDelegate;
    }

    public void destroy() {
        mAwContents.destroy();
    }

    private void attachedContentsInternal() {
        assert !mAttachedContents;
        mAwContents.onAttachedToWindow();
        mAttachedContents = true;
    }

    private void detachedContentsInternal() {
        assert mAttachedContents;
        mAwContents.onDetachedFromWindow();
        mAttachedContents = false;
        if (mHardwareView != null) {
            mHardwareView.awContentsDetached();
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mHardwareView == null || mHardwareView.isReadyToRender()) {
            attachedContentsInternal();
        } else {
            mHardwareView.setReadyToRenderCallback(() -> attachedContentsInternal());
        }

        if (mHardwareView != null) {
            mHardwareView.setReadyToDetachCallback(() -> detachedContentsInternal());
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mHardwareView == null || mHardwareView.isReadyToRender()) {
            detachedContentsInternal();

            if (mHardwareView != null) {
                mHardwareView.setReadyToRenderCallback(null);
                mHardwareView.setReadyToDetachCallback(null);
            }
        }
    }

    @Override
    public void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        mAwContents.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return mAwContents.onCreateInputConnection(outAttrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mAwContents.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onSizeChanged(int w, int h, int ow, int oh) {
        super.onSizeChanged(w, h, ow, oh);
        mAwContents.onSizeChanged(w, h, ow, oh);
    }

    @Override
    public void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        mAwContents.onContainerViewOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mAwContents != null) {
            mAwContents.onContainerViewScrollChanged(l, t, oldl, oldt);
        }
    }

    public void setMeasuredDimension2(int measuredWidth, int measuredHeight) {
        ContainerView.super.setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    public void computeScroll() {
        mAwContents.computeScroll();
    }

    @Override
    public void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        mAwContents.onVisibilityChanged(changedView, visibility);
    }

    @Override
    public void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        mAwContents.onWindowVisibilityChanged(visibility);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        return mAwContents.onTouchEvent(ev);
    }

    @Override
    public boolean onHoverEvent(MotionEvent ev) {
        super.onHoverEvent(ev);
        return mAwContents.onHoverEvent(ev);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (isBackedByHardwareView()) {
            mHardwareView.updateScroll(getScrollX(), getScrollY());
        }
        mAwContents.onDraw(canvas);
        super.onDraw(canvas);
    }

    @Override
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        AccessibilityNodeProvider provider =
                mAwContents.getAccessibilityNodeProvider();
        return provider == null ? super.getAccessibilityNodeProvider() : provider;
    }

    @Override
    public boolean performAccessibilityAction(int action, Bundle arguments) {
        return mAwContents.performAccessibilityAction(action, arguments);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.N)
    public boolean onDragEvent(DragEvent event) {
        return mAwContents.onDragEvent(event);
    }

    public HardwareView getHardmHardwareView() {
        return mHardwareView;
    }
}
