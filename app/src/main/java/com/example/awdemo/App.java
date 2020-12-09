// Copyright 2012 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package com.example.awdemo;

import org.chromium.base.CommandLine;
import org.chromium.base.annotations.SuppressFBWarnings;
import org.chromium.content.app.ContentApplication;

/**
 * The android_webview shell Application subclass.
 */
public class App extends ContentApplication {
    public App() {
        super(false /* mShouldInitializeApplicationStatusTracking */);
    }

    @SuppressFBWarnings("DMI_HARDCODED_ABSOLUTE_FILENAME")
    @Override
    public void initCommandLine() {
        if (!CommandLine.isInitialized()) {
            CommandLine.initFromFile("/data/local/tmp/android-webview-command-line");
        }
    }
}
