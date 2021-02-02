package com.lvyang.order_service.nacosclient.impl;

import com.lvyang.common_utils.voutil.MemberInfoVO;
import com.lvyang.order_service.nacosclient.PortalClient;
import org.springframework.stereotype.Component;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/2/1 18:43
 * @Version: 1.0
 */
@Component
public class PortalFileDegradeFeignClient implements PortalClient {
    @Override
    public MemberInfoVO getMemberInfoForOrder(String memberId) {
        return null;
    }
}
