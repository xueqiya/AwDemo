// XFrameOptionsValue.java is auto generated by mojom_bindings_generator.py, do not edit


// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     services/network/public/mojom/x_frame_options.mojom
//

package org.chromium.network.mojom;

import androidx.annotation.IntDef;

public final class XFrameOptionsValue {
    private static final boolean IS_EXTENSIBLE = false;
    @IntDef({

        XFrameOptionsValue.NONE,
        XFrameOptionsValue.DENY,
        XFrameOptionsValue.SAME_ORIGIN,
        XFrameOptionsValue.ALLOW_ALL,
        XFrameOptionsValue.INVALID,
        XFrameOptionsValue.CONFLICT})
    public @interface EnumType {}

    public static final int NONE = 0;
    public static final int DENY = 1;
    public static final int SAME_ORIGIN = 2;
    public static final int ALLOW_ALL = 3;
    public static final int INVALID = 4;
    public static final int CONFLICT = 5;
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 5;

    public static boolean isKnownValue(int value) {
        return value >= 0 && value <= 5;
    }

    public static void validate(int value) {
        if (IS_EXTENSIBLE || isKnownValue(value)) return;
        throw new org.chromium.mojo.bindings.DeserializationException("Invalid enum value.");
    }

    public static int toKnownValue(int value) {
      return value;
    }

    private XFrameOptionsValue() {}
}