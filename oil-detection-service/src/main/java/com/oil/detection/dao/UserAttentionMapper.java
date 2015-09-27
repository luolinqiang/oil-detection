package com.oil.detection.dao;

import com.oil.detection.domain.UserAttention;
import com.oil.detection.domain.page.QueryUserAttention;

import java.util.List;

public interface UserAttentionMapper {

    /**
     * 查询列表
     *
     * @param userattention
     * @return
     */
    List<UserAttention> listUserAttention(UserAttention userattention);

    /**
     * 查询总数
     *
     * @param userattention
     * @return
     */
    int countUserAttention(UserAttention userattention);

    /**
     * 分页列表
     *
     * @param userattention
     * @return
     */
    List<UserAttention> pageListUserAttention(QueryUserAttention userattention);

    /**
     * 查询
     *
     * @param userattention
     * @return
     */
    UserAttention getUserAttention(UserAttention userattention);

    /**
     * 新增
     *
     * @param userattention
     * @author luolinqiang
     */
    Integer saveUserAttention(UserAttention userattention);

    /**
     * 修改
     *
     * @param userattention
     */
    void modifyUserAttention(UserAttention userattention);

    /**
     * 删除
     *
     * @param userattention
     */
    void removeUserAttention(UserAttention userattention);
}