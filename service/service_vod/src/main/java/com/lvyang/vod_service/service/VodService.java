package com.lvyang.vod_service.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/19 1:55
 * @Version: 1.0
 */
public interface VodService {
    String uploadVideoToAliyun(MultipartFile file);


    void removeMoreAlyVideo(List<String> videoIdList);
}
