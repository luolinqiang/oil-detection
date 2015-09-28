package com.oil.detection.service;

import com.oil.detection.domain.User;
import com.oil.detection.domain.param.UserLogin;
import com.oil.detection.domain.param.UserReg;
import com.oil.detection.domain.param.UserReset;
import org.springframework.validation.BindingResult;

public interface UserService {
    void reg(UserReg userReg, BindingResult valid);

    User login(UserLogin userLogin, BindingResult valid);

    User resetPwd(UserReset userReset, BindingResult valid);

    void modifyUser(User user);

    User getUser(User user);
}
