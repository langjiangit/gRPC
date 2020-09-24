package com.tosang.grpc.client;

import com.sun.javafx.binding.StringFormatter;
import com.tosang.grpc.entity.HelloRequest;
import com.tosang.grpc.entity.IndexResponse;
import com.tosang.grpc.service.IndexServiceGrpc;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: tosang
 * @Date: 2020/8/13 19:41
 */


public class IndexClient {

    private final IndexServiceGrpc.IndexServiceBlockingStub indexServiceBlockingStub;

    private final BaseClient client;

    public IndexClient(BaseClient client) {
        this.client = client;
        this.indexServiceBlockingStub = client.getIndexServiceBlockingStub();
    }

    public IndexResponse simpleRequest(String message) {

        /*
        * 简单rpc：一次性返回
        * */
        HelloRequest helloRequest = HelloRequest.getDefaultInstance()
                .toBuilder()
                .setMessage(message)
                .build();
        /*
        * 简单rpc：返回对象或集合
        * */
        IndexResponse indexResponse = this.indexServiceBlockingStub.simpleRequest(helloRequest);

        return indexResponse;

    }

    public Iterator<IndexResponse> streamRequest(String message) {
        /*
         * 流式rpc：以流的形式推送
         * */
        HelloRequest helloRequest = HelloRequest.getDefaultInstance()
                .toBuilder()
                .setMessage(message)
                .build();

        /*
        * 流式rpc：返回迭代器对象
        * */
        Iterator<IndexResponse> indexResponseIterator = this.indexServiceBlockingStub.streamRequest(helloRequest);

        return indexResponseIterator;
    }
}
