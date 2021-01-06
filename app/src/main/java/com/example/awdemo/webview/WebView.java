package com.example.awdemo.webview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WebView extends FrameLayout {

    private WebViewProvider mProvider;

    public WebView(@NonNull Context context) {
        super(context);
    }

    public WebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mProvider = new WebViewChromium(this, new PrivateAccess());
        mProvider.init();
    }

    public void loadUrl(String url) {
        mProvider.loadUrl(url);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mProvider.onDraw(canvas);
    }

    public class PrivateAccess {
        // ---- Access to super-class methods ----
        public int super_getScrollBarStyle() {
            return WebView.super.getScrollBarStyle();
        }

        public void super_scrollTo(int scrollX, int scrollY) {
            WebView.super.scrollTo(scrollX, scrollY);
        }

        public void super_computeScroll() {
            WebView.super.computeScroll();
        }

        public boolean super_onHoverEvent(MotionEvent event) {
            return WebView.super.onHoverEvent(event);
        }

//        public boolean super_performAccessibilityAction(int action, Bundle arguments) {
//            return  WebView.super.performAccessibilityActionInternal(action, arguments);
//        }

        public boolean super_performLongClick() {
            return WebView.super.performLongClick();
        }

//        public boolean super_setFrame(int left, int top, int right, int bottom) {
//            return  WebView.super.setFrame(left, top, right, bottom);
//        }

        public boolean super_dispatchKeyEvent(KeyEvent event) {
            return WebView.super.dispatchKeyEvent(event);
        }

        public boolean super_onGenericMotionEvent(MotionEvent event) {
            return WebView.super.onGenericMotionEvent(event);
        }

        public boolean super_requestFocus(int direction, Rect previouslyFocusedRect) {
            return WebView.super.requestFocus(direction, previouslyFocusedRect);
        }

        public void super_setLayoutParams(ViewGroup.LayoutParams params) {
            WebView.super.setLayoutParams(params);
        }

//        public void super_startActivityForResult(Intent intent, int requestCode) {
//             WebView.super.startActivityForResult(intent, requestCode);
//        }

        // ---- Access to non-public methods ----
        public void overScrollBy(int deltaX, int deltaY,
                                 int scrollX, int scrollY,
                                 int scrollRangeX, int scrollRangeY,
                                 int maxOverScrollX, int maxOverScrollY,
                                 boolean isTouchEvent) {
            WebView.this.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY,
                    maxOverScrollX, maxOverScrollY, isTouchEvent);
        }

        public void awakenScrollBars(int duration) {
            WebView.this.awakenScrollBars(duration);
        }

        public void awakenScrollBars(int duration, boolean invalidate) {
            WebView.this.awakenScrollBars(duration, invalidate);
        }

//        public float getVerticalScrollFactor() {
//            return  WebView.this.getVerticalScrollFactor();
//        }
//
//        public float getHorizontalScrollFactor() {
//            return  WebView.this.getHorizontalScrollFactor();
//        }

        public void setMeasuredDimension(int measuredWidth, int measuredHeight) {
            WebView.this.setMeasuredDimension(measuredWidth, measuredHeight);
        }

        public void onScrollChanged(int l, int t, int oldl, int oldt) {
            WebView.this.onScrollChanged(l, t, oldl, oldt);
        }

        public int getHorizontalScrollbarHeight() {
            return WebView.this.getHorizontalScrollbarHeight();
        }

//        public void super_onDrawVerticalScrollBar(Canvas canvas, Drawable scrollBar, int l, int t, int r, int b) {
//             WebView.super.onDrawVerticalScrollBar(canvas, scrollBar, l, t, r, b);
//        }

        // ---- Access to (non-public) fields ----
        /** Raw setter for the scroll X value, without invoking onScrollChanged handlers etc. */
//        public void setScrollXRaw(int scrollX) {
//             WebView.this.mScrollX = scrollX;
//        }

        /** Raw setter for the scroll Y value, without invoking onScrollChanged handlers etc. */
