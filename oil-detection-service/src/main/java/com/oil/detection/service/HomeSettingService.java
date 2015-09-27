package com.oil.detection.service;

import com.oil.detection.domain.HomeSetting;
import com.oil.detection.domain.page.QueryHomeSetting;

import java.util.List;

public interface HomeSettingService {
    List<HomeSetting> listHomeSetting(HomeSetting homesetting);

    int countHomeSetting(HomeSetting homesetting);

    List<HomeSetting> pageListHomeSetting(QueryHomeSetting homesetting);

    HomeSetting getHomeSetting(HomeSetting homesetting);

    Integer saveHomeSetting(HomeSetting homesetting);

    void modifyHomeSetting(HomeSetting homesetting);

    void removeHomeSetting(HomeSetting homesetting);
}
