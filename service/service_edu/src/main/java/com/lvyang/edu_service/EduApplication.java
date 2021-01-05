package com.lvyang.edu_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/11/25 9:12
 * @Version: 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lvyang"})
@EnableDiscoveryClient//nacos服务中心注册
@EnableFeignClients//Feign服务调用
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
