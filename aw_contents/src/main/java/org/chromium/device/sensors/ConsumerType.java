
// Copyright 2018 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by
//     java_cpp_enum.py
// From
//     ../../device/sensors/device_sensors_consts.h

package org.chromium.device.sensors;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    ConsumerType.MOTION, ConsumerType.ORIENTATION, ConsumerType.ORIENTATION_ABSOLUTE
})
@Retention(RetentionPolicy.SOURCE)
public @interface ConsumerType {
  int MOTION = 1 << 0;
  int ORIENTATION = 1 << 1;
  int ORIENTATION_ABSOLUTE = 1 << 2;
}
