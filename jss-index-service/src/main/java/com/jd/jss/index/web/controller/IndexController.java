package com.jd.jss.index.web.controller;

import com.jd.jss.index.web.base.BaseControllor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author luolinqiang
 */
@Controller
public class IndexController extends BaseControllor {

    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=utf-8")
    public String index(Model model) {


        return "index";
    }

}
