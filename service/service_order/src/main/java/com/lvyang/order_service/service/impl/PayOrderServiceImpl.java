package com.lvyang.order_service.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvyang.common_utils.voutil.CourseFrontShowVO;
import com.lvyang.common_utils.voutil.MemberInfoVO;
import com.lvyang.order_service.entity.PayOrder;
import com.lvyang.order_service.mapper.PayOrderMapper;
import com.lvyang.order_service.nacosclient.EduClient;
import com.lvyang.order_service.nacosclient.PortalClient;
import com.lvyang.order_service.service.PayOrderService;
import com.lvyang.order_service.utils.OrderNoUtil;
import com.lvyang.service_base.exceptionhandler.ACourseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author lvyang
 * @since 2021-02-01
 */
@Service
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements PayOrderService {

    @Autowired
    private EduClient eduClient;
    @Autowired
    private PortalClient portalClient;

    @Override
    public String createOrders(String courseId, String memberId) {
        //通过nacos远程调用获取用户信息

        //根据用户Id获取
        MemberInfoVO memberInfoForOrder = portalClient.getMemberInfoForOrder(memberId);
        //根据课程Id获取
        CourseFrontShowVO courseInfoForOrder = eduClient.getCourseIdForOrder(courseId);
        //过程
        PayOrder order = new PayOrder();
        //设置订单值
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoForOrder.getTitle());
        order.setCourseCover(courseInfoForOrder.getCover());
        order.setTeacherName(courseInfoForOrder.getTeacherName());
        order.setTotalFee(courseInfoForOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(memberInfoForOrder.getMobile());
        order.setNickname(memberInfoForOrder.getNickname());

        order.setStatus(0);//支付状态
        order.setPayType(1);//支付类型
        baseMapper.insert(order);
        String orderNo = order.getOrderNo();
        return orderNo;
    }

    @Override
    public void removeOrder(String id) {
        int result = baseMapper.deleteById(id);
        if (result == 0) {
            throw new ACourseException(20001, "删除失败");
        }
    }
}
