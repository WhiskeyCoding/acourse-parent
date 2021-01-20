package com.lvyang.portal_service.controller;


import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.common_utils.jwtutil.JwtUtils;
import com.lvyang.portal_service.entity.vo.LoginInfoVO;
import com.lvyang.portal_service.entity.vo.LoginVO;
import com.lvyang.portal_service.entity.vo.RegisterVO;
import com.lvyang.portal_service.service.PortalMemberService;
import com.lvyang.service_base.exceptionhandler.ACourseException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author lvyang
 * @since 2021-01-20
 */
@RestController
@RequestMapping("/portal_service/portal_member")
@CrossOrigin
public class PortalMemberController {
    final PortalMemberService portalMemberService;
    final RedisTemplate<String, String> redisTemplate;

    @Autowired
    private PortalMemberController(PortalMemberService portalMemberService, RedisTemplate<String, String> redisTemplate) {
        this.portalMemberService = portalMemberService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 1.会员注册
     */
    @ApiOperation(value = "注册")
    @PostMapping("registerAcourseMember")
    public JsonResultUnity registerAcourseMember(@RequestBody RegisterVO registerVO) {
        portalMemberService.registerMember(registerVO);
        return JsonResultUnity.correct();
    }

    /**
     * 2.会员登录
     */
    @ApiOperation(value = "登录")
    @PostMapping("loginAcourseMember")
    public JsonResultUnity loginAcourseMember(@RequestBody LoginVO loginVO) {
        //调用Service方法实现登录，返回根据JWT规则生成的token
        String loginToken = portalMemberService.loginMember(loginVO);
        return JsonResultUnity.correct().data("token", loginToken);
    }

    /**
     * 3.根据Token字符串获取用户Id
     */
    @ApiOperation(value = "获取用户信息")
    @GetMapping("auth/getLoginInfo")
    public JsonResultUnity getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            LoginInfoVO loginInfo = portalMemberService.getLoginInfo(memberId);
            return JsonResultUnity.correct().data("loginInfo", loginInfo);
        }catch (Exception e){
            e.printStackTrace();
            throw new ACourseException(20001,"获取登录信息失败");
        }
    }
}

