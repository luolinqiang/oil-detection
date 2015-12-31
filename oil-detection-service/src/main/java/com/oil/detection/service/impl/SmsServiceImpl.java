package com.oil.detection.service.impl;

import com.oil.detection.common.CommonConstants;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.exception.CustomRuntimeException;
import com.oil.detection.log.RunLog;
import com.oil.detection.service.SmsService;
import com.oil.detection.sms.WebService1;
import com.oil.detection.sms.WebService1Soap;
import com.oil.detection.util.PropertyUtil;
import com.oil.detection.util.VerifyCodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class SmsServiceImpl implements SmsService {
    private static final Logger logger = LoggerFactory
            .getLogger(SmsServiceImpl.class);
    public static Map<String, String> codeMap = new HashMap<String, String>();

    @Override
    public String sendPhoneMsg(Integer userType, String phone) {
        if (!(phone != null && phone.matches("^1\\d{10}$"))) {
            ReturnCode returnCode = ReturnCode.ERROR_PARAMS;
            returnCode.setMsg("电话号不能不合法;");
            throw new CustomRuntimeException(returnCode);
        }

//        String keyCount = String.format(CommonCacheImpl.KEY, SysConstants.REDIS_PREFIX, "user_" + userType, phone + "_count");
//        String countStr = commonCache.get(keyCount);
//        if (StringUtils.isNotBlank(countStr)) {
//            int count = Integer.parseInt(countStr);
//            if (count >= Constants.MSG_DAY_MAX_COUNT) {
//                ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ERROR_PHONE_MSG_DAY_COUNT);
//                responsesDTO.setAttach(String.format("请勿频繁发送手机验证码，每个手机号每天限制%d次", Constants.MSG_DAY_MAX_COUNT));
//                return responsesDTO;
//            }
//        }

        String code = VerifyCodeUtils.generateCode(4);
        String returnStr = sendMsg(String.format(CommonConstants.PHONE_MSG_TEMPLATE, code), phone);

        codeMap.put(phone, code);
        return code;
    }


    private String sendMsg(String msg, String phoneNum) {
        URL wsdlURL = null;
        try {
            wsdlURL = new URL(PropertyUtil.getString("sms.webservice.url"));
        } catch (MalformedURLException e) {
            RunLog.LOG.debug("webservice url 发生异常", e);
        }
        QName SERVICE_NAME = new QName("http://tempuri.org/", "WebService1");
        WebService1 ss = new WebService1(wsdlURL, SERVICE_NAME);
        WebService1Soap port = ss.getWebService1Soap();

        RunLog.LOG.debug("Invoking smsSend...");
        String secret = PropertyUtil.getString("sms.webservice.secret");
        String result = port.smsSend(secret, phoneNum, msg);
        if (result != null && result.equals("OK!")) {
            return result;
        }
        throw new RuntimeException(String.format("短信通道发生异常：%s", result));
    }
}
