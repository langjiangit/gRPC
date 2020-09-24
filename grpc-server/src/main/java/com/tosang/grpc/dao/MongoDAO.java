package com.tosang.grpc.dao;

import com.tosang.grpc.entity.IndexObject;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: tosang
 * @Date: 2020/8/14 15:31
 */

@Repository
public class MongoDAO {


    @Resource
    private MongoTemplate mongoTemplate;

    /*查询所有数据*/
    @Bean
    public List<IndexObject> getAllIndex(){
        return mongoTemplate.findAll(IndexObject.class);

    }
}
