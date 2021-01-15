package com.example.awdemo.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;

import org.chromium.android_webview.AwContents;
import org.chromium.content_public.browser.WebContents;

@SuppressLint("ViewConstructor")
public class WebView extends FrameLayout {
    private AwContents mAwContents;
    private final AwContents.InternalAccessDelegate mInternalAccessDelegate;

    private final HardwareView mHardwareView;

    public WebView(Context context) {
        super(context);
        mHardwareView = new HardwareView(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mHardwareView, layoutParams);
        mInternalAccessDelegate = new InternalAccessAdapter(this, mAwContents, mHardwareView);
        setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    public void setAwContents(AwContents awContents) {
        mAwContents = awContents;
        mHardwareView.setReadyToRenderCallback(() -> mAwContents.onAttachedToWindow());
    }

    public WebContents getWebContents() {
        if (mAwContents == null) return null;
        return mAwContents.getWebContents();
    }

    public AwContents getAwContents() {
        if (mAwContents == null) return null;
        return mAwContents;
    }

    public AwContents.NativeDrawFunctorFactory getNativeDrawFunctorFactory() {
        return new NativeDrawFunctorFactory(this);
    }

    public AwContents.InternalAccessDelegate getInternalAccessDelegate() {
        return mInternalAccessDelegate;
    }

    public void onResume() {
        if (mAwContents == null) return;
        mAwContents.onResume();
        mHardwareView.onResume();
    }

    public void onPause() {
        if (mAwContents == null) return;
        mAwContents.onPause();
        mHardwareView.onPause();
    }

    public void onDestroy() {
        if (mAwContents == null) return;
        mAwContents.destroy();
    }

//    private void detachedContentsInternal() {
//        if (BuildConfig.DEBUG && !mAttachedContents) {
//            throw new AssertionError("Assertion failed");
//        }
//        mAwContents.onDetachedFromWindow();
//        mAttachedContents = false;
//        if (mHardwareView != null) {
//            mHardwareView.awContentsDetached();
//        }
//    }

    @Override
    public void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (mAwContents == null) return;
        mAwContents.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return mAwContents.onCreateInputConnection(outAttrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mAwContents == null) return;
        mAwContents.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onSizeChanged(int w, int h, int ow, int oh) {
        super.onSizeChanged(w, h, ow, oh);
        if (mAwContents == null) return;
        mAwContents.onSizeChanged(w, h, ow, oh);
    }

    @Override
    public void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        if (mAwContents == null) return;
        mAwContents.onContainerViewOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mAwContents == null) return;
        mAwContents.onContainerViewScrollChanged(l, t, oldl, oldt);
    }

    public void setMeasuredDimension2(int measuredWidth, int measuredHeight) {
        WebView.super.setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    public void computeScroll() {
        if (mAwContents == null) return;
        mAwContents.computeScroll();
    }

    @Override
    public void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (mAwContents == null) return;
        mAwContents.onVisibilityChanged(changedView, visibility);
    }

    @Override
    public void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (mAwContents == null) return;
        mAwContents.onWindowVisibilityChanged(visibility);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        if (mAwContents == null) return false;
        return mAwContents.onTouchEvent(ev);
    }

    @Override
    public boolean onHoverEvent(MotionEvent ev) {
        super.onHoverEvent(ev);
        if (mAwContents == null) return false;
        return mAwContents.onHoverEvent(ev);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (mAwContents == null) return;
        mHardwareView.updateScroll(getScrollX(), getScrollY());
        mAwContents.onDraw(canvas);
        super.onDraw(canvas);
    }

    @Override
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        if (mAwContents == null) return null;
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
        if (mAwContents == null) return false;
        return mAwContents.onDragEvent(event);
    }

    public HardwareView getHardmHardwareView() {
        return mHardwareView;
    }
}
