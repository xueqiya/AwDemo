// Copyright 2017 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.chromium.android_webview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.autofill.AutofillValue;

import org.chromium.base.ThreadUtils;
import org.chromium.components.autofill.AutofillProvider;
import org.chromium.components.autofill.FormData;
import org.chromium.components.autofill.FormFieldData;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.display.DisplayAndroid;

/**
 * This class uses Android autofill service to fill web form. All methods are
 * supposed to be called in UI thread.
 *
 * This class doesn't have 1:1 mapping to native AutofillProviderAndroid and is
 * same as how AwContents.java mapping to native AwContents, AwAutofillProvider
 * is owned by AwContents.java and AutofillProviderAndroid is owned by native
 * AwContents.
 */
public class AwAutofillProvider extends AutofillProvider {
    private static class FocusField {
        public final short fieldIndex;
        public final Rect absBound;

        public FocusField(short fieldIndex, Rect absBound) {
            this.fieldIndex = fieldIndex;
            this.absBound = absBound;
        }
    }
    /**
     * The class to wrap the request to framework.
     *
     * Though framework guarantees always giving us the autofill value of current
     * session, we still want to verify this by using unique virtual id which is
     * composed of sessionId and form field index, we don't use the request id
     * which comes from renderer as session id because it is not unique.
     */
    private static class AutofillRequest {
        private static final int INIT_ID = 1; // ID can't be 0 in Android.
        private static int sSessionId = INIT_ID;
        public final int sessionId;
        private FormData mFormData;
        private FocusField mFocusField;

        public AutofillRequest(FormData formData, FocusField focus) {
            sessionId = getNextClientId();
            mFormData = formData;
            mFocusField = focus;
        }

        public void fillViewStructure(ViewStructure structure) {
            structure.setWebDomain(mFormData.mHost);
            structure.setHtmlInfo(structure.newHtmlInfoBuilder("form")
                                          .addAttribute("name", mFormData.mName)
                                          .build());
            int index = structure.addChildCount(mFormData.mFields.size());
            short fieldIndex = 0;
            for (FormFieldData field : mFormData.mFields) {
                ViewStructure child = structure.newChild(index++);
                int virtualId = toVirtualId(sessionId, fieldIndex++);
                child.setAutofillId(structure.getAutofillId(), virtualId);
                if (field.mAutocompleteAttr != null) {
                    child.setAutofillHints(field.mAutocompleteAttr.split(" +"));
                }
                child.setHint(field.mPlaceholder);
                child.setHtmlInfo(child.newHtmlInfoBuilder("input")
                                          .addAttribute("name", field.mName)
                                          .addAttribute("type", field.mType)
                                          .build());
                switch (field.getControlType()) {
                    case FormFieldData.TYPE_LIST:
                        child.setAutofillType(View.AUTOFILL_TYPE_LIST);
                        child.setAutofillOptions(field.mOptionContents);
                        int i = findIndex(field.mOptionValues, field.getValue());
                        if (i != -1) {
                            child.setAutofillValue(AutofillValue.forList(i));
                        }
                        break;
                    case FormFieldData.TYPE_TOGGLE:
                        child.setAutofillType(View.AUTOFILL_TYPE_TOGGLE);
                        child.setAutofillValue(AutofillValue.forToggle(field.isChecked()));
                        break;
                    case FormFieldData.TYPE_TEXT:
                        child.setAutofillType(View.AUTOFILL_TYPE_TEXT);
                        child.setAutofillValue(AutofillValue.forText(field.getValue()));
                        break;
                    default:
                        break;
                }
            }
        }

        public boolean autofill(final SparseArray<AutofillValue> values) {
            for (int i = 0; i < values.size(); ++i) {
                int id = values.keyAt(i);
                if (toSessionId(id) != sessionId) return false;
                AutofillValue value = values.get(id);
                if (value == null) continue;
                short index = toIndex(id);
                if (index < 0 || index >= mFormData.mFields.size()) return false;
                FormFieldData field = mFormData.mFields.get(index);
                if (field == null) return false;
                switch (field.getControlType()) {
                    case FormFieldData.TYPE_LIST:
                        int j = value.getListValue();
                        if (j < 0 && j >= field.mOptionValues.length) continue;
                        field.updateValue(field.mOptionValues[j]);
                        break;
                    case FormFieldData.TYPE_TOGGLE:
                        field.setChecked(value.getToggleValue());
                        break;
                    case FormFieldData.TYPE_TEXT:
                        field.updateValue((String) value.getTextValue());
                        break;
                    default:
                        break;
                }
            }
            return true;
        }

