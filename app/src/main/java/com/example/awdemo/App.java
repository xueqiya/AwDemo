package com.example.awdemo;

import android.app.Application;
import android.content.Context;

import org.chromium.android_webview.AwLocaleConfig;
import org.chromium.base.CommandLine;
import org.chromium.base.ContextUtils;
import org.chromium.base.PathUtils;
import org.chromium.ui.base.ResourceBundle;

public class App extends Application {

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        ContextUtils.initApplicationContext(this);
        PathUtils.setPrivateDataDirectorySuffix("webview", "WebView");
        CommandLine.initFromFile("/data/local/tmp/android-webview-command-line");
        ResourceBundle.setAvailablePakLocales(
                new String[] {}, AwLocaleConfig.getWebViewSupportedPakLocales());
    }
}