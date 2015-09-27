package com.oil.detection.service.impl;

import com.oil.detection.dao.HomeSettingMapper;
import com.oil.detection.domain.HomeSetting;
import com.oil.detection.domain.page.QueryHomeSetting;
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

    @Override
    public int countHomeSetting(HomeSetting homesetting) {
        return homesettingMapper.countHomeSetting(homesetting);
    }

    @Override
    public List<HomeSetting> pageListHomeSetting(QueryHomeSetting homesetting) {
        return homesettingMapper.pageListHomeSetting(homesetting);
    }

    @Override
    public HomeSetting getHomeSetting(HomeSetting homesetting) {
        return homesettingMapper.getHomeSetting(homesetting);
    }

    @Override
    public Integer saveHomeSetting(HomeSetting homesetting) {
        return homesettingMapper.saveHomeSetting(homesetting);
    }

    @Override
    public void modifyHomeSetting(HomeSetting homesetting) {
        homesettingMapper.modifyHomeSetting(homesetting);
    }

    @Override
    public void removeHomeSetting(HomeSetting homesetting) {
        homesettingMapper.removeHomeSetting(homesetting);
    }
}
