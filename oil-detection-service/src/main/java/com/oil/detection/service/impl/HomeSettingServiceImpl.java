package com.oil.detection.service.impl;

import com.oil.detection.dao.HomeSettingMapper;
import com.oil.detection.domain.HomeSetting;
import com.oil.detection.service.HomeSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HomeSettingServiceImpl implements HomeSettingService {

    @Resource
    private HomeSettingMapper homesettingMapper;

    @Override
    public List<HomeSetting> listHomeSetting(HomeSetting homesetting) {
        return homesettingMapper.listHomeSetting(homesetting);
    }
}
