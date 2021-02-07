package com.lvyang.order_service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.common_utils.jwtutil.JwtUtils;
import com.lvyang.order_service.entity.PayOrder;
import com.lvyang.order_service.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author lvyang
 * @since 2021-02-01
 */
@RestController
@RequestMapping("/order_service/pay-order")
//@CrossOrigin
public class PayOrderController {
    @Autowired
    private PayOrderService payOrderService;

    @PostMapping("createOrder/{courseId}")
    public JsonResultUnity createOrder(@PathVariable String courseId,HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)) {
            return JsonResultUnity.error().code(28004).message("请登录");
        }
        String orderNumber = payOrderService.createOrders(courseId, memberId);
        return JsonResultUnity.correct().data("orderId",orderNumber);
    }

    //根据订单ID查询订单信息
    @GetMapping("getOrderInfo/{orderId}")
    public JsonResultUnity getOrderInfo(@PathVariable String orderId) {
        QueryWrapper<PayOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderId);
        PayOrder order = payOrderService.getOne(wrapper);
        return JsonResultUnity.correct().data("orderItem", order);
    }
}

