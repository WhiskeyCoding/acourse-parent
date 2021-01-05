package com.lvyang.service_base.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/5 11:34
 * @Version: 1.0
 */
@Data
@AllArgsConstructor //生成有参数构造方法
@NoArgsConstructor  //生成无参数构造方法
public class CreateTeacherInfoException extends RuntimeException{
    private Integer code;
    private String msg;
}
