package com.oil.detection.dao;

import com.oil.detection.domain.Area;
import com.oil.detection.domain.page.QueryArea;

import java.util.List;

public interface AreaMapper {

    /**
     * 查询列表
     * @param area
     * @return
     */
    List<Area> listArea(Area area);

    /**
    * 查询总数
    * @param area
    * @return
    */
    int countArea(Area area);

        /**
    * 分页列表
    * @param area
    * @return
    */
    List<Area> pageListArea(QueryArea area);
    
    /**
     * 查询
     * @param area
     * @return
     */
    Area getArea(Area area);

    /**
     * 新增
     * @param area
    * @author luolinqiang
    */
    Integer saveArea(Area area);

    /**
     * 修改
     * @param area
     */
    void modifyArea(Area area);

    /**
     * 删除
     * @param area
     */
    void removeArea(Area area);
}