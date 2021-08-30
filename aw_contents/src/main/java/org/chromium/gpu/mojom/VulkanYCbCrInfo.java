// VulkanYCbCrInfo.java is auto generated by mojom_bindings_generator.py, do not edit


// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     gpu/ipc/common/vulkan_ycbcr_info.mojom
//

package org.chromium.gpu.mojom;

import androidx.annotation.IntDef;


public final class VulkanYCbCrInfo extends org.chromium.mojo.bindings.Struct {

    private static final int STRUCT_SIZE = 40;
    private static final org.chromium.mojo.bindings.DataHeader[] VERSION_ARRAY = new org.chromium.mojo.bindings.DataHeader[] {new org.chromium.mojo.bindings.DataHeader(40, 0)};
    private static final org.chromium.mojo.bindings.DataHeader DEFAULT_STRUCT_INFO = VERSION_ARRAY[0];
    public int imageFormat;
    public long externalFormat;
    public int suggestedYcbcrModel;
    public int suggestedYcbcrRange;
    public int suggestedXchromaOffset;
    public int suggestedYchromaOffset;
    public int formatFeatures;

    private VulkanYCbCrInfo(int version) {
        super(STRUCT_SIZE, version);
    }

    public VulkanYCbCrInfo() {
        this(0);
    }

    public static VulkanYCbCrInfo deserialize(org.chromium.mojo.bindings.Message message) {
        return decode(new org.chromium.mojo.bindings.Decoder(message));
    }

    /**
     * Similar to the method above, but deserializes from a |ByteBuffer| instance.
     *
     * @throws org.chromium.mojo.bindings.DeserializationException on deserialization failure.
     */
    public static VulkanYCbCrInfo deserialize(java.nio.ByteBuffer data) {
        return deserialize(new org.chromium.mojo.bindings.Message(
                data, new java.util.ArrayList<org.chromium.mojo.system.Handle>()));
    }

    @SuppressWarnings("unchecked")
    public static VulkanYCbCrInfo decode(org.chromium.mojo.bindings.Decoder decoder0) {
        if (decoder0 == null) {
            return null;
        }
        decoder0.increaseStackDepth();
        VulkanYCbCrInfo result;
        try {
            org.chromium.mojo.bindings.DataHeader mainDataHeader = decoder0.readAndValidateDataHeader(VERSION_ARRAY);
            final int elementsOrVersion = mainDataHeader.elementsOrVersion;
            result = new VulkanYCbCrInfo(elementsOrVersion);
                {
                    
                result.imageFormat = decoder0.readInt(8);
                }
                {
                    
                result.suggestedYcbcrModel = decoder0.readInt(12);
                }
                {
                    
                result.externalFormat = decoder0.readLong(16);
                }
                {
                    
                result.suggestedYcbcrRange = decoder0.readInt(24);
                }
                {
                    
                result.suggestedXchromaOffset = decoder0.readInt(28);
                }
                {
                    
                result.suggestedYchromaOffset = decoder0.readInt(32);
                }
                {
                    
                result.formatFeatures = decoder0.readInt(36);
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
        
        encoder0.encode(this.imageFormat, 8);
        
        encoder0.encode(this.suggestedYcbcrModel, 12);
        
        encoder0.encode(this.externalFormat, 16);
        
        encoder0.encode(this.suggestedYcbcrRange, 24);
        
        encoder0.encode(this.suggestedXchromaOffset, 28);
        
        encoder0.encode(this.suggestedYchromaOffset, 32);
        
        encoder0.encode(this.formatFeatures, 36);
    }
}