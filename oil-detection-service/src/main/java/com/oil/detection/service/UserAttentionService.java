package com.oil.detection.service;

import com.oil.detection.domain.UserAttention;
import com.oil.detection.domain.page.QueryUserAttention;

import java.util.List;

public interface UserAttentionService {
    List<UserAttention> listUserAttention(UserAttention userattention);

    int countUserAttention(UserAttention userattention);

    List<UserAttention> pageListUserAttention(QueryUserAttention userattention);

    UserAttention getUserAttention(UserAttention userattention);

    Integer saveUserAttention(UserAttention userattention);

    void modifyUserAttention(UserAttention userattention);

    void removeUserAttention(UserAttention userattention);
}
