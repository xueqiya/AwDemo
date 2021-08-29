// HidReportItem.java is auto generated by mojom_bindings_generator.py, do not edit


// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     services/device/public/mojom/hid.mojom
//

package org.chromium.device.mojom;

import androidx.annotation.IntDef;


public final class HidReportItem extends org.chromium.mojo.bindings.Struct {

    private static final int STRUCT_SIZE = 88;
    private static final org.chromium.mojo.bindings.DataHeader[] VERSION_ARRAY = new org.chromium.mojo.bindings.DataHeader[] {new org.chromium.mojo.bindings.DataHeader(88, 0)};
    private static final org.chromium.mojo.bindings.DataHeader DEFAULT_STRUCT_INFO = VERSION_ARRAY[0];
    public boolean isRange;
    public boolean isConstant;
    public boolean isVariable;
    public boolean isRelative;
    public boolean wrap;
    public boolean isNonLinear;
    public boolean noPreferredState;
    public boolean hasNullPosition;
    public boolean isVolatile;
    public boolean isBufferedBytes;
    public HidUsageAndPage[] usages;
    public HidUsageAndPage usageMinimum;
    public HidUsageAndPage usageMaximum;
    public int designatorMinimum;
    public int designatorMaximum;
    public int stringMinimum;
    public int stringMaximum;
    public int logicalMinimum;
    public int logicalMaximum;
    public int physicalMinimum;
    public int physicalMaximum;
    public int unitExponent;
    public int unit;
    public int reportSize;
    public int reportCount;

    private HidReportItem(int version) {
        super(STRUCT_SIZE, version);
    }

    public HidReportItem() {
        this(0);
    }

    public static HidReportItem deserialize(org.chromium.mojo.bindings.Message message) {
        return decode(new org.chromium.mojo.bindings.Decoder(message));
    }

    /**
     * Similar to the method above, but deserializes from a |ByteBuffer| instance.
     *
     * @throws org.chromium.mojo.bindings.DeserializationException on deserialization failure.
     */
    public static HidReportItem deserialize(java.nio.ByteBuffer data) {
        return deserialize(new org.chromium.mojo.bindings.Message(
                data, new java.util.ArrayList<org.chromium.mojo.system.Handle>()));
    }

