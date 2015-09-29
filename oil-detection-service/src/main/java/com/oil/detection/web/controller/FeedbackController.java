package com.oil.detection.web.controller;

import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.Feedback;
import com.oil.detection.service.FeedbackService;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "/feedback")
public class FeedbackController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(FeedbackController.class);
    @Resource
    private FeedbackService feedbackService;

    @RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO saveFeedback(HttpServletRequest request, Feedback feedback) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        feedback.setUserId(super.getUserInfo(request).getId());
        feedback.setCreateTime(new Date());
        feedbackService.saveFeedback(feedback);
        return responsesDTO;
    }

}
