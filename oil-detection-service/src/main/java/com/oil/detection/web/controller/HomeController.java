/**
 * Copyright(c) 2002-2013, 360buy.com  All Rights Reserved
 */

package com.oil.detection.web.controller;

import com.oil.detection.common.CommonConstants;
import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.HomeSetting;
import com.oil.detection.domain.Supplier;
import com.oil.detection.domain.result.RsSupplierHome;
import com.oil.detection.service.HomeSettingService;
import com.oil.detection.service.SupplierService;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/h")
public class HomeController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(HomeController.class);
    @Resource
    private HomeSettingService homeSettingService;
    @Resource
    private SupplierService supplierService;

    @RequestMapping(value = "/banner", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO banner(HttpServletRequest request, HttpServletResponse response) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        HomeSetting homeSetting = new HomeSetting();
        homeSetting.setType(CommonConstants.HomeSetting.TYPE_BANNER);
        homeSetting.setState(CommonConstants.Common.STATE_2);
        List<HomeSetting> homeSettings = homeSettingService.listHomeSetting(homeSetting);
        responsesDTO.setData(homeSettings);
        return responsesDTO;
    }

    @RequestMapping(value = "/directSale", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO directSale(HttpServletRequest request, HttpServletResponse response) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        HomeSetting homeSetting = new HomeSetting();
        homeSetting.setType(CommonConstants.HomeSetting.TYPE_DIRECTSALE);
        homeSetting.setState(CommonConstants.Common.STATE_2);
        List<HomeSetting> homeSettings = homeSettingService.listHomeSetting(homeSetting);

        List<RsSupplierHome> rsSupplierHomes = new ArrayList<RsSupplierHome>();
        for (HomeSetting setting : homeSettings) {
            Supplier supplier = new Supplier();
            supplier.setId(setting.getTargetId());
            Supplier supplierR = supplierService.getSupplier(supplier);

            RsSupplierHome supplierHome = new RsSupplierHome();
            BeanUtils.copyProperties(supplierR, supplierHome);
            rsSupplierHomes.add(supplierHome);
        }

        responsesDTO.setData(rsSupplierHomes);
        return responsesDTO;
    }

    @RequestMapping(value = "/link", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO link(HttpServletRequest request, HttpServletResponse response) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        HomeSetting homeSetting = new HomeSetting();
        homeSetting.setType(CommonConstants.HomeSetting.TYPE_LINK);
        homeSetting.setState(CommonConstants.Common.STATE_2);
        List<HomeSetting> homeSettings = homeSettingService.listHomeSetting(homeSetting);
        responsesDTO.setData(homeSettings);
        return responsesDTO;
    }
}
