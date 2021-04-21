package com.lvyang.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/2/1 16:47
 * @Version: 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lvyang"})
//@MapperScan("com.lvyang.order_service.mapper")
@EnableDiscoveryClient//nacos服务中心注册,远程调用
@EnableFeignClients//Feign服务调用
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
