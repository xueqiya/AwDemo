
// Copyright 2021 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by
//     java_cpp_enum.py
// From
//     ../../ui/base/ime/text_input_type.h

package org.chromium.ui.base.ime;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    TextInputType.NONE, TextInputType.TEXT, TextInputType.PASSWORD, TextInputType.SEARCH,
    TextInputType.EMAIL, TextInputType.NUMBER, TextInputType.TELEPHONE, TextInputType.URL,
    TextInputType.DATE, TextInputType.DATE_TIME, TextInputType.DATE_TIME_LOCAL, TextInputType.MONTH,
    TextInputType.TIME, TextInputType.WEEK, TextInputType.TEXT_AREA, TextInputType.CONTENT_EDITABLE,
    TextInputType.DATE_TIME_FIELD, TextInputType.NULL, TextInputType.MAX
})
@Retention(RetentionPolicy.SOURCE)
public @interface TextInputType {
  /**
   * Input caret is not in an editable node, no input method shall be used.
   */
  int NONE = 0;
  /**
   * Input caret is in a normal editable node, any input method can be used.
   */
  int TEXT = 1;
  /**
   * Input caret is in a password box, an input method may be used only if it's suitable for
   * password input.
   */
  int PASSWORD = 2;
  int SEARCH = 3;
  int EMAIL = 4;
  int NUMBER = 5;
  int TELEPHONE = 6;
  int URL = 7;
  int DATE = 8;
  int DATE_TIME = 9;
  int DATE_TIME_LOCAL = 10;
  int MONTH = 11;
  int TIME = 12;
  int WEEK = 13;
  int TEXT_AREA = 14;
  /**
   * Input caret is in a contenteditable node (not an INPUT field).
   */
  int CONTENT_EDITABLE = 15;
  /**
   * The focused node is date time field. The date time field does not have input caret but it is
   * necessary to distinguish from NONE for on-screen keyboard.
   */
  int DATE_TIME_FIELD = 16;
  /**
   * Input caret is in an editable node which doesn't support rich editing. It means that the
   * editable node cannot support the features like candidate texts and retrieving text around
   * cursor. However, it still can process raw key events and needs the on-screen keyboard if it
   * wants.
   */
  int NULL = 17;
  int MAX = 17;
}
