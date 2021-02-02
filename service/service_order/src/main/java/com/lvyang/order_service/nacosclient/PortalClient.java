package com.lvyang.order_service.nacosclient;

import com.lvyang.common_utils.voutil.MemberInfoVO;
import com.lvyang.order_service.nacosclient.impl.PortalFileDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/2/1 18:42
 * @Version: 1.0
 */
@FeignClient(name="service-portal",fallback = PortalFileDegradeFeignClient.class)
@Component
public interface PortalClient {
    @PostMapping("/portal_service/portal_member/getMemberInfoForOrder/{memberId}")
    public MemberInfoVO getMemberInfoForOrder(@PathVariable String memberId);
}
