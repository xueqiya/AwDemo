package com.example.awdemo.webview;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.net.http.SslCertificate;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.print.PrintDocumentAdapter;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import org.chromium.android_webview.AwBrowserContext;
import org.chromium.android_webview.AwBrowserProcess;
import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwContentsClient;
import org.chromium.android_webview.AwSettings;
import org.chromium.android_webview.gfx.AwDrawFnImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

class WebViewChromium implements WebViewProvider {
    private AwContents awContents;
    private WebView webView;
    private WebView.PrivateAccess privateAccess;

    public WebViewChromium(WebView webView, WebView.PrivateAccess privateAccess) {
        this.webView = webView;
        this.privateAccess = privateAccess;
    }

    @Override
    public void init() {
        AwBrowserProcess.start();
        AwBrowserContext awBrowserContext = AwBrowserContext.getDefault();
        Context context = webView.getContext();
        InternalAccessAdapter internalAccessAdapter = new InternalAccessAdapter();
        WebViewNativeDrawFunctorFactory webViewNativeDrawFunctorFactory = new WebViewNativeDrawFunctorFactory();
        AwContentsClient awContentsClient = new NullContentsClient();
        AwSettings awSettings = new AwSettings(context, false, false, false, true, false);
        awContents = new AwContents(awBrowserContext, webView, context,
                internalAccessAdapter, webViewNativeDrawFunctorFactory, awContentsClient, awSettings);
    }

    @Override
    public void onDraw(Canvas canvas) {
        awContents.onDraw(canvas);
    }

    @Override
    public void setHorizontalScrollbarOverlay(boolean overlay) {

    }

    @Override
    public void setVerticalScrollbarOverlay(boolean overlay) {

    }

    @Override
    public boolean overlayHorizontalScrollbar() {
        return false;
    }

    @Override
    public boolean overlayVerticalScrollbar() {
        return false;
    }

    @Override
    public int getVisibleTitleHeight() {
        return 0;
    }

    @Override
    public SslCertificate getCertificate() {
        return null;
    }

    @Override
    public void setCertificate(SslCertificate certificate) {

    }

    @Override
    public void savePassword(String host, String username, String password) {

    }

    @Override
    public void setHttpAuthUsernamePassword(String host, String realm, String username, String password) {

    }

    @Override
    public String[] getHttpAuthUsernamePassword(String host, String realm) {
        return new String[0];
    }

    @Override
    public void destroy() {

    }

    @Override
    public void setNetworkAvailable(boolean networkUp) {

    }

    @Override
    public WebBackForwardList saveState(Bundle outState) {
        return null;
    }

    @Override
    public boolean savePicture(Bundle b, File dest) {
        return false;
    }

    @Override
    public boolean restorePicture(Bundle b, File src) {
        return false;
    }

    @Override
    public WebBackForwardList restoreState(Bundle inState) {
        return null;
    }

    @Override
    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {

    }

    @Override
    public void loadUrl(String url) {

    }

    @Override
    public void postUrl(String url, byte[] postData) {

    }

    @Override
    public void loadData(String data, String mimeType, String encoding) {

    }

