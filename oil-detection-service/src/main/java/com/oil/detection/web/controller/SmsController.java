package com.oil.detection.web.controller;

import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.service.SmsService;
import com.oil.detection.web.base.BaseControllor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/sms")
public class SmsController extends BaseControllor {
    private static final Logger logger = LoggerFactory
            .getLogger(SmsController.class);

    @Resource
    private SmsService smsService;

    /**
     * 获取手机验证码
     *
     * @param reuqest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/code", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    public ResponsesDTO verifyCode(HttpServletRequest reuqest, Integer userType, String phone) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        responsesDTO.setData(smsService.sendPhoneMsg(userType, phone));
        return responsesDTO;
    }
}
