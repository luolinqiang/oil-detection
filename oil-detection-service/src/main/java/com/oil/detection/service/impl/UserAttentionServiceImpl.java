package com.oil.detection.service.impl;

import com.oil.detection.dao.UserAttentionMapper;
import com.oil.detection.domain.UserAttention;
import com.oil.detection.domain.page.QueryUserAttention;
import com.oil.detection.service.UserAttentionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserAttentionServiceImpl implements UserAttentionService {

    @Resource
    private UserAttentionMapper userattentionMapper;

    @Override
    public List<UserAttention> listUserAttention(UserAttention userattention) {
        return userattentionMapper.listUserAttention(userattention);
    }

    @Override
    public int countUserAttention(UserAttention userattention) {
        return userattentionMapper.countUserAttention(userattention);
    }

    @Override
    public List<UserAttention> pageListUserAttention(QueryUserAttention userattention) {
        return userattentionMapper.pageListUserAttention(userattention);
    }

    @Override
    public UserAttention getUserAttention(UserAttention userattention) {
        return userattentionMapper.getUserAttention(userattention);
    }

    @Override
    public Integer saveUserAttention(UserAttention userattention) {
        return userattentionMapper.saveUserAttention(userattention);
    }

    @Override
    public void modifyUserAttention(UserAttention userattention) {
        userattentionMapper.modifyUserAttention(userattention);
    }

    @Override
    public void removeUserAttention(UserAttention userattention) {
        userattentionMapper.removeUserAttention(userattention);
    }
}
