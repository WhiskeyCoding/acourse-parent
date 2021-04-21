package com.lvyang.order_service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.common_utils.jwtutil.JwtUtils;
import com.lvyang.order_service.entity.PayOrder;
import com.lvyang.order_service.entity.vo.PayOrderInfoVO;
import com.lvyang.order_service.service.PayOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @Resource
    private PayOrderService payOrderService;

    @PostMapping("createOrder/{courseId}")
    public JsonResultUnity createOrder(@PathVariable String courseId, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)) {
            return JsonResultUnity.error().code(28004).message("请登录");
        }
        String orderNumber = payOrderService.createOrders(courseId, memberId);
        return JsonResultUnity.correct().data("orderId", orderNumber);
    }

    //根据订单ID查询订单信息
    @GetMapping("getOrderInfo/{orderId}")
    public JsonResultUnity getOrderInfo(@PathVariable String orderId) {
        QueryWrapper<PayOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderId);
        PayOrder order = payOrderService.getOne(wrapper);
        return JsonResultUnity.correct().data("orderItem", order);
    }

    //根据MemberID分页查询订单信息
    @PostMapping("getMemberOrderById/{current}/{limit}/{memberId}")
    public JsonResultUnity getMemberOrderById(@PathVariable long current, @PathVariable long limit, @PathVariable String memberId) {
        QueryWrapper<PayOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id", memberId);
        Page<PayOrder> courseOrderPage = new Page<>(current, limit);
        payOrderService.page(courseOrderPage, wrapper);
        long total = courseOrderPage.getTotal();
        List<PayOrder> records = courseOrderPage.getRecords();
        return JsonResultUnity.correct().data("total", total).data("records", records);
    }

    @ApiOperation(value = "查询所有订单信息")
    @GetMapping("retrieveAllOrderData")
    public JsonResultUnity retrieveAllOrderData() {
        List<PayOrder> list = payOrderService.list(null);
        return JsonResultUnity.correct().data("allCourseOrderData", list);
    }

    @ApiOperation(value = "分页查询Order列表")
    @GetMapping("paginationQueryToOrder/{current}/{limit}")
    public JsonResultUnity paginationQueryToOrder(@PathVariable long current, @PathVariable long limit) {
        Page<PayOrder> courseOrderPage = new Page<>(current, limit);
        payOrderService.page(courseOrderPage, null);
        long total = courseOrderPage.getTotal();
        List<PayOrder> records = courseOrderPage.getRecords();
        return JsonResultUnity.correct().data("total", total).data("records", records);
    }


    /**
     * 根据订单ID获取订单信息并返回订单对象供远程调用
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据订单ID获取订单信息并返回订单对象")
    @PostMapping("getOrderRemoteObjectById/{id}")
    public PayOrderInfoVO getOrderRemoteObjectById(@PathVariable String id) {
        PayOrder payOrder = payOrderService.getById(id);
        PayOrderInfoVO payOrderInfoVO = new PayOrderInfoVO();
        BeanUtils.copyProperties(payOrder, payOrderInfoVO);
        return payOrderInfoVO;
    }

    /**
     * 根据订单编号逻辑删除
     */
    @ApiOperation(value = "根据订单ID逻辑删除")
    @DeleteMapping("deleteOrderLogicallyById/{id}")
    public JsonResultUnity deleteOrderLogicallyById(@PathVariable String id) {
        PayOrder order = payOrderService.getById(id);
        if (order.getIsDeleted() == false) {
            payOrderService.removeById(id);
            return JsonResultUnity.correct();
        } else {
            return JsonResultUnity.error();
        }
    }
    /**
     * 根据课程ID和个人ID查询是否存在订单记录
     */
    @ApiOperation(value = "根据课程ID和个人ID查询是否存在订单记录")
    @PostMapping("checkCourseWithMemberPermission/{courseId}")
    public JsonResultUnity checkCourseWithMemberPermission (@PathVariable String courseId,HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        QueryWrapper<PayOrder> orderwrapper = new QueryWrapper<>();
        int count = 0;
        if ((memberId != null)&(courseId != null)){
            orderwrapper.eq("member_id", memberId);
            orderwrapper.eq("course_id", courseId);
            count = payOrderService.count(orderwrapper);
        }
        if (count > 0) {
            return JsonResultUnity.correct().data("checkKey", 1);
        } else {
            return JsonResultUnity.correct().data("checkKey", 0);
        }
    }
}

