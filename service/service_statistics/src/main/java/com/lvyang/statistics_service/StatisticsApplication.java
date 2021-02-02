package com.lvyang.statistics_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/2/2 12:06
 * @Version: 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lvyang"})
@MapperScan("com.lvyang.statistics_service.mapper")
@EnableDiscoveryClient//nacos服务中心注册,远程调用
@EnableFeignClients//Feign服务调用
@EnableScheduling//定时任务
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);
    }
}
