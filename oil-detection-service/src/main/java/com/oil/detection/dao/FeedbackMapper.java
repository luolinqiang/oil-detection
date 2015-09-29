package com.oil.detection.dao;

import com.oil.detection.domain.Feedback;

import java.util.List;

public interface FeedbackMapper {

    /**
     * 查询列表
     *
     * @param feedback
     * @return
     */
    List<Feedback> listFeedback(Feedback feedback);

    /**
     * 查询
     *
     * @param feedback
     * @return
     */
    Feedback getFeedback(Feedback feedback);

    /**
     * 新增
     *
     * @param feedback
     * @author luolinqiang
     */
    Integer saveFeedback(Feedback feedback);

    /**
     * 修改
     *
     * @param feedback
     */
    void modifyFeedback(Feedback feedback);

    /**
     * 删除
     *
     * @param feedback
     */
    void removeFeedback(Feedback feedback);
}