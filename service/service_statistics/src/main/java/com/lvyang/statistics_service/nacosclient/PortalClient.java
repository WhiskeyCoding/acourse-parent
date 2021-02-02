package com.lvyang.statistics_service.nacosclient;

import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.statistics_service.nacosclient.impl.PortalFileDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/2/2 12:22
 * @Version: 1.0
 */
@FeignClient(name="service-portal",fallback = PortalFileDegradeFeignClient.class)
@Component
public interface PortalClient {
    @GetMapping("/portal_service/portal_member/countRegisteredMember/{dayId}")
    public JsonResultUnity countRegisteredMember(@PathVariable String dayId);
}
