package com.lvyang.msm_service.controller.transaction;

import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.msm_service.service.MsmTransactionService;
import com.lvyang.msm_service.smsutils.SMSRandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/1/19 11:08
 * @Version: 1.0
 */
@RestController
@RequestMapping("/msm_service/msm_transaction")
@CrossOrigin
public class MsmTransactionController {

    final MsmTransactionService msmTransactionService;
    final RedisTemplate<String, String> redisTemplate;
    @Autowired
    private MsmTransactionController(MsmTransactionService msmTransactionService,RedisTemplate<String, String> redisTemplate) {
        this.msmTransactionService = msmTransactionService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 发送注册短信的方法
     */
    @GetMapping("sendRegisterMessage/{cellPhoneNumber}")
    public JsonResultUnity sendRegisterMessage(@PathVariable String cellPhoneNumber) {
        //1 从Redis获取验证码，如果获取到直接取回
        String smsCode = redisTemplate.opsForValue().get(cellPhoneNumber);
        if(!StringUtils.isEmpty(smsCode)){
            return JsonResultUnity.correct();
        }
        //2 如何Redis获取不到，进行阿里云
        //生成随机值，传递阿里云进行发送
        smsCode = SMSRandomUtil.getSixBitRandom();
        Map<String, Object> smsParam = new HashMap<>();
        smsParam.put("code", smsCode);
        //调用Service中短信发送方法
        boolean isSend = msmTransactionService.sendRegisterShortMessage(cellPhoneNumber, smsParam);
        if (isSend) {
            //发送成功，把发送成功验证码放到Redis里面，设置有效时间
            redisTemplate.opsForValue().set(cellPhoneNumber,smsCode,5, TimeUnit.MINUTES);
            return JsonResultUnity.correct().message("注册短信发送成功");
        } else {
            return JsonResultUnity.error().message("注册短信发送失败");
        }
    }
}
