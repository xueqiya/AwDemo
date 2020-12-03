
// Copyright 2018 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by
//     java_cpp_enum.py
// From
//     third_party/WebKit/public/platform/WebInputEvent.h

package org.chromium.blink_public.web;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    WebInputEventType.UNDEFINED, WebInputEventType.TYPE_FIRST, WebInputEventType.MOUSE_DOWN,
    WebInputEventType.MOUSE_TYPE_FIRST, WebInputEventType.MOUSE_UP, WebInputEventType.MOUSE_MOVE,
    WebInputEventType.MOUSE_ENTER, WebInputEventType.MOUSE_LEAVE, WebInputEventType.CONTEXT_MENU,
    WebInputEventType.MOUSE_TYPE_LAST, WebInputEventType.MOUSE_WHEEL,
    WebInputEventType.RAW_KEY_DOWN, WebInputEventType.KEYBOARD_TYPE_FIRST,
    WebInputEventType.KEY_DOWN, WebInputEventType.KEY_UP, WebInputEventType.CHAR,
    WebInputEventType.KEYBOARD_TYPE_LAST, WebInputEventType.GESTURE_SCROLL_BEGIN,
    WebInputEventType.GESTURE_TYPE_FIRST, WebInputEventType.GESTURE_SCROLL_END,
    WebInputEventType.GESTURE_SCROLL_UPDATE, WebInputEventType.GESTURE_FLING_START,
    WebInputEventType.GESTURE_FLING_CANCEL, WebInputEventType.GESTURE_PINCH_BEGIN,
    WebInputEventType.GESTURE_PINCH_TYPE_FIRST, WebInputEventType.GESTURE_PINCH_END,
    WebInputEventType.GESTURE_PINCH_UPDATE, WebInputEventType.GESTURE_PINCH_TYPE_LAST,
    WebInputEventType.GESTURE_TAP_DOWN, WebInputEventType.GESTURE_SHOW_PRESS,
    WebInputEventType.GESTURE_TAP, WebInputEventType.GESTURE_TAP_CANCEL,
    WebInputEventType.GESTURE_LONG_PRESS, WebInputEventType.GESTURE_LONG_TAP,
    WebInputEventType.GESTURE_TWO_FINGER_TAP, WebInputEventType.GESTURE_TAP_UNCONFIRMED,
    WebInputEventType.GESTURE_DOUBLE_TAP, WebInputEventType.GESTURE_TYPE_LAST,
    WebInputEventType.TOUCH_START, WebInputEventType.TOUCH_TYPE_FIRST, WebInputEventType.TOUCH_MOVE,
    WebInputEventType.TOUCH_END, WebInputEventType.TOUCH_CANCEL,
    WebInputEventType.TOUCH_SCROLL_STARTED, WebInputEventType.TOUCH_TYPE_LAST,
    WebInputEventType.POINTER_DOWN, WebInputEventType.POINTER_TYPE_FIRST,
    WebInputEventType.POINTER_UP, WebInputEventType.POINTER_MOVE, WebInputEventType.POINTER_CANCEL,
    WebInputEventType.POINTER_TYPE_LAST, WebInputEventType.TYPE_LAST
})
@Retention(RetentionPolicy.SOURCE)
public @interface WebInputEventType {
  int UNDEFINED = -1;
  int TYPE_FIRST = -1;
  /**
   * WebMouseEvent
   */
  int MOUSE_DOWN = 0;
  int MOUSE_TYPE_FIRST = 0;
  int MOUSE_UP = 1;
  int MOUSE_MOVE = 2;
  int MOUSE_ENTER = 3;
  int MOUSE_LEAVE = 4;
  int CONTEXT_MENU = 5;
  int MOUSE_TYPE_LAST = 5;
  /**
   * WebMouseWheelEvent
   */
  int MOUSE_WHEEL = 6;
  /**
   * WebKeyboardEvent
   */
  int RAW_KEY_DOWN = 7;
  int KEYBOARD_TYPE_FIRST = 7;
  /**
   * KeyDown is a single event combining RawKeyDown and Char.  If KeyDown is sent for a given
   * keystroke, those two other events will not be sent. Platforms tend to prefer sending in one
   * format (Android uses KeyDown, Windows uses RawKeyDown+Char, for example), but this is a weakly
   * held property as tools like WebDriver/DevTools might still send the other format.
   */
  int KEY_DOWN = 8;
  int KEY_UP = 9;
  int CHAR = 10;
  int KEYBOARD_TYPE_LAST = 10;
  /**
   * WebGestureEvent - input interpreted semi-semantically, most commonly from touchscreen but also
   * used for touchpad, mousewheel, and gamepad scrolling.
   */
  int GESTURE_SCROLL_BEGIN = 11;
  int GESTURE_TYPE_FIRST = 11;
  int GESTURE_SCROLL_END = 12;
  int GESTURE_SCROLL_UPDATE = 13;
  /**
   * Fling is a high-velocity and quickly released finger movement. FlingStart is sent once and
   * kicks off a scroll animation.
   */
  int GESTURE_FLING_START = 14;
  int GESTURE_FLING_CANCEL = 15;
  /**
   * Pinch is two fingers moving closer or farther apart.
   */
  int GESTURE_PINCH_BEGIN = 16;
  int GESTURE_PINCH_TYPE_FIRST = 16;
  int GESTURE_PINCH_END = 17;
  int GESTURE_PINCH_UPDATE = 18;
  int GESTURE_PINCH_TYPE_LAST = 18;
  /**
   * The following types are variations and subevents of single-taps. Sent the moment the user's
   * finger hits the screen.
   */
  int GESTURE_TAP_DOWN = 19;
  /**
   * Sent a short interval later, after it seems the finger is staying in place.  It's used to
   * activate the link highlight ("show the press").
   */
  int GESTURE_SHOW_PRESS = 20;
  /**
   * Sent on finger lift for a simple, static, quick finger tap.  This is the "main" event which
   * maps to a synthetic mouse click event.
   */
  int GESTURE_TAP = 21;
  /**
   * Sent when a GestureTapDown didn't turn into any variation of GestureTap (likely it turned into
   * a scroll instead).
   */
  int GESTURE_TAP_CANCEL = 22;
  /**
   * Sent as soon as the long-press timeout fires, while the finger is still down.
   */
  int GESTURE_LONG_PRESS = 23;
  /**
   * Sent when the finger is lifted following a GestureLongPress.
   */
  int GESTURE_LONG_TAP = 24;
  /**
   * Sent on finger lift when two fingers tapped at the same time without moving.
   */
  int GESTURE_TWO_FINGER_TAP = 25;
  /**
   * A rare event sent in place of GestureTap on desktop pages viewed on an Android phone.  This tap
   * could not yet be resolved into a GestureTap because it may still turn into a GestureDoubleTap.
   */
  int GESTURE_TAP_UNCONFIRMED = 26;
  /**
   * Double-tap is two single-taps spread apart in time, like a double-click. This event is only
   * sent on desktop pages viewed on an Android phone, and is always preceded by
   * GestureTapUnconfirmed.  It's an instruction to Blink to perform a PageScaleAnimation zoom onto
   * the double-tapped content.  (It's treated differently from GestureTap with tapCount=2, which
   * can also happen.)
   */
  int GESTURE_DOUBLE_TAP = 27;
  int GESTURE_TYPE_LAST = 27;
  /**
   * WebTouchEvent - raw touch pointers not yet classified into gestures.
   */
  int TOUCH_START = 28;
  int TOUCH_TYPE_FIRST = 28;
  int TOUCH_MOVE = 29;
  int TOUCH_END = 30;
  int TOUCH_CANCEL = 31;
  int TOUCH_SCROLL_STARTED = 32;
  int TOUCH_TYPE_LAST = 32;
  /**
   * WebPointerEvent: work in progress
   */
  int POINTER_DOWN = 33;
  int POINTER_TYPE_FIRST = 33;
  int POINTER_UP = 34;
  int POINTER_MOVE = 35;
  int POINTER_CANCEL = 36;
  int POINTER_TYPE_LAST = 36;
  int TYPE_LAST = 32;
}
