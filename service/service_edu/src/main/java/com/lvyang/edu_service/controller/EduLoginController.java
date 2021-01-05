package com.lvyang.edu_service.controller;

import com.lvyang.common_utils.JsonResultUnity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/3 17:15
 * @Version: 1.0
 */
@Api(tags = {"Vue-admin登录"})
@RestController
@RequestMapping("/edu_service/user")
@CrossOrigin
public class EduLoginController {
    //login
    @PostMapping("login")
    public JsonResultUnity login(){
        return JsonResultUnity.correct().data("token","admin");
    }

    //info
    @GetMapping("info")
    public JsonResultUnity info(){
        return JsonResultUnity.correct().data("roles","[admin]").data("name","admin").data("avatar","http://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