    @SuppressWarnings("unchecked")
    public static HidReportItem decode(org.chromium.mojo.bindings.Decoder decoder0) {
        if (decoder0 == null) {
            return null;
        }
        decoder0.increaseStackDepth();
        HidReportItem result;
        try {
            org.chromium.mojo.bindings.DataHeader mainDataHeader = decoder0.readAndValidateDataHeader(VERSION_ARRAY);
            final int elementsOrVersion = mainDataHeader.elementsOrVersion;
            result = new HidReportItem(elementsOrVersion);
                {
                    
                result.isRange = decoder0.readBoolean(8, 0);
                }
                {
                    
                result.isConstant = decoder0.readBoolean(8, 1);
                }
                {
                    
                result.isVariable = decoder0.readBoolean(8, 2);
                }
                {
                    
                result.isRelative = decoder0.readBoolean(8, 3);
                }
                {
                    
                result.wrap = decoder0.readBoolean(8, 4);
                }
                {
                    
                result.isNonLinear = decoder0.readBoolean(8, 5);
                }
                {
                    
                result.noPreferredState = decoder0.readBoolean(8, 6);
                }
                {
                    
                result.hasNullPosition = decoder0.readBoolean(8, 7);
                }
                {
                    
                result.isVolatile = decoder0.readBoolean(9, 0);
                }
                {
                    
                result.isBufferedBytes = decoder0.readBoolean(9, 1);
                }
                {
                    
                result.designatorMinimum = decoder0.readInt(12);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(16, false);
                {
                    org.chromium.mojo.bindings.DataHeader si1 = decoder1.readDataHeaderForPointerArray(org.chromium.mojo.bindings.BindingsHelper.UNSPECIFIED_ARRAY_LENGTH);
                    result.usages = new HidUsageAndPage[si1.elementsOrVersion];
                    for (int i1 = 0; i1 < si1.elementsOrVersion; ++i1) {
                        
                        org.chromium.mojo.bindings.Decoder decoder2 = decoder1.readPointer(org.chromium.mojo.bindings.DataHeader.HEADER_SIZE + org.chromium.mojo.bindings.BindingsHelper.POINTER_SIZE * i1, false);
                        result.usages[i1] = HidUsageAndPage.decode(decoder2);
                    }
                }
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(24, false);
                result.usageMinimum = HidUsageAndPage.decode(decoder1);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(32, false);
                result.usageMaximum = HidUsageAndPage.decode(decoder1);
                }
                {
                    
                result.designatorMaximum = decoder0.readInt(40);
                }
                {
                    
                result.stringMinimum = decoder0.readInt(44);
                }
                {
                    
                result.stringMaximum = decoder0.readInt(48);
                }
                {
                    
                result.logicalMinimum = decoder0.readInt(52);
                }
                {
                    
                result.logicalMaximum = decoder0.readInt(56);
                }
                {
                    
                result.physicalMinimum = decoder0.readInt(60);
                }
                {
                    
                result.physicalMaximum = decoder0.readInt(64);
                }
                {
                    
                result.unitExponent = decoder0.readInt(68);
                }
                {
                    
                result.unit = decoder0.readInt(72);
                }
                {
                    
                result.reportSize = decoder0.readInt(76);
                }
                {
                    
                result.reportCount = decoder0.readInt(80);
                }

        } finally {
            decoder0.decreaseStackDepth();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected final void encode(org.chromium.mojo.bindings.Encoder encoder) {
        org.chromium.mojo.bindings.Encoder encoder0 = encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO);
        
        encoder0.encode(this.isRange, 8, 0);
        
        encoder0.encode(this.isConstant, 8, 1);
        
        encoder0.encode(this.isVariable, 8, 2);
        
        encoder0.encode(this.isRelative, 8, 3);
        
        encoder0.encode(this.wrap, 8, 4);
        
        encoder0.encode(this.isNonLinear, 8, 5);
        
        encoder0.encode(this.noPreferredState, 8, 6);
        
        encoder0.encode(this.hasNullPosition, 8, 7);
        
        encoder0.encode(this.isVolatile, 9, 0);
        
        encoder0.encode(this.isBufferedBytes, 9, 1);
        
        encoder0.encode(this.designatorMinimum, 12);
        
        if (this.usages == null) {
            encoder0.encodeNullPointer(16, false);
        } else {
            org.chromium.mojo.bindings.Encoder encoder1 = encoder0.encodePointerArray(this.usages.length, 16, org.chromium.mojo.bindings.BindingsHelper.UNSPECIFIED_ARRAY_LENGTH);
            for (int i0 = 0; i0 < this.usages.length; ++i0) {
                
                encoder1.encode(this.usages[i0], org.chromium.mojo.bindings.DataHeader.HEADER_SIZE + org.chromium.mojo.bindings.BindingsHelper.POINTER_SIZE * i0, false);
            }
        }
        
        encoder0.encode(this.usageMinimum, 24, false);
        
        encoder0.encode(this.usageMaximum, 32, false);
        
        encoder0.encode(this.designatorMaximum, 40);
        
        encoder0.encode(this.stringMinimum, 44);
        
        encoder0.encode(this.stringMaximum, 48);
        
        encoder0.encode(this.logicalMinimum, 52);
        
        encoder0.encode(this.logicalMaximum, 56);
        
        encoder0.encode(this.physicalMinimum, 60);
        
        encoder0.encode(this.physicalMaximum, 64);
        
        encoder0.encode(this.unitExponent, 68);
        
        encoder0.encode(this.unit, 72);
        
        encoder0.encode(this.reportSize, 76);
        
        encoder0.encode(this.reportCount, 80);
    }
}