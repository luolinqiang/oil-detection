package com.oil.detection.service.impl;

import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.dao.HomeSettingMapper;
import com.oil.detection.dao.ProductMapper;
import com.oil.detection.domain.HomeSetting;
import com.oil.detection.domain.Product;
import com.oil.detection.service.HomeSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HomeSettingServiceImpl implements HomeSettingService {

    @Resource
    private HomeSettingMapper homesettingMapper;
    @Resource
    private ProductMapper productMapper;

    @Override
    public List<HomeSetting> listHomeSetting(HomeSetting homesetting) {
        return homesettingMapper.listHomeSetting(homesetting);
    }

    @Override
    public List<Product> product() {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        return productMapper.listHomeTopProduct();
    }
}
