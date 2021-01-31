package com.lvyang.edu_service.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/1/31 21:05
 * @Version: 1.0
 */
@ApiModel(value = "评论信息", description = "课程评论信息")
@Data
public class CommentQueryVO  {

    @ApiModelProperty(value = "课程id")
    private String courseId;
}