        public void setFocusField(FocusField focusField) {
            mFocusField = focusField;
        }

        public FocusField getFocusField() {
            return mFocusField;
        }

        public int getFieldCount() {
            return mFormData.mFields.size();
        }

        public AutofillValue getFieldNewValue(int index) {
            FormFieldData field = mFormData.mFields.get(index);
            if (field == null) return null;
            String value = field.getValue();
            return AutofillValue.forText(value);
        }

        public int getVirtualId(short index) {
            return toVirtualId(sessionId, index);
        }

        private static int findIndex(String[] values, String value) {
            if (values != null && value != null) {
                for (int i = 0; i < values.length; i++) {
                    if (value.equals(values[i])) return i;
                }
            }
            return -1;
        }

        private static int getNextClientId() {
            ThreadUtils.assertOnUiThread();
            if (sSessionId == 0xffff) sSessionId = INIT_ID;
            return sSessionId++;
        }

        private static int toSessionId(int virtualId) {
            return (virtualId & 0xffff0000) >> 16;
        }

        private static short toIndex(int virtualId) {
            return (short) (virtualId & 0xffff);
        }

        private static int toVirtualId(int clientId, short index) {
            return (clientId << 16) | index;
        }
    }

    private AwAutofillManager mAutofillManager;
    private ViewGroup mContainerView;
    private WebContents mWebContents;

    private AutofillRequest mRequest;
    private long mNativeAutofillProvider;

    public AwAutofillProvider(Context context, ViewGroup containerView) {
        mAutofillManager = new AwAutofillManager(context);
        mContainerView = containerView;
    }

    @Override
    public void onContainerViewChanged(ViewGroup containerView) {
        mContainerView = containerView;
    }

    @Override
    public void onProvideAutoFillVirtualStructure(ViewStructure structure, int flags) {
        // This method could be called for the session started by the native
        // control outside of WebView, e.g. the URL bar, in this case, we simply
        // return.
        if (mRequest == null) return;
        mRequest.fillViewStructure(structure);
    }

    @Override
    public void autofill(final SparseArray<AutofillValue> values) {
        if (mNativeAutofillProvider != 0 && mRequest.autofill((values))) {
            autofill(mNativeAutofillProvider, mRequest.mFormData);
        }
    }

    @Override
    public boolean shouldQueryAutofillSuggestion() {
        return mRequest != null && mRequest.getFocusField() != null
                && !mAutofillManager.isAutofillInputUIShowing();
    }

    @Override
    public void queryAutofillSuggestion() {
        if (shouldQueryAutofillSuggestion()) {
            FocusField focusField = mRequest.getFocusField();
            mAutofillManager.requestAutofill(mContainerView,
                    mRequest.getVirtualId(focusField.fieldIndex), focusField.absBound);
        }
    }

    @Override
    public void startAutofillSession(
            FormData formData, int focus, float x, float y, float width, float height) {
        // Check focusField inside short value?
        // Autofill Manager might have session that wasn't started by WebView,
        // we just always cancel existing session here.
        mAutofillManager.cancel();
        Rect absBound = transformToWindowBounds(new RectF(x, y, x + width, y + height));
        mRequest = new AutofillRequest(formData, new FocusField((short) focus, absBound));
        int virtualId = mRequest.getVirtualId((short) focus);
        mAutofillManager.notifyVirtualViewEntered(mContainerView, virtualId, absBound);
    }

    @Override
    public void onTextFieldDidChange(int index, float x, float y, float width, float height) {
        // Check index inside short value?
        if (mRequest == null) return;

        short sIndex = (short) index;
        FocusField focusField = mRequest.getFocusField();
        if (focusField == null || sIndex != focusField.fieldIndex) {
            onFocusChanged(true, index, x, y, width, height);
        } else {
            // Currently there is no api to notify both value and position
            // change, before the API is availabe, we still need to call
            // notifyVirtualViewEntered() to tell current coordinates because
            // the position could be changed.
            int virtualId = mRequest.getVirtualId(sIndex);
            Rect absBound = transformToWindowBounds(new RectF(x, y, x + width, y + height));
            mAutofillManager.notifyVirtualViewExited(mContainerView, virtualId);
            mAutofillManager.notifyVirtualViewEntered(mContainerView, virtualId, absBound);
            // Update focus field position.
            mRequest.setFocusField(new FocusField(focusField.fieldIndex, absBound));
        }
        notifyVirtualValueChanged(index);
    }

