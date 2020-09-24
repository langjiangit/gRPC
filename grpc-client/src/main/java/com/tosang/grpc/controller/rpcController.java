package com.tosang.grpc.controller;


import com.google.protobuf.TextFormat;
import com.tosang.grpc.client.BaseClient;
import com.tosang.grpc.client.IndexClient;
import com.tosang.grpc.entity.Index;
import com.tosang.grpc.entity.IndexResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

/**
 * @Author: tosang
 * @Date: 2020/8/13 20:36
 */

@RestController
public class rpcController {

    /*
    * 加载客户端
    * */
    BaseClient client = new BaseClient("localhost", 50051);
    IndexClient indexClient = new IndexClient(client);


    /*
    * 简单RPC
    * */
    @GetMapping("/simpleRequest/{msg}")
    public  String simpleRequest(@PathVariable("msg")String message) throws Exception{

        System.out.println("===============测试客户端:简单rpc============");
        IndexResponse indexResponse = indexClient.simpleRequest(message);
        String s = TextFormat.printToUnicodeString(indexResponse);
        System.out.println("状态码："+ indexResponse.getCode()+", 信息为："+indexResponse.getMsg());
        System.out.println(s);
        return s;
    }
    /*
    * 流式RPC
    * */
    @GetMapping("/streamRequest/{msg}")
    public String streamRequest(@PathVariable("msg")String message) throws Exception{
        System.out.println("===============测试客户端:流式rpc============");
        Iterator<IndexResponse> indexResponseIterator = indexClient.streamRequest(message);
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        while (indexResponseIterator.hasNext()){
            IndexResponse next = indexResponseIterator.next();
            String msg = next.getMsg();
            int code = next.getCode();
            List<Index> indexList = next.getIndexList();
            System.out.println("状态码："+ code+", 信息为："+msg);
            //Protobuf格式对中文支持不友好，需要格式化为Unicode编码字符串才能看到中文
            String s = TextFormat.printToUnicodeString(next);
            count++;
            System.out.println("第"+ count  +"个数据包接收完成");
            System.out.println(s);
            stringBuilder.append(s);
        }

        return stringBuilder.toString();
    }
}
