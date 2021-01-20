package com.lvyang.portal_service.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/1/20 1:16
 * @Version: 1.0
 */
@Data
@ApiModel(value="登录对象", description="登录对象")
public class LoginVO {
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;
}
