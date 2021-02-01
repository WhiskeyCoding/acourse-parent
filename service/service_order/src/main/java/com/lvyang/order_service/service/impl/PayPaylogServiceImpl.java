package com.lvyang.order_service.service.impl;

import com.lvyang.order_service.entity.PayPaylog;
import com.lvyang.order_service.mapper.PayPaylogMapper;
import com.lvyang.order_service.service.PayPaylogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author lvyang
 * @since 2021-02-01
 */
@Service
public class PayPaylogServiceImpl extends ServiceImpl<PayPaylogMapper, PayPaylog> implements PayPaylogService {

}
