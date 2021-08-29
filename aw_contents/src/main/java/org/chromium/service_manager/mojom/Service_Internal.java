// Service_Internal.java is auto generated by mojom_bindings_generator.py, do not edit


// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     services/service_manager/public/mojom/service.mojom
//

package org.chromium.service_manager.mojom;

import androidx.annotation.IntDef;


class Service_Internal {

    public static final org.chromium.mojo.bindings.Interface.Manager<Service, Service.Proxy> MANAGER =
            new org.chromium.mojo.bindings.Interface.Manager<Service, Service.Proxy>() {

        @Override
        public String getName() {
            return "service_manager.mojom.Service";
        }

        @Override
        public int getVersion() {
          return 0;
        }

        @Override
        public Proxy buildProxy(org.chromium.mojo.system.Core core,
                                org.chromium.mojo.bindings.MessageReceiverWithResponder messageReceiver) {
            return new Proxy(core, messageReceiver);
        }

        @Override
        public Stub buildStub(org.chromium.mojo.system.Core core, Service impl) {
            return new Stub(core, impl);
        }

        @Override
        public Service[] buildArray(int size) {
          return new Service[size];
        }
    };


    private static final int ON_START_ORDINAL = 0;

    private static final int ON_BIND_INTERFACE_ORDINAL = 1;

    private static final int CREATE_PACKAGED_SERVICE_INSTANCE_ORDINAL = 2;


    static final class Proxy extends org.chromium.mojo.bindings.Interface.AbstractProxy implements Service.Proxy {

        Proxy(org.chromium.mojo.system.Core core,
              org.chromium.mojo.bindings.MessageReceiverWithResponder messageReceiver) {
            super(core, messageReceiver);
        }


        @Override
        public void onStart(
Identity identity, 
OnStartResponse callback) {

            ServiceOnStartParams _message = new ServiceOnStartParams();

            _message.identity = identity;


            getProxyHandler().getMessageReceiver().acceptWithResponder(
                    _message.serializeWithHeader(
                            getProxyHandler().getCore(),
                            new org.chromium.mojo.bindings.MessageHeader(
                                    ON_START_ORDINAL,
                                    org.chromium.mojo.bindings.MessageHeader.MESSAGE_EXPECTS_RESPONSE_FLAG,
                                    0)),
                    new ServiceOnStartResponseParamsForwardToCallback(callback));

        }


        @Override
        public void onBindInterface(
BindSourceInfo source, String interfaceName, org.chromium.mojo.system.MessagePipeHandle interfacePipe, 
OnBindInterfaceResponse callback) {

            ServiceOnBindInterfaceParams _message = new ServiceOnBindInterfaceParams();

            _message.source = source;

            _message.interfaceName = interfaceName;

            _message.interfacePipe = interfacePipe;


            getProxyHandler().getMessageReceiver().acceptWithResponder(
                    _message.serializeWithHeader(
                            getProxyHandler().getCore(),
                            new org.chromium.mojo.bindings.MessageHeader(
                                    ON_BIND_INTERFACE_ORDINAL,
                                    org.chromium.mojo.bindings.MessageHeader.MESSAGE_EXPECTS_RESPONSE_FLAG,
                                    0)),
                    new ServiceOnBindInterfaceResponseParamsForwardToCallback(callback));

        }


        @Override
        public void createPackagedServiceInstance(
Identity identity, org.chromium.mojo.bindings.InterfaceRequest<Service> receiver, ProcessMetadata metadata) {

            ServiceCreatePackagedServiceInstanceParams _message = new ServiceCreatePackagedServiceInstanceParams();

            _message.identity = identity;

            _message.receiver = receiver;

            _message.metadata = metadata;


            getProxyHandler().getMessageReceiver().accept(
                    _message.serializeWithHeader(
                            getProxyHandler().getCore(),
                            new org.chromium.mojo.bindings.MessageHeader(CREATE_PACKAGED_SERVICE_INSTANCE_ORDINAL)));

        }


    }

    static final class Stub extends org.chromium.mojo.bindings.Interface.Stub<Service> {

        Stub(org.chromium.mojo.system.Core core, Service impl) {
            super(core, impl);
        }

