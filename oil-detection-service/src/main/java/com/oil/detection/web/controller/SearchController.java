package com.oil.detection.web.controller;

import com.oil.detection.common.CommonConstants;
import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.HomeSetting;
import com.oil.detection.domain.Pic;
import com.oil.detection.domain.Product;
import com.oil.detection.domain.Supplier;
import com.oil.detection.domain.param.ItemSearch;
import com.oil.detection.domain.result.RsProductDetail;
import com.oil.detection.service.HomeSettingService;
import com.oil.detection.service.PicService;
import com.oil.detection.service.ProductService;
import com.oil.detection.service.SupplierService;
import com.oil.detection.util.DESUtil;
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
import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: guowenjuan
 * Date: 15-9-29
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SearchController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/items-{type}")
    public String searchdItems(@PathVariable("type") String type, Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("type", type);

        return "product/items-list";
    }


    @RequestMapping(value = "/search-diesel")
    public String searchdiesel(Model model, HttpServletRequest request) throws Exception {


        return "search/diesel";
    }

    @RequestMapping(value = "/search-petrol")
    public String searchpetrol(Model model, HttpServletRequest request) throws Exception {


        return "search/petrol";
    }



    @RequestMapping(value = "/zhaoche")
    public String findTruck(Model model, HttpServletRequest request) throws Exception {


        return "product/zhaoche";
    }
}
