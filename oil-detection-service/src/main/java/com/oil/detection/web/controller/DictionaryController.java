package com.oil.detection.web.controller;

import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.Dictionary;
import com.oil.detection.service.DictionaryService;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 数据字典相关接口
 */
@Controller
@RequestMapping(value = "/dic")
public class DictionaryController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(DictionaryController.class);
    @Resource
    private DictionaryService dictionaryService;

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO listDictionary(Dictionary dictionary) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        List<Dictionary> dictionarys = dictionaryService.listDictionary(dictionary);
        responsesDTO.setData(dictionarys);
        return responsesDTO;
    }

    @RequestMapping(value = "/get", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO getDictionary(Dictionary dictionaryQuery) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        Dictionary dictionary = dictionaryService.getDictionary(dictionaryQuery);
        responsesDTO.setData(dictionary);
        return responsesDTO;
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO saveDictionary(Dictionary dictionary) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        dictionary.setCreateTime(new Date());
        dictionaryService.saveDictionary(dictionary);
        return responsesDTO;
    }


    @RequestMapping(value = "/modify", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO modifyDictionary(Dictionary dictionary) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        dictionaryService.modifyDictionary(dictionary);
        return responsesDTO;
    }
}
