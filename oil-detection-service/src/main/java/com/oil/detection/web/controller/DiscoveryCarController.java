package com.oil.detection.web.controller;

import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.DiscoveryCar;
import com.oil.detection.service.DiscoveryCarService;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
public class DiscoveryCarController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(DiscoveryCarController.class);
    @Resource
    private DiscoveryCarService discoverycarService;

    @RequestMapping(value = "/dcar/save", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO saveDiscoveryCar(DiscoveryCar discoverycar) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        discoverycar.setCreateTime(new Date());
        discoverycarService.saveDiscoveryCar(discoverycar);
        return responsesDTO;
    }

    @RequestMapping(value = "/dcar/get", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO getDiscoveryCar(DiscoveryCar discoverycarQuery) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        DiscoveryCar discoverycar = discoverycarService.getDiscoveryCar(discoverycarQuery);
        responsesDTO.setData(discoverycar);
        return responsesDTO;
    }

    @RequestMapping(value = "/dcar/modify", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO modifyDiscoveryCar(DiscoveryCar discoverycar) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        discoverycarService.modifyDiscoveryCar(discoverycar);
        return responsesDTO;
    }
}
