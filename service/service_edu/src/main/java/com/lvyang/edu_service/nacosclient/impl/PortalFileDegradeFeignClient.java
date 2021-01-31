package com.lvyang.edu_service.nacosclient.impl;

import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.common_utils.voutil.MemberInfoVO;
import com.lvyang.edu_service.nacosclient.PortalClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/1/31 18:21
 * @Version: 1.0
 */
@Component
public class PortalFileDegradeFeignClient implements PortalClient {
    @Override
    public JsonResultUnity getInfo(@PathVariable("Id") String id){return JsonResultUnity.error().message("获取信息失败。");}

    @Override
    public MemberInfoVO getMemberInfo(String Id) {
        return null;
    }
}
