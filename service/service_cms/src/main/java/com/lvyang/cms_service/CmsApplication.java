package com.lvyang.cms_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/20 12:17
 * @Version: 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lvyang"})
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
