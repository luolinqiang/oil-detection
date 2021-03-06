package com.oil.detection.service.impl;

import com.oil.detection.dao.AreaMapper;
import com.oil.detection.domain.Area;
import com.oil.detection.domain.page.QueryArea;
import com.oil.detection.service.AreaService;
import com.oil.detection.solr.SolrAreaUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Resource
    private AreaMapper areaMapper;

    @Override
    public List<Area> listArea(Area area) {
        return areaMapper.listArea(area);
    }

    @Override
    public int countArea(Area area) {
        return areaMapper.countArea(area);
    }

    @Override
    public List<Area> pageListArea(QueryArea area) {
        return areaMapper.pageListArea(area);
    }

    @Override
    public Area getArea(Area area) {
        return areaMapper.getArea(area);
    }

    @Override
    public Integer saveArea(Area area) {
        Integer integer = areaMapper.saveArea(area);
        indexArea();
        return integer;
    }

    @Override
    public void modifyArea(Area area) {
        areaMapper.modifyArea(area);
        indexArea();
    }

    @Override
    public void removeArea(Area area) {
        areaMapper.removeArea(area);
    }

    @Override
    public void indexArea() {
        List<Area> areas = areaMapper.listArea(new Area());

        SolrAreaUtils.addIndexs(areas);
    }
}
