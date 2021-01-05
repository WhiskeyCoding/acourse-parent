package com.lvyang.oss_service.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/5 14:54
 * @Version: 1.0
 */
public interface OssService {
    //上传头像
    String uploadFileAvatar(MultipartFile file);
}
