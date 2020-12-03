
// Copyright 2018 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by
//     java_cpp_enum.py
// From
//     base/memory/memory_pressure_listener.h

package org.chromium.base;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    MemoryPressureLevel.NONE, MemoryPressureLevel.MODERATE, MemoryPressureLevel.CRITICAL
})
@Retention(RetentionPolicy.SOURCE)
public @interface MemoryPressureLevel {
  /**
   * No problems, there is enough memory to use. This event is not sent via callback, but the enum
   * is used in other places to find out the current state of the system.
   */
  int NONE = 0;
  /**
   * Modules are advised to free buffers that are cheap to re-allocate and not immediately needed.
   */
  int MODERATE = 1;
  /**
   * At this level, modules are advised to free all possible memory.  The alternative is to be
   * killed by the system, which means all memory will have to be re-created, plus the cost of a
   * cold start.
   */
  int CRITICAL = 2;
}
