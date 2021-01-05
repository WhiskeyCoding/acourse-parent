package com.lvyang.edu_service.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/16 0:45
 * @Version: 1.0
 */
@Data
@ApiModel(value = "课程信息发布")
public class CoursePublishVO {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String description;
    private String cover;
    private String price;//只用于显示
}
