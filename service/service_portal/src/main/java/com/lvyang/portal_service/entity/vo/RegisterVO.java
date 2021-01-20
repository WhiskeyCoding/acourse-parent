package com.lvyang.portal_service.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/1/20 1:17
 * @Version: 1.0
 */
@Data
@ApiModel(value="注册对象", description="注册对象")
public class RegisterVO {
    @ApiModelProperty(value = "用户名昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;
}
