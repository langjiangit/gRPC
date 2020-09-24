package com.tosang.grpc.client;

import com.tosang.grpc.service.IndexServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Author: tosang
 * @Date: 2020/8/12 21:57
 */
public class BaseClient {

    //ManagedChannel是gRPC对SocketChannel的二次封装
    private final ManagedChannel channel;

    //gRPC有多种Stub，参见 https://blog.csdn.net/huweijian5/article/details/83822470
    //FutureStub如果完成就自动调用回调函数，这样可以减少并发程序的复杂度。
    private final IndexServiceGrpc.IndexServiceBlockingStub indexServiceBlockingStub;

    private BaseClient(ManagedChannel channel) {
        this.channel = channel;
        this.indexServiceBlockingStub = IndexServiceGrpc.newBlockingStub(channel);
    }

    /**
     * 构造客户端与Greeter 服务端连接 {@code host:port}
     *
     * @param host 主机地址
     * @param port 端口
     */
    public BaseClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext(true)
                .build());
    }

    /**
     * 关闭函数，限时5s
     * @throws InterruptedException
     */
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
    /**
     * 返回stub
     */
    public IndexServiceGrpc.IndexServiceBlockingStub getIndexServiceBlockingStub(){
        return  indexServiceBlockingStub;
    }
}
