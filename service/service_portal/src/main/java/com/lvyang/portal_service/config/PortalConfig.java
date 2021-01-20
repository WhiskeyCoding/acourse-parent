package com.lvyang.portal_service.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/1/20 1:34
 * @Version: 1.0
 */
@Configuration
@MapperScan("com.lvyang.portal_service.mapper")
public class PortalConfig {
}
