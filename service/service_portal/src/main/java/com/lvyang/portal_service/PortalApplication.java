package com.lvyang.portal_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/1/20 1:12
 * @Version: 1.0
 */
@SpringBootApplication//取消数据源自动配置
@ComponentScan(basePackages = {"com.lvyang"})
@EnableDiscoveryClient//nacos服务中心注册
@EnableFeignClients//Feign服务调用
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class,args);
    }
}
