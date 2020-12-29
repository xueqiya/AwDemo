// SerialReceiveError.java is auto generated by mojom_bindings_generator.py, do not edit


// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     services/device/public/mojom/serial.mojom
//

package org.chromium.device.mojom;

public final class SerialReceiveError {
    private static final boolean IS_EXTENSIBLE = false;

    public static final int NONE = 0;
    public static final int DISCONNECTED = 1; // NONE + 1
    public static final int DEVICE_LOST = 2; // DISCONNECTED + 1
    public static final int BREAK = 3; // DEVICE_LOST + 1
    public static final int FRAME_ERROR = 4; // BREAK + 1
    public static final int OVERRUN = 5; // FRAME_ERROR + 1
    public static final int BUFFER_OVERFLOW = 6; // OVERRUN + 1
    public static final int PARITY_ERROR = 7; // BUFFER_OVERFLOW + 1
    public static final int SYSTEM_ERROR = 8; // PARITY_ERROR + 1

    public static boolean isKnownValue(int value) {
        return value >= 0 && value <= 8;
    }

    public static void validate(int value) {
        if (IS_EXTENSIBLE || isKnownValue(value)) return;
        throw new org.chromium.mojo.bindings.DeserializationException("Invalid enum value.");
    }

    private SerialReceiveError() {}
}