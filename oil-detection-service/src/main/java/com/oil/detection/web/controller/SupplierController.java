package com.oil.detection.web.controller;

import com.oil.detection.common.CommonConstants;
import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.Product;
import com.oil.detection.domain.Supplier;
import com.oil.detection.domain.result.RsSupplierProduct;
import com.oil.detection.service.ProductService;
import com.oil.detection.service.SupplierService;
import com.oil.detection.util.DateUtils;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/s")
public class SupplierController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(SupplierController.class);
    @Resource
    private SupplierService supplierService;
    @Resource
    private ProductService productService;


    @RequestMapping(value = "/shop-{shopId}")
    public String shopDetail(@PathVariable("shopId") Long shopId, Model model, HttpServletRequest request) throws Exception {
        Supplier supplier = new Supplier();
        supplier.setState(CommonConstants.Common.STATE_2);
        supplier.setId(shopId);
        supplier = supplierService.getSupplier(supplier);
        model.addAttribute("supplier", supplier);
        return "supplier/shop";
    }


    @RequestMapping(value = "/ajax/detail", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO detail(HttpServletRequest request, HttpServletResponse response,
                               Supplier supplier) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        supplier.setState(CommonConstants.Common.STATE_2);
        Supplier supplierDb = supplierService.getSupplier(supplier);
        Product productQuery = new Product();
        BeanUtils.copyProperties(supplier, productQuery);
        productQuery.setState(CommonConstants.Common.STATE_2);
        List<Product> products = productService.listProduct(productQuery);
        for (Product product : products) {
            product.setTimeDesc(DateUtils.getTimeDesc(product.getUpdateTime()));
        }

        RsSupplierProduct rsSupplierProduct = new RsSupplierProduct();
        BeanUtils.copyProperties(supplierDb, rsSupplierProduct);
        rsSupplierProduct.setProducts(products);
        rsSupplierProduct.setAllCount(products.size());
        rsSupplierProduct.setNewCount(getNewCount(products));

        responsesDTO.setData(rsSupplierProduct);
        return responsesDTO;
    }

    private int getNewCount(List<Product> products) {
        int count = 0;
        for (Product product : products) {
            Date updateTime = product.getUpdateTime();
            Calendar nowCal = Calendar.getInstance();
            Calendar updateCal = Calendar.getInstance();
            updateCal.setTime(updateTime);
            int updateDay = updateCal.get(Calendar.DAY_OF_MONTH);
            int nowDay = nowCal.get(Calendar.DAY_OF_MONTH);
            int day = nowDay - updateDay;
            if (day <= 1) {
                count++;
            }
        }
        return count;
    }

    @RequestMapping(value = "/ajax/get", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO getSupplier(Supplier supplierQuery) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        Supplier supplier = supplierService.getSupplier(supplierQuery);
        responsesDTO.setData(supplier);
        return responsesDTO;
    }

    @RequestMapping(value = "/ajax/save", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO saveSupplier(Supplier supplier) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        supplierService.saveSupplier(supplier);
        return responsesDTO;
    }


    @RequestMapping(value = "/ajax/modify", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO modifySupplier(Supplier supplier) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        supplierService.modifySupplier(supplier);
        return responsesDTO;
    }

}
