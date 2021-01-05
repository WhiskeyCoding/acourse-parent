package com.lvyang.service_base.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/11/25 19:30
 * @Version: 1.0
 */
@Data
@AllArgsConstructor //生成有参数构造方法
@NoArgsConstructor  //生成无参数构造方法
public class ACourseException extends RuntimeException{
    @ApiModelProperty(value = "状态码")
    private Integer code;//状态码
    private String msg;//异常信息
}
