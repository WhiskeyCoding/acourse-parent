package com.lvyang.edu_service.nacosclient;

import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.common_utils.voutil.MemberInfoVO;
import com.lvyang.edu_service.nacosclient.impl.PortalFileDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/1/31 18:20
 * @Version: 1.0
 */
@FeignClient(name="service-portal",fallback = PortalFileDegradeFeignClient.class)
@Component
public interface PortalClient {
    //根据用户id获取用户信息
    @PostMapping("/portal_service/portal_member/getInfo/{Id}")
    public JsonResultUnity getInfo(@PathVariable("Id") String id);

    //供其他服务调用的据用户id获取用户信息
    @PostMapping("/portal_service/portal_member/getMemberInfo/{Id}")
    public MemberInfoVO getMemberInfo(@PathVariable String Id);
}
