package com.lvyang.order_service.service.impl;

import com.lvyang.order_service.entity.PayOrder;
import com.lvyang.order_service.mapper.PayOrderMapper;
import com.lvyang.order_service.service.PayOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
