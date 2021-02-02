package com.lvyang.order_service.nacosclient.impl;

import com.lvyang.common_utils.voutil.CourseFrontShowVO;
import com.lvyang.order_service.nacosclient.EduClient;
import org.springframework.stereotype.Component;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/2/1 18:38
 * @Version: 1.0
 */
@Component
public class EduFileDegradeFeignClient implements EduClient {
    @Override
    public CourseFrontShowVO getCourseIdForOrder(String courseId) {
        return null;
    }
}
