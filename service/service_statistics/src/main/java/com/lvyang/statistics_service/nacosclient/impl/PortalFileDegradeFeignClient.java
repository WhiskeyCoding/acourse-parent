package com.lvyang.statistics_service.nacosclient.impl;

import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.statistics_service.nacosclient.PortalClient;
import org.springframework.stereotype.Component;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/2/2 12:23
 * @Version: 1.0
 */
@Component
public class PortalFileDegradeFeignClient implements PortalClient {
    @Override
    public JsonResultUnity countRegisteredMember(String dayId) {
        return null;
    }
}
