package com.tosang.grpc.service;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.tosang.grpc.dao.MongoDAO;
import com.tosang.grpc.entity.Index;
import com.tosang.grpc.entity.IndexObject;
import com.tosang.grpc.entity.IndexResponse;
import com.tosang.grpc.service.IndexServiceGrpc;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.RegEx;
import javax.annotation.Resource;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @Author: tosang
 * @Date: 2020/8/13 17:01
 */

@Service
public class IndexServiceImpl extends IndexServiceGrpc.IndexServiceImplBase {

    @Resource
    MongoDAO mongoDAO;

    //简单请求
    @Override
    public void simpleRequest(com.tosang.grpc.entity.HelloRequest request,
                              io.grpc.stub.StreamObserver<com.tosang.grpc.entity.IndexResponse> responseObserver) {
        System.out.println("服务端收到请求消息："+request.getMessage());
        // ClassPathResource类的构造方法接收路径名称，自动去classpath路径下找文件
        ClassPathResource classPathResource = new ClassPathResource("test.json");

        // 获得File对象，当然也可以获取输入流对象
        File file = classPathResource.getFile();

        //转为json数组
        JSONArray objects = JSONUtil.readJSONArray(file, StandardCharsets.UTF_8);
        List<Index> list = new ArrayList<>();
        //简单RPC一次性发送所以数据
        for (Map item : objects.toList(Map.class)) {
            Index index = Index.getDefaultInstance().toBuilder()
                    .setId((String) item.get("_id"))
                    .setSecuritiesCode((String) item.get("证券代码"))
                    .setISIN((String) item.get("ISIN代码"))
                    .setNameOfSecuritiesInChinese((String) item.get("中文证券名称（短）"))
                    .setST((boolean) item.get("ST(连续一年亏损)"))
                    .setStarST((boolean) item.get("*ST(连续两年亏损)"))
                    .setNameOfSecuritiesInEnglish((String) item.get("英文证券名称"))
                    .setUnderlyingSecurityCode((String) item.get("基础证券代码"))
                    .setTypesOfSecurities((String) item.get("证券类别"))
                    .setLastTransactionDate((String) item.get("最后交易日期"))
                    .setListingDate((String) item.get("上市日期"))
                    .setExRight((String) item.get("除权"))
                    .setExDividend((String) item.get("除息"))
                    .setPriceRange((String) item.get("价格档位"))
                    .setLimitTypes((String) item.get("涨跌幅限制类型"))
                    .setCeilingPrice((String) item.get("涨幅上限价格"))
                    .setLowerLimitPrice((String) item.get("跌幅下限价格"))
                    .build();
            list.add(index);
        }

        //处理响应参数 返回状态信息
        IndexResponse response = IndexResponse.getDefaultInstance().toBuilder()
                .setCode(200)
                .setMsg("simple request success!")
                .addAllIndex(list)
                .build();
        responseObserver.onNext(response);
        System.out.println("所有数据包发送完成");
        responseObserver.onCompleted();
    }



