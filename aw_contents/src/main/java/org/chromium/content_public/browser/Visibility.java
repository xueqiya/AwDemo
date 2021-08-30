
// Copyright 2021 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by
//     java_cpp_enum.py
// From
//     ../../content/public/browser/visibility.h

package org.chromium.content_public.browser;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    Visibility.HIDDEN, Visibility.OCCLUDED, Visibility.VISIBLE
})
@Retention(RetentionPolicy.SOURCE)
public @interface Visibility {
  /**
   * The view is not part of any window (e.g. a non-active tab) or is part of a window that is
   * minimized or hidden (Cmd+H).
   */
  int HIDDEN = 0;
  /**
   * The view is not visible on any screen despite not being HIDDEN. This can be because it is
   * covered by other windows and/or because it is outside the bounds of the screen.
   */
  int OCCLUDED = 1;
  /**
   * The view is visible on the screen.
   */
  int VISIBLE = 2;
}