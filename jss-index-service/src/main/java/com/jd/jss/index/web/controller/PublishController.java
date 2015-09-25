package com.jd.jss.index.web.controller;

import com.jd.jss.index.web.base.BaseControllor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author luolinqiang
 */
@Controller
public class PublishController extends BaseControllor {

    @RequestMapping(value = "/zhaoche")
    public String findTruck( Model model, HttpServletRequest request) throws Exception {




        return "publish/zhaoche";
    }

//    private List<String> getDays() {
//
//
//
//
//    }



}
