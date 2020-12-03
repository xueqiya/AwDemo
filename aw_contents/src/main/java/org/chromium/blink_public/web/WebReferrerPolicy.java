
// Copyright 2018 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by
//     java_cpp_enum.py
// From
//     third_party/WebKit/public/platform/WebReferrerPolicy.h

package org.chromium.blink_public.web;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    WebReferrerPolicy.WEB_REFERRER_POLICY_ALWAYS, WebReferrerPolicy.WEB_REFERRER_POLICY_DEFAULT,
    WebReferrerPolicy.WEB_REFERRER_POLICY_NO_REFERRER_WHEN_DOWNGRADE,
    WebReferrerPolicy.WEB_REFERRER_POLICY_NEVER, WebReferrerPolicy.WEB_REFERRER_POLICY_ORIGIN,
    WebReferrerPolicy.WEB_REFERRER_POLICY_ORIGIN_WHEN_CROSS_ORIGIN,
    WebReferrerPolicy.WEB_REFERRER_POLICY_NO_REFERRER_WHEN_DOWNGRADE_ORIGIN_WHEN_CROSS_ORIGIN,
    WebReferrerPolicy.WEB_REFERRER_POLICY_SAME_ORIGIN,
    WebReferrerPolicy.WEB_REFERRER_POLICY_STRICT_ORIGIN, WebReferrerPolicy.WEB_REFERRER_POLICY_LAST
})
@Retention(RetentionPolicy.SOURCE)
public @interface WebReferrerPolicy {
  int WEB_REFERRER_POLICY_ALWAYS = 0;
  int WEB_REFERRER_POLICY_DEFAULT = 1;
  int WEB_REFERRER_POLICY_NO_REFERRER_WHEN_DOWNGRADE = 2;
  int WEB_REFERRER_POLICY_NEVER = 3;
  int WEB_REFERRER_POLICY_ORIGIN = 4;
  int WEB_REFERRER_POLICY_ORIGIN_WHEN_CROSS_ORIGIN = 5;
  /**
   * This policy corresponds to strict-origin-when-cross-origin. TODO(estark): rename to match the
   * spec.
   */
  int WEB_REFERRER_POLICY_NO_REFERRER_WHEN_DOWNGRADE_ORIGIN_WHEN_CROSS_ORIGIN = 6;
  int WEB_REFERRER_POLICY_SAME_ORIGIN = 7;
  int WEB_REFERRER_POLICY_STRICT_ORIGIN = 8;
  int WEB_REFERRER_POLICY_LAST = 8;
}
