package com.tosang.grpc.boot;

import com.tosang.grpc.BootStrap;
import com.tosang.grpc.dao.MongoDAO;
import com.tosang.grpc.service.IndexServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author: tosang
 * @Date: 2020/8/14 19:19
 */

@Component
public class RPCBoot {


    @Resource
    IndexServiceImpl indexService;

    private Server server;

    /**
     * 服务启动类
     *
     * @param port 端口
     * @throws IOException
     */
    public void start(int port) throws IOException {

        server = ServerBuilder.forPort(port)
                //注册服务
                .addService(indexService)
                .build()
                .start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** JVM 关闭,导致gRPC服务关闭!");
            RPCBoot.this.stop();
            System.err.println("*** 服务关闭");
        }));
    }

    /**
     * RPC 服务关闭
     */
    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * 设置守护进程，即gRPC服务和JVM共生死
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
    /*
    * rpc启动方法
    * */
    public void run(String[] args) throws IOException, InterruptedException {


        final RPCBoot server = new RPCBoot();
        server.start(50051);
        server.blockUntilShutdown();

    }

}
