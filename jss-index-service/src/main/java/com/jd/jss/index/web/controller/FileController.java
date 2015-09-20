package com.jd.jss.index.web.controller;

import com.jd.jss.index.web.base.BaseControllor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FileController extends BaseControllor {

    @RequestMapping(value = "/files", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String putFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("name") String name
            , @RequestParam("pid") String pidStr, @RequestParam("uin") String userIdStr, @RequestParam("url") String link, @RequestParam("Image-Meta") String imageMeta) {
        return "test";
    }
}
