package com.oil.detection.web.controller;

import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.UserAttention;
import com.oil.detection.domain.page.QueryUserAttention;
import com.oil.detection.service.UserAttentionService;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/userattention")
public class UserAttentionController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(UserAttentionController.class);
    @Resource
    private UserAttentionService userattentionService;

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO listUserAttention(UserAttention userattention) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        List<UserAttention> userattentions = userattentionService.listUserAttention(userattention);
        responsesDTO.setData(userattentions);
        return responsesDTO;
    }

    @RequestMapping(value = "/count", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO countUserAttention(UserAttention userattention) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        responsesDTO.setData(userattentionService.countUserAttention(userattention));
        return responsesDTO;
    }

    @RequestMapping(value = "/pageList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO pageListUserAttention(QueryUserAttention userattention) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        List<UserAttention> userattentions = userattentionService.pageListUserAttention(userattention);
        responsesDTO.setData(userattentions);
        return responsesDTO;
    }

    @RequestMapping(value = "/get", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO getUserAttention(UserAttention userattentionQuery) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        UserAttention userattention = userattentionService.getUserAttention(userattentionQuery);
        responsesDTO.setData(userattention);
        return responsesDTO;
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO saveUserAttention(UserAttention userattention) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        userattentionService.saveUserAttention(userattention);
        return responsesDTO;
    }


    @RequestMapping(value = "/modify", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO modifyUserAttention(UserAttention userattention) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        userattentionService.modifyUserAttention(userattention);
        return responsesDTO;
    }
}
