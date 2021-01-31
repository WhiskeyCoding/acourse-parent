package com.lvyang.vod_service.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.service_base.exceptionhandler.ACourseException;
import com.lvyang.vod_service.service.VodService;
import com.lvyang.vod_service.utils.ConstantVodUtils;
import com.lvyang.vod_service.utils.InitVodClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/19 1:53
 * @Version: 1.0
 */
@Api(tags = {"阿里云Vod上传管理"})
@RestController
@CrossOrigin
@RequestMapping("/vod_service/vodFile")
public class VodController {
    @Autowired
    private VodService vodService;

    /**
     * 上传视频到阿里云
     *
     * @param file 视频文件
     * @return
     */
    @PostMapping("uploadAliyunVideo")
    public JsonResultUnity uploadAliyunVideo(MultipartFile file) {
        String videoId = vodService.uploadVideoToAliyun(file);
        return JsonResultUnity.correct().data("videoId", videoId);
    }

    /**
     * 根据课程Id删除阿里云视频
     *
     * @param videoId 视频Id
     * @return
     */
    @DeleteMapping("removeAliyunVideo/{videoId}")
    public JsonResultUnity removeAliyunVideo(@PathVariable String videoId) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(videoId);
            client.getAcsResponse(request);
            return JsonResultUnity.correct();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ACourseException(20001, "删除视频失败");
        }
    }

    /**
     * 删除多个阿里云视频的方法
     *
     * @param videoIdList 参数多个视频id
     * @return JsonResultUnity.correct()
     */
    @DeleteMapping("deleteAliyunVideo_Batch")
    public JsonResultUnity deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return JsonResultUnity.correct();
    }

    /**
     * 根据视频Id获取视频的阿里云Id
     * @param videoId
     * @return
     */
    @GetMapping("getPlayAuth/{videoId}")
    public JsonResultUnity getPlayAuth(@PathVariable String videoId) {
        try {
            //创建初始化对象
            DefaultAcsClient AliyunVodClient = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建获取凭证的Request对象和Response对象
            GetVideoPlayAuthRequest getVideoPlayAuthRequest = new GetVideoPlayAuthRequest();
            //向request对象里设置视频Id
            getVideoPlayAuthRequest.setVideoId(videoId);
            //调用方法得到凭证
            GetVideoPlayAuthResponse acsResponse = AliyunVodClient.getAcsResponse(getVideoPlayAuthRequest);
            String playAuth = acsResponse.getPlayAuth();
            return JsonResultUnity.correct().data("playAuth", playAuth);
        } catch (Exception e) {
            throw new ACourseException(20001, "获取视频播放凭证失败");
        }
    }
}
