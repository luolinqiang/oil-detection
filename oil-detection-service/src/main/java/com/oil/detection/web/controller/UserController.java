/**
 * Copyright(c) 2002-2013, 360buy.com  All Rights Reserved
 */

package com.oil.detection.web.controller;

import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.User;
import com.oil.detection.domain.param.UserLogin;
import com.oil.detection.domain.param.UserModify;
import com.oil.detection.domain.param.UserReg;
import com.oil.detection.domain.param.UserReset;
import com.oil.detection.service.UserService;
import com.oil.detection.util.DESUtil;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/u")
public class UserController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login(Model model, HttpServletRequest request) throws Exception {
        return "/user/login";
    }

    @RequestMapping(value = "/register")
    public String register(Model model, HttpServletRequest request) throws Exception {
        return "/user/register";
    }

    @RequestMapping(value = "/forget")
    public String forget(Model model, HttpServletRequest request) throws Exception {
        return "/user/forget";
    }

    @RequestMapping(value = "/more")
    public String more(Model model, HttpServletRequest request) throws Exception {
        User user = new User();
        user.setId(super.getUserInfo(request).getId());
        user = userService.getUser(user);
        model.addAttribute("user", user);
        return "/user/more";
    }

    @RequestMapping(value = "/edit")
    public String edit(Model model, HttpServletRequest request) throws Exception {
        User user = new User();
        user.setId(super.getUserInfo(request).getId());
        user = userService.getUser(user);
        model.addAttribute("user", user);
        return "/user/edit";
    }

    @RequestMapping(value = "/ajax/reg", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO reg(HttpServletRequest request, HttpServletResponse response,
                            @Valid UserReg userReg, BindingResult valid) {
        logger.debug("UserControllor--->reg--->start");
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        /*加密*/
        UserReg uReg = new UserReg();
        BeanUtils.copyProperties(userReg, uReg);
        try {
            String encryptString = DESUtil.encrypt(userReg.getPassword(), "12345678");
            userReg.setPassword(encryptString);
            userReg.setConfirmPassword(encryptString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userService.reg(userReg, valid);
        responsesDTO = regLogin(response, uReg, valid);
        return responsesDTO;
    }

    protected ResponsesDTO regLogin(HttpServletResponse response, UserReg userReg, BindingResult valid) {
        ResponsesDTO responsesDTO;
        UserLogin userLogin = new UserLogin();
        BeanUtils.copyProperties(userReg, userLogin);
        responsesDTO = this.loginUser(response, userLogin, valid);

        setCookieRedis(response, (User) responsesDTO.getData(), responsesDTO);
        return responsesDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/ajax/login", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    public ResponsesDTO loginUser(HttpServletResponse response, @Valid UserLogin userLogin, BindingResult valid) {
        logger.debug("LoginController--->login--->start");
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
//        if (StringUtils.isBlank(userLogin.getDevice())) {
//            return new ResponsesDTO(ReturnCode.ERROR_DEVICE_NULL);
//        }
        try {
            String encryptString = DESUtil.encrypt(userLogin.getPassword(), "12345678");
            userLogin.setPassword(encryptString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User login = userService.login(userLogin, valid);
        responsesDTO.setData(login);
        setCookieRedis(response, login, responsesDTO);
        return responsesDTO;
    }

    /**
     * 退出
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/ajax/logout", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    protected ResponsesDTO logout(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("LoginController--->logout--->start");
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        super.removeCookieRedis(request, response);
        return responsesDTO;
    }

    /**
     * 密码找回接口
     *
     * @param request
     * @param response
     * @param userReset
     * @param valid
     * @return
     */
    @RequestMapping(value = "/ajax/resetPwd", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO resetPwd(HttpServletRequest request, HttpServletResponse response,
                                 @Valid UserReset userReset, BindingResult valid) {
        logger.debug("UserControllor--->resetPwd--->start");
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);

        responsesDTO.setData(userService.resetPwd(userReset, valid));

        return responsesDTO;
    }

    /**
     * 修改用户基本信息
     *
     * @param request
     * @param response
     * @param userModify
     * @return
     */
    @RequestMapping(value = "/ajax/modify", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO modify(HttpServletRequest request, HttpServletResponse response,
                               UserModify userModify) {
        logger.debug("UserControllor--->modify--->start");
        User user = new User();
        BeanUtils.copyProperties(userModify, user);
        user.setId(super.getUserInfo(request).getId());
        userService.modifyUser(user);

        return new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
    }

    /**
     * 获取用户基本信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/ajax/getUser", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO getUser(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("UserControllor--->get--->start");
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        User user = new User();
        user.setId(super.getUserInfo(request).getId());
        User userR = userService.getUser(user);
        if (userR == null) {
            return new ResponsesDTO(ReturnCode.ERROR_USER_NONE);
        }

        responsesDTO.setData(userR);

        return responsesDTO;
    }

}
