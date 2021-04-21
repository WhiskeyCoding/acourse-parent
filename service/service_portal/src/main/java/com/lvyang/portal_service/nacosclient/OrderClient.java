package com.lvyang.portal_service.nacosclient;

import com.lvyang.portal_service.nacosclient.impl.OrderFileDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/4/16 12:29
 * @Version: 1.0
 */
@FeignClient(name="service-order",fallback = OrderFileDegradeFeignClient.class)
@Component
public interface OrderClient {

}
