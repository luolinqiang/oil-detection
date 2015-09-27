package com.oil.detection.service.impl;

import com.oil.detection.dao.UserMapper;
import com.oil.detection.domain.User;
import com.oil.detection.domain.page.QueryUser;
import com.oil.detection.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> listUser(User user) {
        return userMapper.listUser(user);
    }

    @Override
    public int countUser(User user) {
        return userMapper.countUser(user);
    }

    @Override
    public List<User> pageListUser(QueryUser user) {
        return userMapper.pageListUser(user);
    }

    @Override
    public User getUser(User user) {
        return userMapper.getUser(user);
    }

    @Override
    public Integer saveUser(User user) {
        return userMapper.saveUser(user);
    }

    @Override
    public void modifyUser(User user) {
        userMapper.modifyUser(user);
    }

    @Override
    public void removeUser(User user) {
        userMapper.removeUser(user);
    }
}
