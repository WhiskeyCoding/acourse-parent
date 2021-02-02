package com.lvyang.order_service.nacosclient;

import com.lvyang.common_utils.voutil.CourseFrontShowVO;
import com.lvyang.order_service.nacosclient.impl.EduFileDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/2/1 18:38
 * @Version: 1.0
 */
@FeignClient(name="service-edu",fallback = EduFileDegradeFeignClient.class)
@Component
public interface EduClient {
    @PostMapping("/edu_service/coursefront/getCourseIdForOrder/{courseId}")
    public CourseFrontShowVO getCourseIdForOrder(@PathVariable String courseId);
}
