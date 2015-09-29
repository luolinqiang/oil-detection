package com.oil.detection.web.controller;

import com.oil.detection.web.base.BaseControllor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: guowenjuan
 * Date: 15-9-29
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SearchController extends BaseControllor {

    @RequestMapping(value = "/search-diesel")
    public String searchdiesel(Model model, HttpServletRequest request) throws Exception {


        return "search/diesel";
    }

    @RequestMapping(value = "/search-petrol")
    public String searchpetrol(Model model, HttpServletRequest request) throws Exception {


        return "search/petrol";
    }
}
