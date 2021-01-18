package com.lvyang.msm_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/1/19 0:09
 * @Version: 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lvyang"})
@EnableDiscoveryClient
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }
}
