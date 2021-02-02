package com.lvyang.common_utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lvyang
 * @Description: 统一返回状态码
 * @Date: 2020/11/25 10:53
 * @Version: 1.0
 */
@Data
public class JsonResultUnity {
    @ApiModelProperty(value = "成功状态")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    /**链式编程*/
    private JsonResultUnity(){}

    public static JsonResultUnity correct(){
        JsonResultUnity jsonResult = new JsonResultUnity();
        jsonResult.setSuccess(true);
        jsonResult.setCode(ResultCode.SUCCESS);
        jsonResult.setMessage("成功");
        return jsonResult;
    }

    public static JsonResultUnity error(){
        JsonResultUnity jsonResult = new JsonResultUnity();
        jsonResult.setSuccess(false);
        jsonResult.setCode(ResultCode.ERROR);
        jsonResult.setMessage("失败");
        return jsonResult;
    }

    public JsonResultUnity success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public JsonResultUnity message(String message){
        this.setMessage(message);
        return this;
    }

    public JsonResultUnity code(Integer code){
        this.setCode(code);
        return this;
    }

    public JsonResultUnity data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public JsonResultUnity data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
