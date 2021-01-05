package com.lvyang.vod_service.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/19 2:12
 * @Version: 1.0
 */
@Component
public class ConstantVodUtils implements InitializingBean {
    @Value("${aliyun.vod.file.keyid}")
    private String keyid;
    @Value("${aliyun.vod.file.keysecret}")
    private String keysecret;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyid;
        ACCESS_KEY_SECRET = keysecret;

    }
}
