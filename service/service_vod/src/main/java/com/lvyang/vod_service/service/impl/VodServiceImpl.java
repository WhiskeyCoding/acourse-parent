package com.lvyang.vod_service.service.impl;


import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.lvyang.service_base.exceptionhandler.ACourseException;
import com.lvyang.vod_service.service.VodService;
import com.lvyang.vod_service.utils.ConstantVodUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.lvyang.vod_service.utils.InitVodClient;

import java.io.InputStream;
import java.util.List;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/19 1:55
 * @Version: 1.0
 */
@Service
public class VodServiceImpl implements VodService {



    @Override
    public String uploadVideoToAliyun(MultipartFile file) {
        /**
         * 流式上传接口
         *
         * @param accessKeyId ID
         * @param accessKeySecret 秘钥
         * @param title 上传之后显示的名称
         * @param fileName 上传文件原始名称
         * @param inputStream 上传文件输入流
         */
        try {
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (Exception e) {
            System.out.println("UploadVideoRequest Client Exception:");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeMoreAlyVideo(List<String> videoIdList) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //videoIdList值转换成 1,2,3
            String videoIds = StringUtils.join(videoIdList.toArray(), ",");
            //向request设置视频id
            request.setVideoIds(videoIds);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ACourseException(20001, "删除视频失败");
        }
    }


}
