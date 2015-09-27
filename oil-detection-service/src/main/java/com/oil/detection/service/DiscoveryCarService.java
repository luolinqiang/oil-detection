package com.oil.detection.service;

import com.oil.detection.domain.DiscoveryCar;
import com.oil.detection.domain.page.QueryDiscoveryCar;

import java.util.List;

public interface DiscoveryCarService {
    List<DiscoveryCar> listDiscoveryCar(DiscoveryCar discoverycar);

    int countDiscoveryCar(DiscoveryCar discoverycar);

    List<DiscoveryCar> pageListDiscoveryCar(QueryDiscoveryCar discoverycar);

    DiscoveryCar getDiscoveryCar(DiscoveryCar discoverycar);

    Integer saveDiscoveryCar(DiscoveryCar discoverycar);

    void modifyDiscoveryCar(DiscoveryCar discoverycar);

    void removeDiscoveryCar(DiscoveryCar discoverycar);
}
