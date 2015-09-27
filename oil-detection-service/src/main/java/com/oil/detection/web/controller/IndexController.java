package com.oil.detection.web.controller;

import com.oil.detection.web.base.BaseControllor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseControllor {

    @RequestMapping(value = "/index")
    public String index(Model model, HttpServletRequest request) throws Exception {


        return "index";
    }

    @RequestMapping(value = "/more")
    public String more(Model model, HttpServletRequest request) throws Exception {

        return "more";
    }

}
