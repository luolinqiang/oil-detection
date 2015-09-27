package com.oil.detection.web.controller;

import com.oil.detection.web.base.BaseControllor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController extends BaseControllor {

    @RequestMapping(value = "/zhaoche")
    public String findTruck(Model model, HttpServletRequest request) throws Exception {


        return "publish/zhaoche";
    }

//    private List<String> getDays() {
//
//
//
//
//    }


}
