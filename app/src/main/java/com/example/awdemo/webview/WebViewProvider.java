package com.example.awdemo.webview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.os.Message;
import android.print.PrintDocumentAdapter;
import android.view.View;

import java.io.BufferedWriter;
import java.io.File;
import java.util.Map;

public interface WebViewProvider {
    public void init(WebView webView);

    public void onDraw(Canvas canvas);

    public void setHorizontalScrollbarOverlay(boolean overlay);

    public void setVerticalScrollbarOverlay(boolean overlay);

    public boolean overlayHorizontalScrollbar();

    public boolean overlayVerticalScrollbar();

    public int getVisibleTitleHeight();

    public SslCertificate getCertificate();

    public void setCertificate(SslCertificate certificate);

    public void savePassword(String host, String username, String password);

    public void setHttpAuthUsernamePassword(String host, String realm,
                                            String username, String password);

    public String[] getHttpAuthUsernamePassword(String host, String realm);

    public void destroy();

    public void setNetworkAvailable(boolean networkUp);

    public WebBackForwardList saveState(Bundle outState);

    public boolean savePicture(Bundle b, final File dest);

    public boolean restorePicture(Bundle b, File src);

    public WebBackForwardList restoreState(Bundle inState);

    public void loadUrl(String url, Map<String, String> additionalHttpHeaders);

    public void loadUrl(String url);

    public void postUrl(String url, byte[] postData);

    public void loadData(String data, String mimeType, String encoding);

    public void loadDataWithBaseURL(String baseUrl, String data,
                                    String mimeType, String encoding, String historyUrl);

    public void evaluateJavaScript(String script, ValueCallback<String> resultCallback);

    public void saveWebArchive(String filename);

    public void saveWebArchive(String basename, boolean autoname, ValueCallback<String> callback);

    public void stopLoading();

    public void reload();

    public boolean canGoBack();

    public void goBack();

    public boolean canGoForward();

    public void goForward();

    public boolean canGoBackOrForward(int steps);

    public void goBackOrForward(int steps);

    public boolean isPrivateBrowsingEnabled();

    public boolean pageUp(boolean top);

    public boolean pageDown(boolean bottom);

    public void clearView();

    public Picture capturePicture();

    public PrintDocumentAdapter createPrintDocumentAdapter(String documentName);

    public float getScale();

    public void setInitialScale(int scaleInPercent);

    public void invokeZoomPicker();

    public void requestFocusNodeHref(Message hrefMsg);

    public void requestImageRef(Message msg);

    public String getUrl();

    public String getOriginalUrl();

    public String getTitle();

    public Bitmap getFavicon();

    public String getTouchIconUrl();

    public int getProgress();

    public int getContentHeight();

    public int getContentWidth();

    public void pauseTimers();

    public void resumeTimers();

    public void onPause();

    public void onResume();

    public boolean isPaused();

    public void freeMemory();

    public void clearCache(boolean includeDiskFiles);

    public void clearFormData();

    public void clearHistory();

    public void clearSslPreferences();

    public WebBackForwardList copyBackForwardList();

    public void findNext(boolean forward);

    public int findAll(String find);

    public void findAllAsync(String find);

    public boolean showFindDialog(String text, boolean showIme);

    public void clearMatches();

    public void documentHasImages(Message response);

    public void setWebViewClient(WebViewClient client);

    public WebViewClient getWebViewClient();

    public void setWebChromeClient(WebChromeClient client);

    public WebChromeClient getWebChromeClient();

    public void addJavascriptInterface(Object obj, String interfaceName);

    public void removeJavascriptInterface(String interfaceName);

    public WebSettings getSettings();

    public void setMapTrackballToArrowKeys(boolean setMap);

    public void flingScroll(int vx, int vy);

    public View getZoomControls();

    public boolean canZoomIn();

    public boolean canZoomOut();

    public boolean zoomBy(float zoomFactor);

    public boolean zoomIn();

    public boolean zoomOut();

    public void dumpViewHierarchyWithProperties(BufferedWriter out, int level);

    public View findHierarchyView(String className, int hashCode);

    public void setRendererPriorityPolicy(int rendererRequestedPriority, boolean waivedWhenNotVisible);

    public int getRendererRequestedPriority();

    public boolean getRendererPriorityWaivedWhenNotVisible();

    ViewDelegate getViewDelegate();

    ScrollDelegate getScrollDelegate();

    interface ViewDelegate {
    }

    interface ScrollDelegate {
    }
}