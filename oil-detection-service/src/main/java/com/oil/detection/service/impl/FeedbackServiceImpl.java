package com.oil.detection.service.impl;

import com.oil.detection.dao.FeedbackMapper;
import com.oil.detection.domain.Feedback;
import com.oil.detection.service.FeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Resource
    private FeedbackMapper feedbackMapper;

    @Override
    public Integer saveFeedback(Feedback feedback) {
        return feedbackMapper.saveFeedback(feedback);
    }
}
