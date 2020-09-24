
package com.tosang.grpc;

import com.tosang.grpc.boot.RPCBoot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: tosang
 * @Date: 2020/8/14 19:46
 */

@Component
public class BootStrap implements CommandLineRunner {

    @Resource
    RPCBoot rpcBoot;

    @Override
    public void run(String... args) throws Exception {
        rpcBoot.start(50051);
        rpcBoot.blockUntilShutdown();
    }
}
