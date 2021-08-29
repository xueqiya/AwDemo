// Geoposition.java is auto generated by mojom_bindings_generator.py, do not edit


// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     services/device/public/mojom/geoposition.mojom
//

package org.chromium.device.mojom;

import androidx.annotation.IntDef;


public final class Geoposition extends org.chromium.mojo.bindings.Struct {

    private static final int STRUCT_SIZE = 88;
    private static final org.chromium.mojo.bindings.DataHeader[] VERSION_ARRAY = new org.chromium.mojo.bindings.DataHeader[] {new org.chromium.mojo.bindings.DataHeader(88, 0)};
    private static final org.chromium.mojo.bindings.DataHeader DEFAULT_STRUCT_INFO = VERSION_ARRAY[0];

    public static final class ErrorCode {
        private static final boolean IS_EXTENSIBLE = false;
        @IntDef({

            ErrorCode.NONE,
            ErrorCode.PERMISSION_DENIED,
            ErrorCode.POSITION_UNAVAILABLE,
            ErrorCode.TIMEOUT,
            ErrorCode.LAST})
        public @interface EnumType {}

        public static final int NONE = 0;
        public static final int PERMISSION_DENIED = 1;
        public static final int POSITION_UNAVAILABLE = 2;
        public static final int TIMEOUT = 3;
        public static final int LAST = 3;
        public static final int MIN_VALUE = 0;
        public static final int MAX_VALUE = 3;

        public static boolean isKnownValue(int value) {
            return value >= 0 && value <= 3;
        }

        public static void validate(int value) {
            if (IS_EXTENSIBLE || isKnownValue(value)) return;
            throw new org.chromium.mojo.bindings.DeserializationException("Invalid enum value.");
        }

        public static int toKnownValue(int value) {
          return value;
        }

        private ErrorCode() {}
    }
    public boolean valid;
    public double latitude;
    public double longitude;
    public double altitude;
    public double accuracy;
    public double altitudeAccuracy;
    public double heading;
    public double speed;
    public org.chromium.mojo_base.mojom.Time timestamp;
    public int errorCode;
    public String errorMessage;

    private Geoposition(int version) {
        super(STRUCT_SIZE, version);
        this.latitude = (double) GeopositionConstants.BAD_LATITUDE_LONGITUDE;
        this.longitude = (double) GeopositionConstants.BAD_LATITUDE_LONGITUDE;
        this.altitude = (double) GeopositionConstants.BAD_ALTITUDE;
        this.accuracy = (double) GeopositionConstants.BAD_ACCURACY;
        this.altitudeAccuracy = (double) GeopositionConstants.BAD_ACCURACY;
        this.heading = (double) GeopositionConstants.BAD_HEADING;
        this.speed = (double) GeopositionConstants.BAD_SPEED;
        this.errorCode = (int) Geoposition.ErrorCode.NONE;
    }

    public Geoposition() {
        this(0);
    }

    public static Geoposition deserialize(org.chromium.mojo.bindings.Message message) {
        return decode(new org.chromium.mojo.bindings.Decoder(message));
    }

    /**
     * Similar to the method above, but deserializes from a |ByteBuffer| instance.
     *
     * @throws org.chromium.mojo.bindings.DeserializationException on deserialization failure.
     */
    public static Geoposition deserialize(java.nio.ByteBuffer data) {
        return deserialize(new org.chromium.mojo.bindings.Message(
                data, new java.util.ArrayList<org.chromium.mojo.system.Handle>()));
    }

    @SuppressWarnings("unchecked")
    public static Geoposition decode(org.chromium.mojo.bindings.Decoder decoder0) {
        if (decoder0 == null) {
            return null;
        }
        decoder0.increaseStackDepth();
        Geoposition result;
        try {
            org.chromium.mojo.bindings.DataHeader mainDataHeader = decoder0.readAndValidateDataHeader(VERSION_ARRAY);
            final int elementsOrVersion = mainDataHeader.elementsOrVersion;
            result = new Geoposition(elementsOrVersion);
                {
                    
                result.valid = decoder0.readBoolean(8, 0);
                }
                {
                    
                result.errorCode = decoder0.readInt(12);
                    Geoposition.ErrorCode.validate(result.errorCode);
                    result.errorCode = Geoposition.ErrorCode.toKnownValue(result.errorCode);
                }
                {
                    
                result.latitude = decoder0.readDouble(16);
                }
                {
                    
                result.longitude = decoder0.readDouble(24);
                }
                {
                    
                result.altitude = decoder0.readDouble(32);
                }
                {
                    
                result.accuracy = decoder0.readDouble(40);
                }
                {
                    
                result.altitudeAccuracy = decoder0.readDouble(48);
                }
                {
                    
                result.heading = decoder0.readDouble(56);
                }
                {
                    
                result.speed = decoder0.readDouble(64);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(72, false);
                result.timestamp = org.chromium.mojo_base.mojom.Time.decode(decoder1);
                }
                {
                    
                result.errorMessage = decoder0.readString(80, false);
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
        
        encoder0.encode(this.valid, 8, 0);
        
        encoder0.encode(this.errorCode, 12);
        
        encoder0.encode(this.latitude, 16);
        
        encoder0.encode(this.longitude, 24);
        
        encoder0.encode(this.altitude, 32);
        
        encoder0.encode(this.accuracy, 40);
        
        encoder0.encode(this.altitudeAccuracy, 48);
        
        encoder0.encode(this.heading, 56);
        
        encoder0.encode(this.speed, 64);
        
        encoder0.encode(this.timestamp, 72, false);
        
        encoder0.encode(this.errorMessage, 80, false);
    }
}