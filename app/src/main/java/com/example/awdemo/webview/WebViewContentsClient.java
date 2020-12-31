package com.example.awdemo.webview;

import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.http.SslError;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;

import org.chromium.android_webview.AwConsoleMessage;
import org.chromium.android_webview.AwContentsClient;
import org.chromium.android_webview.AwContentsClientBridge;
import org.chromium.android_webview.AwGeolocationPermissions;
import org.chromium.android_webview.AwHttpAuthHandler;
import org.chromium.android_webview.AwRenderProcess;
import org.chromium.android_webview.AwRenderProcessGoneDetail;
import org.chromium.android_webview.AwWebResourceResponse;
import org.chromium.android_webview.JsPromptResultReceiver;
import org.chromium.android_webview.JsResultReceiver;
import org.chromium.android_webview.permission.AwPermissionRequest;
import org.chromium.android_webview.safe_browsing.AwSafeBrowsingResponse;
import org.chromium.base.Callback;

import java.security.Principal;

class WebViewContentsClient extends AwContentsClient {
    @Override
    public boolean hasWebViewClient() {
        return false;
    }

    @Override
    public void getVisitedHistory(Callback<String[]> callback) {

    }

    @Override
    public void doUpdateVisitedHistory(String url, boolean isReload) {

    }

    @Override
    public void onProgressChanged(int progress) {

    }

    @Override
    public AwWebResourceResponse shouldInterceptRequest(AwWebResourceRequest request) {
        return null;
    }

    @Override
    public boolean shouldOverrideKeyEvent(KeyEvent event) {
        return false;
    }

    @Override
    public boolean shouldOverrideUrlLoading(AwWebResourceRequest request) {
        return false;
    }

    @Override
    public void onLoadResource(String url) {

    }

    @Override
    public void onUnhandledKeyEvent(KeyEvent event) {

    }

    @Override
    public boolean onConsoleMessage(AwConsoleMessage consoleMessage) {
        return false;
    }

    @Override
    public void onReceivedHttpAuthRequest(AwHttpAuthHandler handler, String host, String realm) {

    }

    @Override
    public void onReceivedSslError(Callback<Boolean> callback, SslError error) {

    }

    @Override
    public void onReceivedClientCertRequest(AwContentsClientBridge.ClientCertificateRequestCallback callback, String[] keyTypes, Principal[] principals, String host, int port) {

    }

    @Override
    public void onReceivedLoginRequest(String realm, String account, String args) {

    }

    @Override
    public void onFormResubmission(Message dontResend, Message resend) {

    }

    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {

    }

    @Override
    public void showFileChooser(Callback<String[]> uploadFilePathsCallback, FileChooserParamsImpl fileChooserParams) {

    }

    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, AwGeolocationPermissions.Callback callback) {

    }

    @Override
    public void onGeolocationPermissionsHidePrompt() {

    }

    @Override
    public void onPermissionRequest(AwPermissionRequest awPermissionRequest) {

    }

    @Override
    public void onPermissionRequestCanceled(AwPermissionRequest awPermissionRequest) {

    }

    @Override
    public void onScaleChangedScaled(float oldScale, float newScale) {

    }

    @Override
    protected void handleJsAlert(String url, String message, JsResultReceiver receiver) {

    }

    @Override
    protected void handleJsBeforeUnload(String url, String message, JsResultReceiver receiver) {

    }

    @Override
    protected void handleJsConfirm(String url, String message, JsResultReceiver receiver) {

    }

    @Override
    protected void handleJsPrompt(String url, String message, String defaultValue, JsPromptResultReceiver receiver) {

    }

    @Override
    protected boolean onCreateWindow(boolean isDialog, boolean isUserGesture) {
        return false;
    }

    @Override
    protected void onCloseWindow() {

    }

    @Override
    public void onReceivedTouchIconUrl(String url, boolean precomposed) {

    }

    @Override
    public void onReceivedIcon(Bitmap bitmap) {

    }

    @Override
    public void onReceivedTitle(String title) {

    }

    @Override
    protected void onRequestFocus() {

    }

    @Override
    protected View getVideoLoadingProgressView() {
        return null;
    }

    @Override
    public void onPageStarted(String url) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageCommitVisible(String url) {

    }

    @Override
    protected void onReceivedError(int errorCode, String description, String failingUrl) {

    }

    @Override
    protected void onReceivedError2(AwWebResourceRequest request, AwWebResourceError error) {

    }

    @Override
    protected void onSafeBrowsingHit(AwWebResourceRequest request, int threatType, Callback<AwSafeBrowsingResponse> callback) {

    }

    @Override
    public void onReceivedHttpError(AwWebResourceRequest request, AwWebResourceResponse response) {

    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {

    }

    @Override
    public void onHideCustomView() {

    }

    @Override
    public Bitmap getDefaultVideoPoster() {
        return null;
    }

    @Override
    public void onFindResultReceived(int activeMatchOrdinal, int numberOfMatches, boolean isDoneCounting) {

    }

    @Override
    public void onNewPicture(Picture picture) {

    }

    @Override
    public void onRendererUnresponsive(AwRenderProcess renderProcess) {

    }

    @Override
    public void onRendererResponsive(AwRenderProcess renderProcess) {

    }

    @Override
    public boolean onRenderProcessGone(AwRenderProcessGoneDetail detail) {
        return false;
    }
}
