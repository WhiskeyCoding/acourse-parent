package com.lvyang.common_utils.voutil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/1/31 18:06
 * @Version: 1.0
 */
@Data
@ApiModel(value="Member对象信息", description="Member对象信息")
public class MemberInfoVO {
    @ApiModelProperty(value = "会员id")
    private String id;

    @ApiModelProperty(value = "用户名昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "性别 1 女，2 男")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户签名")
    private String sign;

}
