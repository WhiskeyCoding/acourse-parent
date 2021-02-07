package com.lvyang.edu_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Whiskey
 * @Author: lvyang
 * @Description:
 * @Date: 2020/11/25 9:12
 * @Version: 1.0
 */

/*这个扫描的是Swagger2，所以需要配置，扫包的范围，com.lvyang下所有的类都会被注入到SpringMVC容器中。在@SpringBootApplication中已经有
相关范围无需配置*/
@ComponentScan(basePackages = {"com.lvyang"})
/*Nacos服务中心注册*/
@EnableDiscoveryClient
/*Feign服务调用*/
@EnableFeignClients
/*SpringBoot启动，SpringBootApplication中也有@ComponentScan，扫包范围com.lvyang.edu_service*/
@SpringBootApplication
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
