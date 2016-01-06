package com.oil.detection.service;

import com.oil.detection.domain.Area;
import com.oil.detection.domain.page.QueryArea;

import java.util.List;

public interface AreaService {
List<Area> listArea(Area area);

int countArea(Area area);

List<Area> pageListArea(QueryArea area);

Area getArea(Area area);

Integer saveArea(Area area);

void modifyArea(Area area);

void removeArea(Area area);

    void indexArea();
}
