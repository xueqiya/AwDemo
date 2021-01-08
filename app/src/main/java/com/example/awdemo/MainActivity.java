package com.example.awdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.awdemo.webview.ContainerView;
import com.example.awdemo.webview.ContentsClient;

import org.chromium.android_webview.AwBrowserContext;
import org.chromium.android_webview.AwBrowserProcess;
import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwContentsClient;
import org.chromium.android_webview.AwDevToolsServer;
import org.chromium.android_webview.AwGeolocationPermissions;
import org.chromium.android_webview.AwSettings;
import org.chromium.android_webview.JsResultReceiver;
import org.chromium.android_webview.shell.AwShellResourceProvider;
import org.chromium.android_webview.shell.AwShellSwitches;
import org.chromium.base.CommandLine;
import org.chromium.base.Log;
import org.chromium.base.TraceEvent;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ContentUrlConstants;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "AwShellActivity";
    private static final String PREFERENCES_NAME = "AwShellPrefs";
    private static final String INITIAL_URL = ContentUrlConstants.ABOUT_BLANK_DISPLAY_URL;
    private AwBrowserContext mBrowserContext;
    private AwDevToolsServer mDevToolsServer;
    private ContainerView mContainerView;
    private WebContents mWebContents;
    private NavigationController mNavigationController;
    private EditText mUrlTextView;
    private ImageButton mPrevButton;
    private ImageButton mNextButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AwShellResourceProvider.registerResources(this);

        AwBrowserProcess.loadLibrary(null);

        if (CommandLine.getInstance().hasSwitch(AwShellSwitches.ENABLE_ATRACE)) {
            Log.e(TAG, "Enabling Android trace.");
            TraceEvent.setATraceEnabled(true);
        }

        setContentView(R.layout.activity_main);

        mContainerView = createAwTestContainerView();

        mWebContents = mContainerView.getWebContents();
        mNavigationController = mWebContents.getNavigationController();
        LinearLayout contentContainer = (LinearLayout) findViewById(org.chromium.android_webview.R.id.content_container);
        mContainerView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
        contentContainer.addView(mContainerView);
        mContainerView.requestFocus();

        initializeUrlField();
        initializeNavigationButtons();

        String startupUrl = getUrlFromIntent(getIntent());
        if (TextUtils.isEmpty(startupUrl)) {
            startupUrl = INITIAL_URL;
        }

        mContainerView.getAwContents().loadUrl(startupUrl);
        AwContents.setShouldDownloadFavicons();
        mUrlTextView.setText(startupUrl);
    }

    @Override
    public void onDestroy() {
        if (mDevToolsServer != null) {
            mDevToolsServer.destroy();
            mDevToolsServer = null;
        }
        super.onDestroy();
    }

    private ContainerView createAwTestContainerView() {
        AwBrowserProcess.start();
        ContainerView testContainerView = new ContainerView(this, true);
        AwContentsClient awContentsClient = new ContentsClient() {
            private View mCustomView;

            @Override
            public void handleJsConfirm(String url, String message, JsResultReceiver receiver) {
                String title = "From ";
                try {
                    URL javaUrl = new URL(url);
                    title += javaUrl.getProtocol() + "://" + javaUrl.getHost();
                    if (javaUrl.getPort() != -1) {
                        title += ":" + javaUrl.getPort();
                    }
                } catch (MalformedURLException e) {
                    title += url;
                }

                new AlertDialog.Builder(testContainerView.getContext())
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        receiver.confirm();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        receiver.cancel();
                                    }
                                })
                        .create()
                        .show();
            }

            @Override
            public void onPageStarted(String url) {
                if (mUrlTextView != null) {
                    mUrlTextView.setText(url);
                }
            }

            @Override
            public void onShowCustomView(View view, AwContentsClient.CustomViewCallback callback) {
                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);

                getWindow().addContentView(view,
                        new FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                Gravity.CENTER));
                mCustomView = view;
            }

            @Override
            public void onHideCustomView() {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                FrameLayout decorView = (FrameLayout) getWindow().getDecorView();
                decorView.removeView(mCustomView);
                mCustomView = null;
            }

            @Override
            public boolean shouldOverrideKeyEvent(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(
                    String origin, AwGeolocationPermissions.Callback callback) {
                callback.invoke(origin, false, false);
            }
        };

        SharedPreferences sharedPreferences =
                getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        if (mBrowserContext == null) {
            mBrowserContext = new AwBrowserContext(
                    sharedPreferences, AwBrowserContext.getDefault().getNativePointer(), true);
        }
        final AwSettings awSettings =
                new AwSettings(this /* context */, false /* isAccessFromFileURLsGrantedByDefault */,
                        false /* supportsLegacyQuirks */, false /* allowEmptyDocumentPersistence */,
                        true /* allowGeolocationOnInsecureOrigins */,
                        false /* doNotUpdateSelectionOnMutatingSelectionRange */);
        // Required for WebGL conformance tests.
        awSettings.setMediaPlaybackRequiresUserGesture(false);
        // Allow zoom and fit contents to screen
        awSettings.setBuiltInZoomControls(true);
        awSettings.setDisplayZoomControls(false);
        awSettings.setUseWideViewPort(true);
        awSettings.setLoadWithOverviewMode(true);
        awSettings.setLayoutAlgorithm(AwSettings.LAYOUT_ALGORITHM_TEXT_AUTOSIZING);

        testContainerView.initialize(new AwContents(mBrowserContext, testContainerView,
                testContainerView.getContext(), testContainerView.getInternalAccessDelegate(),
                testContainerView.getNativeDrawFunctorFactory(), awContentsClient, awSettings));
        testContainerView.getAwContents().getSettings().setJavaScriptEnabled(true);
        if (mDevToolsServer == null) {
            mDevToolsServer = new AwDevToolsServer();
            mDevToolsServer.setRemoteDebuggingEnabled(true);
        }
        return testContainerView;
    }

    private static String getUrlFromIntent(Intent intent) {
        return intent != null ? intent.getDataString() : null;
    }

    private void setKeyboardVisibilityForUrl(boolean visible) {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        if (visible) {
            imm.showSoftInput(mUrlTextView, InputMethodManager.SHOW_IMPLICIT);
        } else {
            imm.hideSoftInputFromWindow(mUrlTextView.getWindowToken(), 0);
        }
    }

    private void initializeUrlField() {
        mUrlTextView = (EditText) findViewById(org.chromium.android_webview.R.id.url);
        mUrlTextView.setOnEditorActionListener((v, actionId, event) -> {
            if ((actionId != EditorInfo.IME_ACTION_GO) && (event == null
                    || event.getKeyCode() != KeyEvent.KEYCODE_ENTER
                    || event.getAction() != KeyEvent.ACTION_DOWN)) {
                return false;
            }

            String url = mUrlTextView.getText().toString();
            try {
                URI uri = new URI(url);
                if (uri.getScheme() == null) {
                    url = "http://" + uri.toString();
                } else {
                    url = uri.toString();
                }
            } catch (URISyntaxException e) {
                // Ignore syntax errors.
            }
            mContainerView.getAwContents().loadUrl(url);
            mUrlTextView.clearFocus();
            setKeyboardVisibilityForUrl(false);
            mContainerView.requestFocus();
            return true;
        });
        mUrlTextView.setOnFocusChangeListener((v, hasFocus) -> {
            setKeyboardVisibilityForUrl(hasFocus);
            mNextButton.setVisibility(hasFocus ? View.GONE : View.VISIBLE);
            mPrevButton.setVisibility(hasFocus ? View.GONE : View.VISIBLE);
            if (!hasFocus) {
                mUrlTextView.setText(mWebContents.getVisibleUrl());
            }
        });
    }

    private void initializeNavigationButtons() {
        mPrevButton = (ImageButton) findViewById(org.chromium.android_webview.R.id.prev);
        mPrevButton.setOnClickListener(v -> {
            if (mNavigationController.canGoBack()) {
                mNavigationController.goBack();
            }
        });

        mNextButton = (ImageButton) findViewById(org.chromium.android_webview.R.id.next);
        mNextButton.setOnClickListener(v -> {
            if (mNavigationController.canGoForward()) {
                mNavigationController.goForward();
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mNavigationController.canGoBack()) {
                mNavigationController.goBack();
                return true;
            }
        }

        return super.onKeyUp(keyCode, event);
    }
}
