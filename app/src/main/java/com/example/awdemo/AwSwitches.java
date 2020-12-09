// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package com.example.awdemo;

/**
 * Contains all of the command line switches that are specific to the test shell of
 * the android_webview glue layer.
 */
public abstract class AwSwitches {
    // Enables Android systrace path for Chrome traces.
    public static final String ENABLE_ATRACE = "enable-atrace";

    // Prevent instantiation.
    private AwSwitches() {
    }
}

