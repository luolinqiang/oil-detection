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
public class UserController extends BaseControllor {

    @RequestMapping(value = "/login")
    public String login( Model model, HttpServletRequest request) throws Exception {


        return "user/login";
    }

    @RequestMapping(value = "/register")
    public String register( Model model, HttpServletRequest request) throws Exception {


        return "user/register";
    }


    @RequestMapping(value = "/forget")
    public String forget( Model model, HttpServletRequest request) throws Exception {


        return "user/forget";
    }

}
