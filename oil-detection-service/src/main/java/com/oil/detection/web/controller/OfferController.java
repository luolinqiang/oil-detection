/**
 * Copyright(c) 2002-2013, 360buy.com  All Rights Reserved
 */

package com.oil.detection.web.controller;

import com.oil.detection.common.CommonConstants;
import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.page.QueryProduct;
import com.oil.detection.domain.result.RsOfferProduct;
import com.oil.detection.service.ProductService;
import com.oil.detection.util.DateUtils;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 报价信息相关接口
 */
@Controller
@RequestMapping(value = "/offer")
public class OfferController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(OfferController.class);
    @Resource
    private ProductService productService;

    @RequestMapping(value = "/diesel", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO diesel(HttpServletRequest request, HttpServletResponse response,
                               @Valid QueryProduct queryProduct, BindingResult valid) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        queryProduct.setType(CommonConstants.Product.TYPE_DIESEL);
        queryProduct.setState(CommonConstants.Common.STATE_2);
        List<RsOfferProduct> rsOfferProducts = productService.pageListOfferProduct(queryProduct);
        for (RsOfferProduct rsOfferProduct : rsOfferProducts) {
            rsOfferProduct.setTimeDesc(DateUtils.getTimeDesc(rsOfferProduct.getUpdateTime()));
        }
        responsesDTO.setData(rsOfferProducts);
        return responsesDTO;
    }

    @RequestMapping(value = "/petrol", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO petrol(HttpServletRequest request, HttpServletResponse response,
                               @Valid QueryProduct queryProduct, BindingResult valid) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        queryProduct.setType(CommonConstants.Product.TYPE_PETROL);
        List<RsOfferProduct> rsOfferProducts = productService.pageListOfferProduct(queryProduct);
        for (RsOfferProduct rsOfferProduct : rsOfferProducts) {
            rsOfferProduct.setTimeDesc(DateUtils.getTimeDesc(rsOfferProduct.getUpdateTime()));
        }
        responsesDTO.setData(rsOfferProducts);
        return responsesDTO;
    }
}
