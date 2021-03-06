package com.lvyang.oss_service.controller;

import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.oss_service.service.OssService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/5 14:56
 * @Version: 1.0
 */
@Api(tags = {"阿里云OSS上传管理"})
@RestController
//@CrossOrigin
@RequestMapping("/oss_service/ossFile")
public class OssController {
    @Resource
    private OssService ossService;

    //上传头像的方法
    @PostMapping
    public JsonResultUnity uploadOssFile(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        return JsonResultUnity.correct().data("url",url);
    }
}
