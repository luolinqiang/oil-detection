package com.oil.detection.service;

import com.oil.detection.domain.HomeSetting;
import com.oil.detection.domain.Product;

import java.util.List;

public interface HomeSettingService {
    List<HomeSetting> listHomeSetting(HomeSetting homesetting);


    List<Product> product();
}
