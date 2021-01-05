package com.lvyang.edu_service.nacosclient;

import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.edu_service.nacosclient.impl.VodFileDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/19 14:31
 * @Version: 1.0
 */
@FeignClient(name="service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    @DeleteMapping("/vod_service/vodFile/removeAliyunVideo/{videoId}")
    public JsonResultUnity removeAliyunVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping("/vod_service/vodFile/deleteAliyunVideo_Batch")
    public JsonResultUnity deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