    //流式请求
    /*@Override
    public void streamRequest(com.tosang.grpc.entity.HelloRequest request,
                              io.grpc.stub.StreamObserver<com.tosang.grpc.entity.IndexResponse> responseObserver) {
        System.out.println("服务端收到请求消息："+request.getMessage());
        // ClassPathResource类的构造方法接收路径名称，自动去classpath路径下找文件
        ClassPathResource classPathResource = new ClassPathResource("test.json");

        // 获得File对象，当然也可以获取输入流对象
        File file = classPathResource.getFile();

        //转为json数组
        JSONArray objects = JSONUtil.readJSONArray(file, StandardCharsets.UTF_8);

        //json数组转index类型
        // 用for循环添加并推送数据
        int count=0;//计数
        for (Map item : objects.toList(Map.class)) {
            //用list添加数据，list可以包含0个或多个数据，这里每次只添加一个数据
            List<Index> list = new ArrayList<>();
            Index index = Index.getDefaultInstance().toBuilder()
                    .setId((String)item.get("_id"))
                    .setSecuritiesCode((String)item.get("证券代码"))
                    .setISIN((String)item.get("ISIN代码"))
                    .setNameOfSecuritiesInChinese((String)item.get("中文证券名称（短）"))
                    .setST((boolean)item.get("ST(连续一年亏损)"))
                    .setStarST((boolean)item.get("*ST(连续两年亏损)"))
                    .setNameOfSecuritiesInEnglish((String)item.get("英文证券名称"))
                    .setUnderlyingSecurityCode((String)item.get("基础证券代码"))
                    .setTypesOfSecurities((String)item.get("证券类别"))
                    .setLastTransactionDate((String)item.get("最后交易日期"))
                    .setListingDate((String)item.get("上市日期"))
                    .setExRight((String)item.get("除权"))
                    .setExDividend((String)item.get("除息"))
                    .setPriceRange((String)item.get("价格档位"))
                    .setLimitTypes((String)item.get("涨跌幅限制类型"))
                    .setCeilingPrice((String)item.get("涨幅上限价格"))
                    .setLowerLimitPrice((String)item.get("跌幅下限价格"))
                    .build();
            count ++;
            list.add(index);
            //封装响应参数并返回
            IndexResponse response = IndexResponse.getDefaultInstance().toBuilder()
                    .setCode(2000)
                    .setMsg("stream request success!")
                    .addAllIndex(list)
                    .build();
            responseObserver.onNext(response);
            System.out.println("第"+ count  +"个数据包发送完成");
            ThreadUtil.sleep(5, TimeUnit.SECONDS);

        }
        responseObserver.onCompleted();
    }*/

    @Override
    public void streamRequest(com.tosang.grpc.entity.HelloRequest request,
                              io.grpc.stub.StreamObserver<com.tosang.grpc.entity.IndexResponse> responseObserver) {
        System.out.println("服务端收到请求消息："+request.getMessage());

        // 获取全部数据
        List<IndexObject> allIndex = mongoDAO.getAllIndex();

        //计数器
        int count=0;

        // 用for循环添加并推送数据：将IndexObject对象转为protobuf格式
        for(IndexObject item : allIndex){
            /*
            * 这里有个大坑，Protobuf 3 为每个字段都提供默认值，这使得它不允许null出现，
            * 而json数据中必然有可能存在null，需要进行处理
            * 我暂时没想到好的办法去处理，所有下面用三目运算符处理
            * */
            Index index = Index.getDefaultInstance().toBuilder()
                    .setId(item.getId() != null ? item.getId():"null")
                    .setSecuritiesCode(item.getSecuritiesCode() != null ? item.getSecuritiesCode() : "null")
                    .setISIN(item.getISIN() != null ? item.getISIN() : "null")
                    .setNameOfSecuritiesInChinese(item.getNameOfSecuritiesInChinese() != null ? item.getNameOfSecuritiesInChinese() : "null")
                    .setST(item.isST())
                    .setStarST(item.isStarST())
                    .setNameOfSecuritiesInEnglish(item.getNameOfSecuritiesInEnglish() != null ? item.getNameOfSecuritiesInEnglish() : "null")
                    .setUnderlyingSecurityCode(item.getUnderlyingSecurityCode() != null ? item.getUnderlyingSecurityCode() : "null")
                    .setTypesOfSecurities(item.getTypesOfSecurities() != null ? item.getTypesOfSecurities() : "null")
                    .setLastTransactionDate(item.getLastTransactionDate() != null ? item.getLastTransactionDate() : "null")
                    .setListingDate(item.getListingDate() != null ? item.getListingDate() : "null")
                    .setExRight(item.getExRight() != null ? item.getExRight() : "null")
                    .setExDividend(item.getExDividend() != null ? item.getExDividend() : "null")
                    .setPriceRange(item.getPriceRange() != null ? item.getPriceRange() : "null")
                    .setLimitTypes(item.getLimitTypes() != null ? item.getLimitTypes() : "null")
                    .setCeilingPrice(item.getCeilingPrice() != null ? item.getCeilingPrice() : "null")
                    .setLowerLimitPrice(item.getLowerLimitPrice() != null ? item.getLowerLimitPrice() : "null")
                    .build();
            count ++;
            //封装响应参数并返回
            IndexResponse response = IndexResponse.getDefaultInstance().toBuilder()
                    .setCode(2000)
                    .setMsg("stream request success!")
                    .addIndex(index)
                    .build();
            responseObserver.onNext(response);
            System.out.println("第"+ count  +"个数据包发送完成");
        }
        responseObserver.onCompleted();
    }
}
