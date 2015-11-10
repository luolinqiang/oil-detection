/**
 * Copyright(c) 2002-2013, 360buy.com  All Rights Reserved
 */

package com.oil.detection.web.controller;

import com.oil.detection.common.CommonConstants;
import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.Area;
import com.oil.detection.domain.HomeSetting;
import com.oil.detection.domain.Product;
import com.oil.detection.domain.Supplier;
import com.oil.detection.domain.result.RsHomeProduct;
import com.oil.detection.domain.result.RsHomeSupplier;
import com.oil.detection.service.AreaService;
import com.oil.detection.service.HomeSettingService;
import com.oil.detection.service.SupplierService;
import com.oil.detection.util.TransferUtils;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页相关接口
 */
@Controller
public class HomeController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(HomeController.class);
    @Resource
    private HomeSettingService homeSettingService;
    @Resource
    private SupplierService supplierService;
    @Resource
    private AreaService areaService;

    @RequestMapping(value = "/index")
    public String index(Model model, HttpServletRequest request) throws Exception {
        HomeSetting homeSetting = new HomeSetting();
        homeSetting.setType(CommonConstants.HomeSetting.TYPE_BANNER);
        homeSetting.setState(CommonConstants.Common.STATE_2);
        List<HomeSetting> homeSettings = homeSettingService.listHomeSetting(homeSetting);
        model.addAttribute("banners", homeSettings);
        return "index";
    }

    @RequestMapping(value = "/h/ajax/directSale", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO directSale(HttpServletRequest request, HttpServletResponse response) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        HomeSetting homeSetting = new HomeSetting();
        homeSetting.setType(CommonConstants.HomeSetting.TYPE_DIRECTSALE);
        homeSetting.setState(CommonConstants.Common.STATE_2);
        List<HomeSetting> homeSettings = homeSettingService.listHomeSetting(homeSetting);

        List<RsHomeSupplier> rsSupplierHomes = new ArrayList<RsHomeSupplier>();
        for (HomeSetting setting : homeSettings) {
            Supplier supplier = new Supplier();
            supplier.setId(setting.getTargetId());
            Supplier supplierR = supplierService.getSupplier(supplier);
            supplierR.setType(setting.getType());
            rsSupplierHomes.add((RsHomeSupplier) TransferUtils.transfer(supplierR, RsHomeSupplier.class));
        }

        responsesDTO.setData(rsSupplierHomes);
        return responsesDTO;
    }

    @RequestMapping(value = "/h/ajax/recommend", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO product(HttpServletRequest request, HttpServletResponse response) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        List<RsHomeProduct> rs = new ArrayList<RsHomeProduct>();
        List<Product> products = homeSettingService.product();
        for (Product product : products) {
            RsHomeProduct rsHomeProduct = (RsHomeProduct) TransferUtils.transfer(product, RsHomeProduct.class);
            Supplier supplier = new Supplier();
            supplier.setId(product.getId());
            Supplier supplierDb = supplierService.getSupplier(supplier);
            rsHomeProduct.setSupplierName(supplierDb.getCompanyName());

            Area area = new Area();
            area.setId(supplierDb.getAreaId());
            Area areaDb = areaService.getArea(area);
            rsHomeProduct.setAreaDesc(areaDb.getAreaName());
            rs.add(rsHomeProduct);
        }
        responsesDTO.setData(rs);
        return responsesDTO;
    }

    @RequestMapping(value = "/h/ajax/link", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
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
