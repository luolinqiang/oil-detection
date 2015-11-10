package com.oil.detection.web.controller;

import com.oil.detection.common.CommonConstants;
import com.oil.detection.domain.HomeSetting;
import com.oil.detection.service.HomeSettingService;
import com.oil.detection.web.base.BaseControllor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: guowenjuan
 * Date: 15-9-29
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class FrontController extends BaseControllor {

    @Resource
    private HomeSettingService homeSettingService;


    @RequestMapping(value = "/search-diesel")
    public String searchdiesel(Model model, HttpServletRequest request) throws Exception {


        return "search/diesel";
    }

    @RequestMapping(value = "/search-petrol")
    public String searchpetrol(Model model, HttpServletRequest request) throws Exception {


        return "search/petrol";
    }

    @RequestMapping(value = "/shop-{shopId}")
    public String shopDetail(@PathVariable("shopId") Long shopId, Model model, HttpServletRequest request) throws Exception {


        return "shop";
    }

    @RequestMapping(value = "/product-{pid}")
    public String productDetail(@PathVariable("pid") Long pid, Model model, HttpServletRequest request) throws Exception {


        return "product";
    }

    @RequestMapping(value = "/zhaoche")
    public String findTruck(Model model, HttpServletRequest request) throws Exception {


        return "publish/zhaoche";
    }
}