        @Override
        public boolean accept(org.chromium.mojo.bindings.Message message) {
            try {
                org.chromium.mojo.bindings.ServiceMessage messageWithHeader =
                        message.asServiceMessage();
                org.chromium.mojo.bindings.MessageHeader header = messageWithHeader.getHeader();
                int flags = org.chromium.mojo.bindings.MessageHeader.NO_FLAG;
                if (header.hasFlag(org.chromium.mojo.bindings.MessageHeader.MESSAGE_IS_SYNC_FLAG)) {
                    flags = flags | org.chromium.mojo.bindings.MessageHeader.MESSAGE_IS_SYNC_FLAG;
                }
                if (!header.validateHeader(flags)) {
                    return false;
                }
                switch(header.getType()) {

                    case org.chromium.mojo.bindings.interfacecontrol.InterfaceControlMessagesConstants.RUN_OR_CLOSE_PIPE_MESSAGE_ID:
                        return org.chromium.mojo.bindings.InterfaceControlMessagesHelper.handleRunOrClosePipe(
                                Service_Internal.MANAGER, messageWithHeader);









                    case CREATE_PACKAGED_SERVICE_INSTANCE_ORDINAL: {

                        ServiceCreatePackagedServiceInstanceParams data =
                                ServiceCreatePackagedServiceInstanceParams.deserialize(messageWithHeader.getPayload());

                        getImpl().createPackagedServiceInstance(data.identity, data.receiver, data.metadata);
                        return true;
                    }


                    default:
                        return false;
                }
            } catch (org.chromium.mojo.bindings.DeserializationException e) {
                System.err.println(e.toString());
                return false;
            }
        }

        @Override
        public boolean acceptWithResponder(org.chromium.mojo.bindings.Message message, org.chromium.mojo.bindings.MessageReceiver receiver) {
            try {
                org.chromium.mojo.bindings.ServiceMessage messageWithHeader =
                        message.asServiceMessage();
                org.chromium.mojo.bindings.MessageHeader header = messageWithHeader.getHeader();
                int flags = org.chromium.mojo.bindings.MessageHeader.MESSAGE_EXPECTS_RESPONSE_FLAG;
                if (header.hasFlag(org.chromium.mojo.bindings.MessageHeader.MESSAGE_IS_SYNC_FLAG)) {
                    flags = flags | org.chromium.mojo.bindings.MessageHeader.MESSAGE_IS_SYNC_FLAG;
                }
                if (!header.validateHeader(flags)) {
                    return false;
                }
                switch(header.getType()) {

                    case org.chromium.mojo.bindings.interfacecontrol.InterfaceControlMessagesConstants.RUN_MESSAGE_ID:
                        return org.chromium.mojo.bindings.InterfaceControlMessagesHelper.handleRun(
                                getCore(), Service_Internal.MANAGER, messageWithHeader, receiver);







                    case ON_START_ORDINAL: {

                        ServiceOnStartParams data =
                                ServiceOnStartParams.deserialize(messageWithHeader.getPayload());

                        getImpl().onStart(data.identity, new ServiceOnStartResponseParamsProxyToResponder(getCore(), receiver, header.getRequestId()));
                        return true;
                    }







                    case ON_BIND_INTERFACE_ORDINAL: {

                        ServiceOnBindInterfaceParams data =
                                ServiceOnBindInterfaceParams.deserialize(messageWithHeader.getPayload());

                        getImpl().onBindInterface(data.source, data.interfaceName, data.interfacePipe, new ServiceOnBindInterfaceResponseParamsProxyToResponder(getCore(), receiver, header.getRequestId()));
                        return true;
                    }




                    default:
                        return false;
                }
            } catch (org.chromium.mojo.bindings.DeserializationException e) {
                System.err.println(e.toString());
                return false;
            }
        }
    }


    
    static final class ServiceOnStartParams extends org.chromium.mojo.bindings.Struct {