    private void notifyVirtualValueChanged(int index) {
        AutofillValue autofillValue = mRequest.getFieldNewValue(index);
        mAutofillManager.notifyVirtualValueChanged(
                mContainerView, mRequest.getVirtualId((short) index), autofillValue);
    }

    @Override
    public void onWillSubmitForm() {
        // The changes could be missing, like those made by Javascript, we'd better to notify
        // AutofillManager current values. also see crbug.com/353001 and crbug.com/732856.
        notifyFormValues();
        mAutofillManager.commit();
        mRequest = null;
    }

    @Override
    public void onFocusChanged(
            boolean focusOnForm, int focusField, float x, float y, float width, float height) {
        // Check focusField inside short value?
        // FocusNoLongerOnForm is called after form submitted.
        if (mRequest == null) return;
        FocusField prev = mRequest.getFocusField();
        if (focusOnForm) {
            Rect absBound = transformToWindowBounds(new RectF(x, y, x + width, y + height));
            if (prev != null && prev.fieldIndex == focusField && absBound.equals(prev.absBound)) {
                return;
            }

            // Notify focus changed.
            if (prev != null) {
                mAutofillManager.notifyVirtualViewExited(
                        mContainerView, mRequest.getVirtualId(prev.fieldIndex));
            }

            mAutofillManager.notifyVirtualViewEntered(
                    mContainerView, mRequest.getVirtualId((short) focusField), absBound);
            // The focus field value might not sync with platform's
            // AutofillManager, just notify it value changed.
            notifyVirtualValueChanged(focusField);
            mRequest.setFocusField(new FocusField((short) focusField, absBound));
        } else {
            if (prev == null) return;
            // Notify focus changed.
            mAutofillManager.notifyVirtualViewExited(
                    mContainerView, mRequest.getVirtualId(prev.fieldIndex));
            mRequest.setFocusField(null);
        }
    }

    @Override
    protected void reset() {
        mAutofillManager.cancel();
        mRequest = null;
    }

    @Override
    protected void setNativeAutofillProvider(long nativeAutofillProvider) {
        if (nativeAutofillProvider == mNativeAutofillProvider) return;
        mNativeAutofillProvider = nativeAutofillProvider;
        // Setting the mNativeAutofillProvider to 0 may occur as a
        // result of WebView.destroy, or because a WebView has been
        // gc'ed. In the former case we can go ahead and clean up the
        // frameworks autofill manager, but in the latter case the
        // binder connection has already been dropped in a framework
        // finalizer, and so the methods we call will throw. It's not
        // possible to know which case we're in, so just catch and
        // ignore the exception.
        try {
            reset();
            if (nativeAutofillProvider == 0) {
                mAutofillManager.destroy();
            }
        } catch (IllegalStateException e) {
        }
    }

    @Override
    public void setWebContents(WebContents webContents) {
        if (webContents == mWebContents) return;
        mWebContents = webContents;
        reset();
    }

    @Override
    protected void onDidFillAutofillFormData() {
        notifyFormValues();
    }

    private void notifyFormValues() {
        if (mRequest == null) return;
        for (int i = 0; i < mRequest.getFieldCount(); ++i) notifyVirtualValueChanged(i);
    }

    private Rect transformToWindowBounds(RectF rect) {
        // Convert bounds to device pixel.
        WindowAndroid windowAndroid = mWebContents.getTopLevelNativeWindow();
        DisplayAndroid displayAndroid = windowAndroid.getDisplay();
        float dipScale = displayAndroid.getDipScale();
        RectF bounds = new RectF(rect);
        Matrix matrix = new Matrix();
        matrix.setScale(dipScale, dipScale);
        int[] location = new int[2];
        mContainerView.getLocationOnScreen(location);
        matrix.postTranslate(location[0], location[1]);
        matrix.mapRect(bounds);
        return new Rect(
                (int) bounds.left, (int) bounds.top, (int) bounds.right, (int) bounds.bottom);
    }
}
