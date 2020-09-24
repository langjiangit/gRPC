package com.tosang.grpc.service;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 * <pre>
 *定义rpc服务接口：共两个服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.0)",
    comments = "Source: service.proto")
public class IndexServiceGrpc {

  private IndexServiceGrpc() {}

  public static final String SERVICE_NAME = "com.tosang.grpc.service.IndexService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tosang.grpc.entity.HelloRequest,
      com.tosang.grpc.entity.IndexResponse> METHOD_SIMPLE_REQUEST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.tosang.grpc.service.IndexService", "simpleRequest"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tosang.grpc.entity.HelloRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tosang.grpc.entity.IndexResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tosang.grpc.entity.HelloRequest,
      com.tosang.grpc.entity.IndexResponse> METHOD_STREAM_REQUEST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "com.tosang.grpc.service.IndexService", "streamRequest"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tosang.grpc.entity.HelloRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tosang.grpc.entity.IndexResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static IndexServiceStub newStub(io.grpc.Channel channel) {
    return new IndexServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static IndexServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new IndexServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static IndexServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new IndexServiceFutureStub(channel);
  }

  /**
   * <pre>
   *定义rpc服务接口：共两个服务
   * </pre>
   */
  public static abstract class IndexServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *简单 RPC ， 客户端使用存根发送请求到服务器并等待响应返回，就像平常的函数调用一样。
     * </pre>
     */
    public void simpleRequest(com.tosang.grpc.entity.HelloRequest request,
        io.grpc.stub.StreamObserver<com.tosang.grpc.entity.IndexResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SIMPLE_REQUEST, responseObserver);
    }

    /**
     * <pre>
     *服务器端流式 RPC ， 客户端发送请求到服务器，拿到一个流去读取返回的消息序列。 客户端读取返回的流，直到里面没有任何消息。
     * </pre>
     */
    public void streamRequest(com.tosang.grpc.entity.HelloRequest request,
        io.grpc.stub.StreamObserver<com.tosang.grpc.entity.IndexResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_STREAM_REQUEST, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_SIMPLE_REQUEST,
            asyncUnaryCall(
              new MethodHandlers<
                com.tosang.grpc.entity.HelloRequest,
                com.tosang.grpc.entity.IndexResponse>(
                  this, METHODID_SIMPLE_REQUEST)))
          .addMethod(
            METHOD_STREAM_REQUEST,
            asyncServerStreamingCall(
              new MethodHandlers<
                com.tosang.grpc.entity.HelloRequest,
                com.tosang.grpc.entity.IndexResponse>(
                  this, METHODID_STREAM_REQUEST)))
          .build();
    }
  }

  /**
   * <pre>
   *定义rpc服务接口：共两个服务
   * </pre>
   */
  public static final class IndexServiceStub extends io.grpc.stub.AbstractStub<IndexServiceStub> {
    private IndexServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private IndexServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IndexServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new IndexServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *简单 RPC ， 客户端使用存根发送请求到服务器并等待响应返回，就像平常的函数调用一样。
     * </pre>
     */
    public void simpleRequest(com.tosang.grpc.entity.HelloRequest request,
        io.grpc.stub.StreamObserver<com.tosang.grpc.entity.IndexResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SIMPLE_REQUEST, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *服务器端流式 RPC ， 客户端发送请求到服务器，拿到一个流去读取返回的消息序列。 客户端读取返回的流，直到里面没有任何消息。
     * </pre>
     */
    public void streamRequest(com.tosang.grpc.entity.HelloRequest request,
        io.grpc.stub.StreamObserver<com.tosang.grpc.entity.IndexResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_STREAM_REQUEST, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *定义rpc服务接口：共两个服务
   * </pre>
   */
  public static final class IndexServiceBlockingStub extends io.grpc.stub.AbstractStub<IndexServiceBlockingStub> {
    private IndexServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private IndexServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IndexServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new IndexServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *简单 RPC ， 客户端使用存根发送请求到服务器并等待响应返回，就像平常的函数调用一样。
     * </pre>
     */
    public com.tosang.grpc.entity.IndexResponse simpleRequest(com.tosang.grpc.entity.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SIMPLE_REQUEST, getCallOptions(), request);
    }

    /**
     * <pre>
     *服务器端流式 RPC ， 客户端发送请求到服务器，拿到一个流去读取返回的消息序列。 客户端读取返回的流，直到里面没有任何消息。
     * </pre>
     */
    public java.util.Iterator<com.tosang.grpc.entity.IndexResponse> streamRequest(
        com.tosang.grpc.entity.HelloRequest request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_STREAM_REQUEST, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *定义rpc服务接口：共两个服务
   * </pre>
   */
  public static final class IndexServiceFutureStub extends io.grpc.stub.AbstractStub<IndexServiceFutureStub> {
    private IndexServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private IndexServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IndexServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new IndexServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *简单 RPC ， 客户端使用存根发送请求到服务器并等待响应返回，就像平常的函数调用一样。
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tosang.grpc.entity.IndexResponse> simpleRequest(
        com.tosang.grpc.entity.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SIMPLE_REQUEST, getCallOptions()), request);
    }
  }

  private static final int METHODID_SIMPLE_REQUEST = 0;
  private static final int METHODID_STREAM_REQUEST = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final IndexServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(IndexServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SIMPLE_REQUEST:
          serviceImpl.simpleRequest((com.tosang.grpc.entity.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.tosang.grpc.entity.IndexResponse>) responseObserver);
          break;
        case METHODID_STREAM_REQUEST:
          serviceImpl.streamRequest((com.tosang.grpc.entity.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.tosang.grpc.entity.IndexResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_SIMPLE_REQUEST,
        METHOD_STREAM_REQUEST);
  }

}
