package com.oil.detection.service.impl;

import com.oil.detection.dao.DiscoveryCarMapper;
import com.oil.detection.domain.DiscoveryCar;
import com.oil.detection.domain.page.QueryDiscoveryCar;
import com.oil.detection.service.DiscoveryCarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DiscoveryCarServiceImpl implements DiscoveryCarService {

    @Resource
    private DiscoveryCarMapper discoverycarMapper;

    @Override
    public List<DiscoveryCar> listDiscoveryCar(DiscoveryCar discoverycar) {
        return discoverycarMapper.listDiscoveryCar(discoverycar);
    }

    @Override
    public int countDiscoveryCar(DiscoveryCar discoverycar) {
        return discoverycarMapper.countDiscoveryCar(discoverycar);
    }

    @Override
    public List<DiscoveryCar> pageListDiscoveryCar(QueryDiscoveryCar discoverycar) {
        return discoverycarMapper.pageListDiscoveryCar(discoverycar);
    }

    @Override
    public DiscoveryCar getDiscoveryCar(DiscoveryCar discoverycar) {
        return discoverycarMapper.getDiscoveryCar(discoverycar);
    }

    @Override
    public Integer saveDiscoveryCar(DiscoveryCar discoverycar) {
        return discoverycarMapper.saveDiscoveryCar(discoverycar);
    }

    @Override
    public void modifyDiscoveryCar(DiscoveryCar discoverycar) {
        discoverycarMapper.modifyDiscoveryCar(discoverycar);
    }

    @Override
    public void removeDiscoveryCar(DiscoveryCar discoverycar) {
        discoverycarMapper.removeDiscoveryCar(discoverycar);
    }
}
