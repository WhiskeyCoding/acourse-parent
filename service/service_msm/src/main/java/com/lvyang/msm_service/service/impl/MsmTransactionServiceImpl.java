package com.lvyang.msm_service.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.utils.StringUtils;
import com.lvyang.msm_service.service.MsmTransactionService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/1/19 11:15
 * @Version: 1.0
 */
@Service
public class MsmTransactionServiceImpl implements MsmTransactionService {

    /**
     * 发送注册短信方法
     *
     * @param cellPhoneNumber 用户手机号
     * @param smsParam        验证码
     * @return
     */
    @Override
    public boolean sendRegisterShortMessage(String cellPhoneNumber, Map<String, Object> smsParam) {
        if (StringUtils.isEmpty(cellPhoneNumber)) {
            return false;
        }

        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI4Fy13keirDJT2uiPK2Ez", "c0sTUVJEH0CzM94xMi0I176oxkB8zN");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        //设置发送的相关参数
        request.putQueryParameter("PhoneNumbers", cellPhoneNumber);//手机号
        request.putQueryParameter("SignName", "咚文洋我的在线教育网站");//申请签名名称
        request.putQueryParameter("TemplateCode", "SMS_209827577");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(smsParam));


        try {
            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}


