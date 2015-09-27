package com.oil.detection.service;

import com.oil.detection.domain.User;
import com.oil.detection.domain.page.QueryUser;

import java.util.List;

public interface UserService {
    List<User> listUser(User user);

    int countUser(User user);

    List<User> pageListUser(QueryUser user);

    User getUser(User user);

    Integer saveUser(User user);

    void modifyUser(User user);

    void removeUser(User user);
}
