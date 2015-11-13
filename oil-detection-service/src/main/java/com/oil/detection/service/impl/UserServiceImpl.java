package com.oil.detection.service.impl;

import com.oil.detection.common.CommonConstants;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.dao.UserMapper;
import com.oil.detection.domain.User;
import com.oil.detection.domain.param.UserLogin;
import com.oil.detection.domain.param.UserReg;
import com.oil.detection.domain.param.UserReset;
import com.oil.detection.exception.CustomRuntimeException;
import com.oil.detection.service.UserService;
import com.oil.detection.util.DESUtil;
import com.oil.detection.util.ValidateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void reg(UserReg userReg, BindingResult valid) {
        checkUser(userReg, valid);

        User userQ = new User();
        userQ.setUserId(userReg.getUserId());
        userQ.setUserType(CommonConstants.User.USER_TYPE_C);
        int count = userMapper.countUser(userQ);
        if (count != 0) {
            throw new CustomRuntimeException(ReturnCode.ERROR_USER_REGED_LOGIN);
        }

        User user = new User();
        BeanUtils.copyProperties(userReg, user);
        user.setPhone(userReg.getUserId());
        user.setCreateTime(new Date());
        userMapper.saveUser(user);
    }

    private void checkUser(UserReg userReg, BindingResult valid) {
        if (valid.hasErrors()) {
            ReturnCode returnCode = ReturnCode.ERROR_PARAMS;
            returnCode.setMsg(ValidateUtils.validator(valid.getAllErrors()));
            throw new CustomRuntimeException(returnCode);
        }

        String password = userReg.getPassword();
        String confirmPassword = userReg.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            throw new CustomRuntimeException(ReturnCode.ERROR_USER_PASSWORD_CONFIRM_PASSWORD_DIFF);
        }

        // 验证码校验
//        String key = String.format(CommonCacheImpl.KEY, SysConstants.REDIS_PREFIX, "user_"
//                + userRegVo.getUserType(), userRegVo.getPhone());
//        String redisVerifyCode = commonCache.get(key);
//        if (!userRegVo.getVerifyCode().equals(redisVerifyCode)) {
//            return new ResponsesDTO(ReturnCode.ERROR_VERIFYCODE);
//        }
    }

    @Override
    public User login(UserLogin userLogin, BindingResult valid) {
        String userId = userLogin.getUserId();
        String password = userLogin.getPassword();

        if (valid.hasErrors()) {
            ReturnCode returnCode = ReturnCode.ERROR_PARAMS;
            returnCode.setMsg(ValidateUtils.validator(valid.getAllErrors()));
            throw new CustomRuntimeException(returnCode);
        }

        User userQ = new User();
        userQ.setUserId(userId);
        User userR = userMapper.getUser(userQ);
        if (userR == null) {
            throw new CustomRuntimeException(ReturnCode.ERROR_USER_NONE);
        }
        if (!userR.getPassword().equalsIgnoreCase(password.trim())) {
            throw new CustomRuntimeException(ReturnCode.ERROR_USER_PASSWORD);
        }

        return userR;
    }

    @Override
    public User resetPwd(UserReset userReset, BindingResult valid) {
        UserReg userReg = new UserReg();
        BeanUtils.copyProperties(userReset,userReg);
        checkUser(userReg, valid);

        User user = new User();
        user.setUserId(userReset.getUserId());
        user.setPassword(userReset.getPassword());
        userMapper.modifyUser(user);

        return user;
    }

    @Override
    public void modifyUser(User user) {
        userMapper.modifyUser(user);
    }

    @Override
    public User getUser(User user) {
        return userMapper.getUser(user);
    }
}
