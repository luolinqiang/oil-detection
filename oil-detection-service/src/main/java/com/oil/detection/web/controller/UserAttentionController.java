package com.oil.detection.web.controller;

import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.UserAttention;
import com.oil.detection.domain.page.QueryUserAttention;
import com.oil.detection.service.UserAttentionService;
import com.oil.detection.util.Precondition;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/attention")
public class UserAttentionController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(UserAttentionController.class);
    @Resource
    private UserAttentionService userattentionService;

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO listUserAttention(HttpServletRequest request, UserAttention userattention) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        userattention.setUserId(super.getUserInfo(request).getId());
        List<UserAttention> userattentions = userattentionService.listUserAttention(userattention);
        responsesDTO.setData(userattentions);
        return responsesDTO;
    }

    @RequestMapping(value = "/count", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO countUserAttention(HttpServletRequest request, UserAttention userattention) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        userattention.setUserId(super.getUserInfo(request).getId());
        responsesDTO.setData(userattentionService.countUserAttention(userattention));
        return responsesDTO;
    }

    @RequestMapping(value = "/pageList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO pageListUserAttention(HttpServletRequest request, QueryUserAttention userattention) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        userattention.setUserId(super.getUserInfo(request).getId());
        List<UserAttention> userattentions = userattentionService.pageListUserAttention(userattention);
        responsesDTO.setData(userattentions);
        return responsesDTO;
    }

    @RequestMapping(value = "/get", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO getUserAttention(HttpServletRequest request, UserAttention userattentionQuery) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        userattentionQuery.setUserId(super.getUserInfo(request).getId());
        UserAttention userattention = userattentionService.getUserAttention(userattentionQuery);
        responsesDTO.setData(userattention);
        return responsesDTO;
    }

    @RequestMapping(value = "/follow", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO follow(HttpServletRequest request, UserAttention userattention) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        Precondition.assertionNotBlank(userattention, "supplierId");
        Precondition.assertionNotBlank(userattention, "productId");
        userattention.setUserId(super.getUserInfo(request).getId());
        userattention.setCreateTime(new Date());
        userattentionService.saveUserAttention(userattention);
        return responsesDTO;
    }


    @RequestMapping(value = "/unFollow", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO unFollow(HttpServletRequest request, UserAttention userattention) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        Precondition.assertionNotBlank(userattention, "id");
        userattentionService.removeUserAttention(userattention);
        return responsesDTO;
    }
}
