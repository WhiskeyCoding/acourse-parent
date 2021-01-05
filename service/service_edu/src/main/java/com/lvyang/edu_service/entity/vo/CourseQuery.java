package com.lvyang.edu_service.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/16 20:36
 * @Version: 1.0
 */
@Data
public class CourseQuery {
    @ApiModelProperty(value="课程名称，模糊查询")
    private String title;
    @ApiModelProperty(value="状态")
    private String status;
}
