package com.lvyang.edu_service.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/4/17 12:02
 * @Version: 1.0
 */
@Data
public class NoteListShowVO {
    @ApiModelProperty(value = "笔记Id")
    private String id;

    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "会员Id")
    private String memberId;

    @ApiModelProperty(value = "会员名称")
    private String nickname;

    @ApiModelProperty(value = "笔记主题")
    private String noteTheme;

    @ApiModelProperty(value = "笔记信息")
    private String noteMessage;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;
}
