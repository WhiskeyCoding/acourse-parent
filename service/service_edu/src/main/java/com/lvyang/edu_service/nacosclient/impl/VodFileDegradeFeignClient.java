package com.lvyang.edu_service.nacosclient.impl;

import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.edu_service.nacosclient.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/19 16:15
 * @Version: 1.0
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    /**
     * 出错之后执行
     * @param videoId
     * @return
     */
    @Override
    public JsonResultUnity removeAliyunVideo(String videoId) {
        return JsonResultUnity.error().message("根据ID删除视频出错");
    }

    /**
     * 出错之后执行
     * @param videoIdList
     * @return
     */
    @Override
    public JsonResultUnity deleteBatch(List<String> videoIdList) {
        return JsonResultUnity.error().message("根据ID列表删除多个视频出错");
    }
}