//        public void setScrollYRaw(int scrollY) {
//             WebView.this.mScrollY = scrollY;
//        }
    }

    /**
     * Returns {@code true} if the draw GL functor can be invoked (see {@link #invokeDrawGlFunctor})
     * and {@code false} otherwise.
     */
    public boolean canInvokeDrawGlFunctor(View containerView) {
        return true;
    }

    /**
     * Invokes the draw GL functor. If waitForCompletion is {@code false} the functor
     * may be invoked asynchronously.
     *
     * @param nativeDrawGLFunctor the pointer to the native functor that implements
     *                            system/core/include/utils/Functor.h
     */
    public void invokeDrawGlFunctor(View containerView, long nativeDrawGLFunctor,
                                    boolean waitForCompletion) {
//        ViewRootImpl.invokeFunctor(nativeDrawGLFunctor, waitForCompletion);
        nInvokeFunctor(nativeDrawGLFunctor, waitForCompletion);
    }

    /**
     * Calls the function specified with the nativeDrawGLFunctor functor pointer. This
     * functionality is used by the WebView for calling into their renderer from the
     * framework display lists.
     *
     * @param canvas              a hardware accelerated canvas (see {@link Canvas#isHardwareAccelerated()})
     * @param nativeDrawGLFunctor the pointer to the native functor that implements
     *                            system/core/include/utils/Functor.h
     * @throws IllegalArgumentException if the canvas is not hardware accelerated
     */
    public void callDrawGlFunction(Canvas canvas, long nativeDrawGLFunctor) {
//        if (!(canvas instanceof DisplayListCanvas)) {
//            // Canvas#isHardwareAccelerated() is only true for subclasses of HardwareCanvas.
//            throw new IllegalArgumentException(canvas.getClass().getName()
//                    + " is not a DisplayList canvas");
//        }
//        ((DisplayListCanvas) canvas).drawGLFunctor2(nativeDrawGLFunctor, null);
    }

    /**
     * Calls the function specified with the nativeDrawGLFunctor functor pointer. This
     * functionality is used by the WebView for calling into their renderer from the
     * framework display lists.
     *
     * @param canvas              a hardware accelerated canvas (see {@link Canvas#isHardwareAccelerated()})
     * @param nativeDrawGLFunctor the pointer to the native functor that implements
     *                            system/core/include/utils/Functor.h
     * @param releasedRunnable    Called when this nativeDrawGLFunctor is no longer referenced by this
     *                            canvas, so is safe to be destroyed.
     * @throws IllegalArgumentException if the canvas is not hardware accelerated
     */
    public void callDrawGlFunction(@NonNull Canvas canvas, long nativeDrawGLFunctor,
                                   @Nullable Runnable releasedRunnable) {
//        if (!(canvas instanceof DisplayListCanvas)) {
//            // Canvas#isHardwareAccelerated() is only true for subclasses of HardwareCanvas.
//            throw new IllegalArgumentException(canvas.getClass().getName()
//                    + " is not a DisplayList canvas");
//        }
//        ((DisplayListCanvas) canvas).drawGLFunctor2(nativeDrawGLFunctor, releasedRunnable);
//        nCallDrawGLFunction(canvas.getNativeCanvasWrapper(), releasedRunnable,null);
    }

    /**
     * Detaches the draw GL functor.
     *
     * @param nativeDrawGLFunctor the pointer to the native functor that implements
     *                            system/core/include/utils/Functor.h
     */
    public void detachDrawGlFunctor(View containerView, long nativeDrawGLFunctor) {
//        ViewRootImpl viewRootImpl = containerView.getViewRootImpl();
//        if (nativeDrawGLFunctor != 0 && viewRootImpl != null) {
//            viewRootImpl.detachFunctor(nativeDrawGLFunctor);
//        }
    }

    /**
     * Returns the package id of the given {@code packageName}.
     */
    public int getPackageId(Resources resources, String packageName) {
//        SparseArray<String> packageIdentifiers =
//                resources.getAssets().getAssignedPackageIdentifiers();
//        for (int i = 0; i < packageIdentifiers.size(); i++) {
//            final String name = packageIdentifiers.valueAt(i);
//
//            if (packageName.equals(name)) {
//                return packageIdentifiers.keyAt(i);
//            }
//        }
//        throw new RuntimeException("Package not found: " + packageName);
        return 0;
    }

    private static native void nInvokeFunctor(long functor, boolean waitForCompletion);
}
