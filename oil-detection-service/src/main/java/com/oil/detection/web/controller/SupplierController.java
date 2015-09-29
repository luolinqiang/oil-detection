package com.oil.detection.web.controller;

import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.Supplier;
import com.oil.detection.service.SupplierService;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/supplier")
public class SupplierController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(SupplierController.class);
    @Resource
    private SupplierService supplierService;

    @RequestMapping(value = "/get", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO getSupplier(Supplier supplierQuery) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        Supplier supplier = supplierService.getSupplier(supplierQuery);
        responsesDTO.setData(supplier);
        return responsesDTO;
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO saveSupplier(Supplier supplier) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        supplierService.saveSupplier(supplier);
        return responsesDTO;
    }


    @RequestMapping(value = "/modify", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO modifySupplier(Supplier supplier) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        supplierService.modifySupplier(supplier);
        return responsesDTO;
    }

    @RequestMapping(value = "/shop-{shopId}")
    public String shopDetail(@PathVariable("shopId") Long shopId, Model model, HttpServletRequest request) throws Exception {


         return "shop";
    }

    @RequestMapping(value = "/product-{pid}")
    public String productDetail(@PathVariable("pid") Long pid, Model model, HttpServletRequest request) throws Exception {


        return "product";
    }

}
