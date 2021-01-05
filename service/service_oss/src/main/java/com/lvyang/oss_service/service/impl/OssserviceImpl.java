package com.lvyang.oss_service.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.lvyang.oss_service.service.OssService;
import com.lvyang.oss_service.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/5 14:54
 * @Version: 1.0
 */
@Service
public class OssserviceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try{
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();

            //1在文件中生成唯一值,防止文件被覆盖
            String ossFileId = UUID.randomUUID().toString().replace("-", "");
            //2文件分类
            String ossFileTime = new DateTime().toString("yyyy/MM/dd");
            String ossFileName = "file_"+ossFileTime+"_"+ossFileId+"_"+originalFilename;
            //oss参数，第一个，bucket名字，第二个，文件的原始名字，第三个文件流
            ossClient.putObject(bucketName, ossFileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();


            //上传文件路径返回
            String urlOfOssFile = "https://"+bucketName+"."+endpoint+"/"+ossFileName;
            return urlOfOssFile;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
