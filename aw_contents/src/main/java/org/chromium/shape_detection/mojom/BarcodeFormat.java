// BarcodeFormat.java is auto generated by mojom_bindings_generator.py, do not edit


// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     services/shape_detection/public/mojom/barcodedetection.mojom
//

package org.chromium.shape_detection.mojom;

import androidx.annotation.IntDef;

public final class BarcodeFormat {
    private static final boolean IS_EXTENSIBLE = false;
    @IntDef({

        BarcodeFormat.AZTEC,
        BarcodeFormat.CODE_128,
        BarcodeFormat.CODE_39,
        BarcodeFormat.CODE_93,
        BarcodeFormat.CODABAR,
        BarcodeFormat.DATA_MATRIX,
        BarcodeFormat.EAN_13,
        BarcodeFormat.EAN_8,
        BarcodeFormat.ITF,
        BarcodeFormat.PDF417,
        BarcodeFormat.QR_CODE,
        BarcodeFormat.UNKNOWN,
        BarcodeFormat.UPC_A,
        BarcodeFormat.UPC_E})
    public @interface EnumType {}

    public static final int AZTEC = 0;
    public static final int CODE_128 = 1;
    public static final int CODE_39 = 2;
    public static final int CODE_93 = 3;
    public static final int CODABAR = 4;
    public static final int DATA_MATRIX = 5;
    public static final int EAN_13 = 6;
    public static final int EAN_8 = 7;
    public static final int ITF = 8;
    public static final int PDF417 = 9;
    public static final int QR_CODE = 10;
    public static final int UNKNOWN = 11;
    public static final int UPC_A = 12;
    public static final int UPC_E = 13;
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 13;

    public static boolean isKnownValue(int value) {
        return value >= 0 && value <= 13;
    }

    public static void validate(int value) {
        if (IS_EXTENSIBLE || isKnownValue(value)) return;
        throw new org.chromium.mojo.bindings.DeserializationException("Invalid enum value.");
    }

    public static int toKnownValue(int value) {
      return value;
    }

    private BarcodeFormat() {}
}