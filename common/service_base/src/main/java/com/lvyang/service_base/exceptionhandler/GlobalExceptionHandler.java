package com.lvyang.service_base.exceptionhandler;


import com.lvyang.common_utils.ExceptionUtil;
import com.lvyang.common_utils.JsonResultUnity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/11/25 19:05
 * @Version: 1.0
 */
@Slf4j//日志注解
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回数据
    public JsonResultUnity error(Exception e) {
        e.printStackTrace();
        return JsonResultUnity.error().message("执行了全局异常处理。");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public JsonResultUnity error(ArithmeticException e){
        e.printStackTrace();
        return JsonResultUnity.error().message("执行了ArithmeticException特殊异常");
    }

    @ExceptionHandler(CreateTeacherInfoException.class)
    @ResponseBody
    public JsonResultUnity error(CreateTeacherInfoException e){
        e.printStackTrace();
        return JsonResultUnity.error().code(e.getCode()).message(e.getMsg());
    }


    @ExceptionHandler(ACourseException.class)
    @ResponseBody
    public JsonResultUnity error(ACourseException e){
        log.error(ExceptionUtil.getMessage(e));//日志输出到文件
        e.printStackTrace();
        return JsonResultUnity.error().code(e.getCode()).message(e.getMsg());
    }
}