    @Override
    public void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl) {

    }

    @Override
    public void evaluateJavaScript(String script, ValueCallback<String> resultCallback) {

    }

    @Override
    public void saveWebArchive(String filename) {

    }

    @Override
    public void saveWebArchive(String basename, boolean autoname, ValueCallback<String> callback) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void reload() {

    }

    @Override
    public boolean canGoBack() {
        return false;
    }

    @Override
    public void goBack() {

    }

    @Override
    public boolean canGoForward() {
        return false;
    }

    @Override
    public void goForward() {

    }

    @Override
    public boolean canGoBackOrForward(int steps) {
        return false;
    }

    @Override
    public void goBackOrForward(int steps) {

    }

    @Override
    public boolean isPrivateBrowsingEnabled() {
        return false;
    }

    @Override
    public boolean pageUp(boolean top) {
        return false;
    }

    @Override
    public boolean pageDown(boolean bottom) {
        return false;
    }

    @Override
    public void clearView() {

    }

    @Override
    public Picture capturePicture() {
        return null;
    }

    @Override
    public PrintDocumentAdapter createPrintDocumentAdapter(String documentName) {
        return null;
    }

    @Override
    public float getScale() {
        return 0;
    }

    @Override
    public void setInitialScale(int scaleInPercent) {

    }

    @Override
    public void invokeZoomPicker() {

    }

    @Override
    public void requestFocusNodeHref(Message hrefMsg) {

    }

    @Override
    public void requestImageRef(Message msg) {

    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public String getOriginalUrl() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public Bitmap getFavicon() {
        return null;
    }

    @Override
    public String getTouchIconUrl() {
        return null;
    }

    @Override
    public int getProgress() {
        return 0;
    }

    @Override
    public int getContentHeight() {
        return 0;
    }

    @Override
    public int getContentWidth() {
        return 0;
    }

    @Override
    public void pauseTimers() {

    }

    @Override
    public void resumeTimers() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public boolean isPaused() {
        return false;
    }

    @Override
    public void freeMemory() {

    }

    @Override
    public void clearCache(boolean includeDiskFiles) {

    }

    @Override
    public void clearFormData() {

    }

    @Override
    public void clearHistory() {

    }

    @Override
    public void clearSslPreferences() {

    }

    @Override
    public WebBackForwardList copyBackForwardList() {
        return null;
    }

    @Override
    public void findNext(boolean forward) {

    }

    @Override
    public int findAll(String find) {
        return 0;
    }

    @Override
    public void findAllAsync(String find) {

    }

    @Override
    public boolean showFindDialog(String text, boolean showIme) {
        return false;
    }

    @Override
    public void clearMatches() {

    }

    @Override
    public void documentHasImages(Message response) {

    }

    @Override
    public void setWebViewClient(WebViewClient client) {

    }

    @Override
    public WebViewClient getWebViewClient() {
        return null;
    }

    @Override
    public void setWebChromeClient(WebChromeClient client) {

    }

    @Override
    public WebChromeClient getWebChromeClient() {
        return null;
    }

    @Override
    public void addJavascriptInterface(Object obj, String interfaceName) {

    }

    @Override
    public void removeJavascriptInterface(String interfaceName) {

    }

    @Override
    public WebSettings getSettings() {
        return null;
    }

    @Override
    public void setMapTrackballToArrowKeys(boolean setMap) {

    }

    @Override
    public void flingScroll(int vx, int vy) {

    }

    @Override
    public View getZoomControls() {
        return null;
    }

    @Override
    public boolean canZoomIn() {
        return false;
    }

    @Override
    public boolean canZoomOut() {
        return false;
    }

    @Override
    public boolean zoomBy(float zoomFactor) {
        return false;
    }

    @Override
    public boolean zoomIn() {
        return false;
    }

    @Override
    public boolean zoomOut() {
        return false;
    }

    @Override
    public void dumpViewHierarchyWithProperties(BufferedWriter out, int level) {

    }

    @Override
    public View findHierarchyView(String className, int hashCode) {
        return null;
    }

    @Override
    public void setRendererPriorityPolicy(int rendererRequestedPriority, boolean waivedWhenNotVisible) {

    }

    @Override
    public int getRendererRequestedPriority() {
        return 0;
    }

    @Override
    public boolean getRendererPriorityWaivedWhenNotVisible() {
        return false;
    }

    @Override
    public ViewDelegate getViewDelegate() {
        return null;
    }

    @Override
    public ScrollDelegate getScrollDelegate() {
        return null;
    }


    // AwContents.InternalAccessDelegate implementation --------------------------------------
    private class InternalAccessAdapter implements AwContents.InternalAccessDelegate {
        @Override
        public boolean super_onKeyUp(int arg0, KeyEvent arg1) {
            // Intentional no-op
            return false;
        }

        @Override
        public boolean super_dispatchKeyEvent(KeyEvent event) {
            return privateAccess.super_dispatchKeyEvent(event);
        }

        @Override
        public boolean super_onGenericMotionEvent(MotionEvent arg0) {
            return privateAccess.super_onGenericMotionEvent(arg0);
        }

        @Override
        public void super_onConfigurationChanged(Configuration arg0) {
            // Intentional no-op
        }

        @Override
        public int super_getScrollBarStyle() {
            return privateAccess.super_getScrollBarStyle();
        }

        @Override
        public void super_startActivityForResult(Intent intent, int requestCode) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                GlueApiHelperForN.super_startActivityForResult(
//                        privateAccess, intent, requestCode);
            } else {
                try {
                    Method startActivityForResultMethod =
                            View.class.getMethod("startActivityForResult", Intent.class, int.class);
                    startActivityForResultMethod.invoke(webView, intent, requestCode);
                } catch (Exception e) {
                    throw new RuntimeException("Invalid reflection", e);
                }
            }
        }

        @Override
        public void onScrollChanged(int l, int t, int oldl, int oldt) {
            // Intentional no-op.
            // Chromium calls this directly to trigger accessibility events. That isn't needed
            // for WebView since super_scrollTo invokes onScrollChanged for us.
        }

        @Override
        public void overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
                                 int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
            privateAccess.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                    scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
        }

        @Override
        public void super_scrollTo(int scrollX, int scrollY) {
            privateAccess.super_scrollTo(scrollX, scrollY);
        }

        @Override
        public void setMeasuredDimension(int measuredWidth, int measuredHeight) {
            privateAccess.setMeasuredDimension(measuredWidth, measuredHeight);
        }

        // @Override
        public boolean super_onHoverEvent(MotionEvent event) {
            return privateAccess.super_onHoverEvent(event);
        }
    }

    // AwContents.NativeDrawFunctorFactory implementation ----------------------------------
    private class WebViewNativeDrawFunctorFactory implements AwContents.NativeDrawFunctorFactory {
        @Override
        public AwContents.NativeDrawGLFunctor createGLFunctor(long context) {
            return new DrawGLFunctor(context, webView);
        }

        @Override
        public AwDrawFnImpl.DrawFnAccess getDrawFnAccess() {
//            if (BuildInfo.isAtLeastQ()) {
//                return mFactory.getWebViewDelegate();
//            }
            return null;
        }
    }
}
