package com.jd.jss.index.web.controller;

import com.jd.jss.index.web.base.BaseControllor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luolinqiang
 */
@Controller
public class IndexController extends BaseControllor {

    @RequestMapping(value = "/index")
    public String index( Model model, HttpServletRequest request) throws Exception {


        return "index";
    }

    @RequestMapping(value = "/more")
    public String more( Model model, HttpServletRequest request) throws Exception {

        return "more";
    }

}
