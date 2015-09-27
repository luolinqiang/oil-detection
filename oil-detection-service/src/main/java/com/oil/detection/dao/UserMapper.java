package com.oil.detection.dao;

import com.oil.detection.domain.User;
import com.oil.detection.domain.page.QueryUser;

import java.util.List;

public interface UserMapper {

    /**
     * 查询列表
     *
     * @param user
     * @return
     */
    List<User> listUser(User user);

    /**
     * 查询总数
     *
     * @param user
     * @return
     */
    int countUser(User user);

    /**
     * 分页列表
     *
     * @param user
     * @return
     */
    List<User> pageListUser(QueryUser user);

    /**
     * 查询
     *
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 新增
     *
     * @param user
     * @author luolinqiang
     */
    Integer saveUser(User user);

    /**
     * 修改
     *
     * @param user
     */
    void modifyUser(User user);

    /**
     * 删除
     *
     * @param user
     */
    void removeUser(User user);
}