        private static final int STRUCT_SIZE = 16;
        private static final org.chromium.mojo.bindings.DataHeader[] VERSION_ARRAY = new org.chromium.mojo.bindings.DataHeader[] {new org.chromium.mojo.bindings.DataHeader(16, 0)};
        private static final org.chromium.mojo.bindings.DataHeader DEFAULT_STRUCT_INFO = VERSION_ARRAY[0];
        public Identity identity;

        private ServiceOnStartParams(int version) {
            super(STRUCT_SIZE, version);
        }

        public ServiceOnStartParams() {
            this(0);
        }

        public static ServiceOnStartParams deserialize(org.chromium.mojo.bindings.Message message) {
            return decode(new org.chromium.mojo.bindings.Decoder(message));
        }

        /**
         * Similar to the method above, but deserializes from a |ByteBuffer| instance.
         *
         * @throws org.chromium.mojo.bindings.DeserializationException on deserialization failure.
         */
        public static ServiceOnStartParams deserialize(java.nio.ByteBuffer data) {
            return deserialize(new org.chromium.mojo.bindings.Message(
                    data, new java.util.ArrayList<org.chromium.mojo.system.Handle>()));
        }

        @SuppressWarnings("unchecked")
        public static ServiceOnStartParams decode(org.chromium.mojo.bindings.Decoder decoder0) {
            if (decoder0 == null) {
                return null;
            }
            decoder0.increaseStackDepth();
            ServiceOnStartParams result;
            try {
                org.chromium.mojo.bindings.DataHeader mainDataHeader = decoder0.readAndValidateDataHeader(VERSION_ARRAY);
                final int elementsOrVersion = mainDataHeader.elementsOrVersion;
                result = new ServiceOnStartParams(elementsOrVersion);
                    {
                        
                    org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(8, false);
                    result.identity = Identity.decode(decoder1);
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
            
            encoder0.encode(this.identity, 8, false);
        }
    }



    
    static final class ServiceOnStartResponseParams extends org.chromium.mojo.bindings.Struct {

        private static final int STRUCT_SIZE = 16;
        private static final org.chromium.mojo.bindings.DataHeader[] VERSION_ARRAY = new org.chromium.mojo.bindings.DataHeader[] {new org.chromium.mojo.bindings.DataHeader(16, 0)};
        private static final org.chromium.mojo.bindings.DataHeader DEFAULT_STRUCT_INFO = VERSION_ARRAY[0];
        public org.chromium.mojo.bindings.InterfaceRequest<Connector> connectorReceiver;
        public org.chromium.mojo.bindings.AssociatedInterfaceRequestNotSupported controlReceiver;

        private ServiceOnStartResponseParams(int version) {
            super(STRUCT_SIZE, version);
        }

        public ServiceOnStartResponseParams() {
            this(0);
        }

        public static ServiceOnStartResponseParams deserialize(org.chromium.mojo.bindings.Message message) {
            return decode(new org.chromium.mojo.bindings.Decoder(message));
        }

        /**
         * Similar to the method above, but deserializes from a |ByteBuffer| instance.
         *
         * @throws org.chromium.mojo.bindings.DeserializationException on deserialization failure.
         */
        public static ServiceOnStartResponseParams deserialize(java.nio.ByteBuffer data) {
            return deserialize(new org.chromium.mojo.bindings.Message(
                    data, new java.util.ArrayList<org.chromium.mojo.system.Handle>()));
        }

        @SuppressWarnings("unchecked")
        public static ServiceOnStartResponseParams decode(org.chromium.mojo.bindings.Decoder decoder0) {
            if (decoder0 == null) {
                return null;
            }
            decoder0.increaseStackDepth();
            ServiceOnStartResponseParams result;
            try {
                org.chromium.mojo.bindings.DataHeader mainDataHeader = decoder0.readAndValidateDataHeader(VERSION_ARRAY);
                final int elementsOrVersion = mainDataHeader.elementsOrVersion;
                result = new ServiceOnStartResponseParams(elementsOrVersion);
                    {
                        
                    result.connectorReceiver = decoder0.readInterfaceRequest(8, false);
                    }
                    {
                        
                    result.controlReceiver = decoder0.readAssociatedInterfaceRequestNotSupported(12, false);
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
            
            encoder0.encode(this.connectorReceiver, 8, false);
            
            encoder0.encode(this.controlReceiver, 12, false);
        }
    }

    static class ServiceOnStartResponseParamsForwardToCallback extends org.chromium.mojo.bindings.SideEffectFreeCloseable
            implements org.chromium.mojo.bindings.MessageReceiver {
        private final Service.OnStartResponse mCallback;

        ServiceOnStartResponseParamsForwardToCallback(Service.OnStartResponse callback) {
            this.mCallback = callback;
        }

        @Override
        public boolean accept(org.chromium.mojo.bindings.Message message) {
            try {
                org.chromium.mojo.bindings.ServiceMessage messageWithHeader =
                        message.asServiceMessage();
                org.chromium.mojo.bindings.MessageHeader header = messageWithHeader.getHeader();
                if (!header.validateHeader(ON_START_ORDINAL,
                                           org.chromium.mojo.bindings.MessageHeader.MESSAGE_IS_RESPONSE_FLAG)) {
                    return false;
                }

                ServiceOnStartResponseParams response = ServiceOnStartResponseParams.deserialize(messageWithHeader.getPayload());

                mCallback.call(response.connectorReceiver, response.controlReceiver);
                return true;
            } catch (org.chromium.mojo.bindings.DeserializationException e) {
                return false;
            }
        }
    }

    static class ServiceOnStartResponseParamsProxyToResponder implements Service.OnStartResponse {

        private final org.chromium.mojo.system.Core mCore;
        private final org.chromium.mojo.bindings.MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceOnStartResponseParamsProxyToResponder(
                org.chromium.mojo.system.Core core,
                org.chromium.mojo.bindings.MessageReceiver messageReceiver,
                long requestId) {
            mCore = core;
            mMessageReceiver = messageReceiver;
            mRequestId = requestId;
        }

        @Override
        public void call(org.chromium.mojo.bindings.InterfaceRequest<Connector> connectorReceiver, org.chromium.mojo.bindings.AssociatedInterfaceRequestNotSupported controlReceiver) {
            ServiceOnStartResponseParams _response = new ServiceOnStartResponseParams();

            _response.connectorReceiver = connectorReceiver;

            _response.controlReceiver = controlReceiver;

            org.chromium.mojo.bindings.ServiceMessage _message =
                    _response.serializeWithHeader(
                            mCore,
                            new org.chromium.mojo.bindings.MessageHeader(
                                    ON_START_ORDINAL,
                                    org.chromium.mojo.bindings.MessageHeader.MESSAGE_IS_RESPONSE_FLAG,
                                    mRequestId));
            mMessageReceiver.accept(_message);
        }
    }



    
    static final class ServiceOnBindInterfaceParams extends org.chromium.mojo.bindings.Struct {

        private static final int STRUCT_SIZE = 32;
        private static final org.chromium.mojo.bindings.DataHeader[] VERSION_ARRAY = new org.chromium.mojo.bindings.DataHeader[] {new org.chromium.mojo.bindings.DataHeader(32, 0)};
        private static final org.chromium.mojo.bindings.DataHeader DEFAULT_STRUCT_INFO = VERSION_ARRAY[0];
        public BindSourceInfo source;
        public String interfaceName;
        public org.chromium.mojo.system.MessagePipeHandle interfacePipe;

        private ServiceOnBindInterfaceParams(int version) {
            super(STRUCT_SIZE, version);
            this.interfacePipe = org.chromium.mojo.system.InvalidHandle.INSTANCE;
        }

        public ServiceOnBindInterfaceParams() {
            this(0);
        }

        public static ServiceOnBindInterfaceParams deserialize(org.chromium.mojo.bindings.Message message) {
            return decode(new org.chromium.mojo.bindings.Decoder(message));
        }

        /**
         * Similar to the method above, but deserializes from a |ByteBuffer| instance.
         *
         * @throws org.chromium.mojo.bindings.DeserializationException on deserialization failure.
         */
        public static ServiceOnBindInterfaceParams deserialize(java.nio.ByteBuffer data) {
            return deserialize(new org.chromium.mojo.bindings.Message(
                    data, new java.util.ArrayList<org.chromium.mojo.system.Handle>()));
        }

        @SuppressWarnings("unchecked")
        public static ServiceOnBindInterfaceParams decode(org.chromium.mojo.bindings.Decoder decoder0) {
            if (decoder0 == null) {
                return null;
            }
            decoder0.increaseStackDepth();
            ServiceOnBindInterfaceParams result;
            try {
                org.chromium.mojo.bindings.DataHeader mainDataHeader = decoder0.readAndValidateDataHeader(VERSION_ARRAY);
                final int elementsOrVersion = mainDataHeader.elementsOrVersion;
                result = new ServiceOnBindInterfaceParams(elementsOrVersion);
                    {
                        
                    org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(8, false);
                    result.source = BindSourceInfo.decode(decoder1);
                    }
                    {
                        
                    result.interfaceName = decoder0.readString(16, false);
                    }
                    {
                        
                    result.interfacePipe = decoder0.readMessagePipeHandle(24, false);
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
            
            encoder0.encode(this.source, 8, false);
            
            encoder0.encode(this.interfaceName, 16, false);
            
            encoder0.encode(this.interfacePipe, 24, false);
        }
    }



    
    static final class ServiceOnBindInterfaceResponseParams extends org.chromium.mojo.bindings.Struct {

        private static final int STRUCT_SIZE = 8;
        private static final org.chromium.mojo.bindings.DataHeader[] VERSION_ARRAY = new org.chromium.mojo.bindings.DataHeader[] {new org.chromium.mojo.bindings.DataHeader(8, 0)};
        private static final org.chromium.mojo.bindings.DataHeader DEFAULT_STRUCT_INFO = VERSION_ARRAY[0];

        private ServiceOnBindInterfaceResponseParams(int version) {
            super(STRUCT_SIZE, version);
        }

        public ServiceOnBindInterfaceResponseParams() {
            this(0);
        }

        public static ServiceOnBindInterfaceResponseParams deserialize(org.chromium.mojo.bindings.Message message) {
            return decode(new org.chromium.mojo.bindings.Decoder(message));
        }

        /**
         * Similar to the method above, but deserializes from a |ByteBuffer| instance.
         *
         * @throws org.chromium.mojo.bindings.DeserializationException on deserialization failure.
         */
        public static ServiceOnBindInterfaceResponseParams deserialize(java.nio.ByteBuffer data) {
            return deserialize(new org.chromium.mojo.bindings.Message(
                    data, new java.util.ArrayList<org.chromium.mojo.system.Handle>()));
        }

        @SuppressWarnings("unchecked")
        public static ServiceOnBindInterfaceResponseParams decode(org.chromium.mojo.bindings.Decoder decoder0) {
            if (decoder0 == null) {
                return null;
            }
            decoder0.increaseStackDepth();
            ServiceOnBindInterfaceResponseParams result;
            try {
                org.chromium.mojo.bindings.DataHeader mainDataHeader = decoder0.readAndValidateDataHeader(VERSION_ARRAY);
                final int elementsOrVersion = mainDataHeader.elementsOrVersion;
                result = new ServiceOnBindInterfaceResponseParams(elementsOrVersion);

            } finally {
                decoder0.decreaseStackDepth();
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected final void encode(org.chromium.mojo.bindings.Encoder encoder) {
            encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO);
        }
    }

    static class ServiceOnBindInterfaceResponseParamsForwardToCallback extends org.chromium.mojo.bindings.SideEffectFreeCloseable
            implements org.chromium.mojo.bindings.MessageReceiver {
        private final Service.OnBindInterfaceResponse mCallback;

        ServiceOnBindInterfaceResponseParamsForwardToCallback(Service.OnBindInterfaceResponse callback) {
            this.mCallback = callback;
        }

        @Override
        public boolean accept(org.chromium.mojo.bindings.Message message) {
            try {
                org.chromium.mojo.bindings.ServiceMessage messageWithHeader =
                        message.asServiceMessage();
                org.chromium.mojo.bindings.MessageHeader header = messageWithHeader.getHeader();
                if (!header.validateHeader(ON_BIND_INTERFACE_ORDINAL,
                                           org.chromium.mojo.bindings.MessageHeader.MESSAGE_IS_RESPONSE_FLAG)) {
                    return false;
                }

                mCallback.call();
                return true;
            } catch (org.chromium.mojo.bindings.DeserializationException e) {
                return false;
            }
        }
    }

    static class ServiceOnBindInterfaceResponseParamsProxyToResponder implements Service.OnBindInterfaceResponse {

        private final org.chromium.mojo.system.Core mCore;
        private final org.chromium.mojo.bindings.MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceOnBindInterfaceResponseParamsProxyToResponder(
                org.chromium.mojo.system.Core core,
                org.chromium.mojo.bindings.MessageReceiver messageReceiver,
                long requestId) {
            mCore = core;
            mMessageReceiver = messageReceiver;
            mRequestId = requestId;
        }

        @Override
        public void call() {
            ServiceOnBindInterfaceResponseParams _response = new ServiceOnBindInterfaceResponseParams();

            org.chromium.mojo.bindings.ServiceMessage _message =
                    _response.serializeWithHeader(
                            mCore,
                            new org.chromium.mojo.bindings.MessageHeader(
                                    ON_BIND_INTERFACE_ORDINAL,
                                    org.chromium.mojo.bindings.MessageHeader.MESSAGE_IS_RESPONSE_FLAG,
                                    mRequestId));
            mMessageReceiver.accept(_message);
        }
    }



    
    static final class ServiceCreatePackagedServiceInstanceParams extends org.chromium.mojo.bindings.Struct {

        private static final int STRUCT_SIZE = 32;
        private static final org.chromium.mojo.bindings.DataHeader[] VERSION_ARRAY = new org.chromium.mojo.bindings.DataHeader[] {new org.chromium.mojo.bindings.DataHeader(32, 0)};
        private static final org.chromium.mojo.bindings.DataHeader DEFAULT_STRUCT_INFO = VERSION_ARRAY[0];
        public Identity identity;
        public org.chromium.mojo.bindings.InterfaceRequest<Service> receiver;
        public ProcessMetadata metadata;

        private ServiceCreatePackagedServiceInstanceParams(int version) {
            super(STRUCT_SIZE, version);
        }

        public ServiceCreatePackagedServiceInstanceParams() {
            this(0);
        }

        public static ServiceCreatePackagedServiceInstanceParams deserialize(org.chromium.mojo.bindings.Message message) {
            return decode(new org.chromium.mojo.bindings.Decoder(message));
        }

        /**
         * Similar to the method above, but deserializes from a |ByteBuffer| instance.
         *
         * @throws org.chromium.mojo.bindings.DeserializationException on deserialization failure.
         */
        public static ServiceCreatePackagedServiceInstanceParams deserialize(java.nio.ByteBuffer data) {
            return deserialize(new org.chromium.mojo.bindings.Message(
                    data, new java.util.ArrayList<org.chromium.mojo.system.Handle>()));
        }

        @SuppressWarnings("unchecked")
        public static ServiceCreatePackagedServiceInstanceParams decode(org.chromium.mojo.bindings.Decoder decoder0) {
            if (decoder0 == null) {
                return null;
            }
            decoder0.increaseStackDepth();
            ServiceCreatePackagedServiceInstanceParams result;
            try {
                org.chromium.mojo.bindings.DataHeader mainDataHeader = decoder0.readAndValidateDataHeader(VERSION_ARRAY);
                final int elementsOrVersion = mainDataHeader.elementsOrVersion;
                result = new ServiceCreatePackagedServiceInstanceParams(elementsOrVersion);
                    {
                        
                    org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(8, false);
                    result.identity = Identity.decode(decoder1);
                    }
                    {
                        
                    result.receiver = decoder0.readInterfaceRequest(16, false);
                    }
                    {
                        
                    result.metadata = decoder0.readServiceInterface(20, false, ProcessMetadata.MANAGER);
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
            
            encoder0.encode(this.identity, 8, false);
            
            encoder0.encode(this.receiver, 16, false);
            
            encoder0.encode(this.metadata, 20, false, ProcessMetadata.MANAGER);
        }
    }



}
