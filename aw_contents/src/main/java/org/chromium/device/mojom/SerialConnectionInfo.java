// SerialConnectionInfo.java is auto generated by mojom_bindings_generator.py, do not edit


// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     services/device/public/mojom/serial.mojom
//

package org.chromium.device.mojom;

import androidx.annotation.IntDef;


public final class SerialConnectionInfo extends org.chromium.mojo.bindings.Struct {

    private static final int STRUCT_SIZE = 32;
    private static final org.chromium.mojo.bindings.DataHeader[] VERSION_ARRAY = new org.chromium.mojo.bindings.DataHeader[] {new org.chromium.mojo.bindings.DataHeader(32, 0)};
    private static final org.chromium.mojo.bindings.DataHeader DEFAULT_STRUCT_INFO = VERSION_ARRAY[0];
    public int bitrate;
    public int dataBits;
    public int parityBit;
    public int stopBits;
    public boolean ctsFlowControl;

    private SerialConnectionInfo(int version) {
        super(STRUCT_SIZE, version);
        this.bitrate = (int) 0;
        this.dataBits = (int) SerialDataBits.NONE;
        this.parityBit = (int) SerialParityBit.NONE;
        this.stopBits = (int) SerialStopBits.NONE;
    }

    public SerialConnectionInfo() {
        this(0);
    }

    public static SerialConnectionInfo deserialize(org.chromium.mojo.bindings.Message message) {
        return decode(new org.chromium.mojo.bindings.Decoder(message));
    }

    /**
     * Similar to the method above, but deserializes from a |ByteBuffer| instance.
     *
     * @throws org.chromium.mojo.bindings.DeserializationException on deserialization failure.
     */
    public static SerialConnectionInfo deserialize(java.nio.ByteBuffer data) {
        return deserialize(new org.chromium.mojo.bindings.Message(
                data, new java.util.ArrayList<org.chromium.mojo.system.Handle>()));
    }

    @SuppressWarnings("unchecked")
    public static SerialConnectionInfo decode(org.chromium.mojo.bindings.Decoder decoder0) {
        if (decoder0 == null) {
            return null;
        }
        decoder0.increaseStackDepth();
        SerialConnectionInfo result;
        try {
            org.chromium.mojo.bindings.DataHeader mainDataHeader = decoder0.readAndValidateDataHeader(VERSION_ARRAY);
            final int elementsOrVersion = mainDataHeader.elementsOrVersion;
            result = new SerialConnectionInfo(elementsOrVersion);
                {
                    
                result.bitrate = decoder0.readInt(8);
                }
                {
                    
                result.dataBits = decoder0.readInt(12);
                    SerialDataBits.validate(result.dataBits);
                    result.dataBits = SerialDataBits.toKnownValue(result.dataBits);
                }
                {
                    
                result.parityBit = decoder0.readInt(16);
                    SerialParityBit.validate(result.parityBit);
                    result.parityBit = SerialParityBit.toKnownValue(result.parityBit);
                }
                {
                    
                result.stopBits = decoder0.readInt(20);
                    SerialStopBits.validate(result.stopBits);
                    result.stopBits = SerialStopBits.toKnownValue(result.stopBits);
                }
                {
                    
                result.ctsFlowControl = decoder0.readBoolean(24, 0);
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
        
        encoder0.encode(this.bitrate, 8);
        
        encoder0.encode(this.dataBits, 12);
        
        encoder0.encode(this.parityBit, 16);
        
        encoder0.encode(this.stopBits, 20);
        
        encoder0.encode(this.ctsFlowControl, 24, 0);
    }
}