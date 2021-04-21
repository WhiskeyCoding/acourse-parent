package com.lvyang.edu_service.entity.cchapter;

import lombok.Data;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/11 20:04
 * @Version: 1.0
 */
@Data
public class TrainingVO {
    private String id;
    private String title;
    private String videoSourceId;//视频Id
    private Boolean isFree;
}
