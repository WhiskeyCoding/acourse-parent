package com.lvyang.msm_service.service;

import java.util.Map;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/1/19 11:12
 * @Version: 1.0
 */
public interface MsmTransactionService {

    boolean sendRegisterShortMessage(String cellPhoneNumber, Map<String, Object> smsParam);
}
