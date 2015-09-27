package com.oil.detection.dao;

import com.oil.detection.domain.DiscoveryCar;
import com.oil.detection.domain.page.QueryDiscoveryCar;

import java.util.List;

public interface DiscoveryCarMapper {

    /**
     * 查询列表
     * @param discoverycar
     * @return
     */
    List<DiscoveryCar> listDiscoveryCar(DiscoveryCar discoverycar);

    /**
    * 查询总数
    * @param discoverycar
    * @return
    */
    int countDiscoveryCar(DiscoveryCar discoverycar);

        /**
    * 分页列表
    * @param discoverycar
    * @return
    */
    List<DiscoveryCar> pageListDiscoveryCar(QueryDiscoveryCar discoverycar);
    
    /**
     * 查询
     * @param discoverycar
     * @return
     */
    DiscoveryCar getDiscoveryCar(DiscoveryCar discoverycar);

    /**
     * 新增
     * @param discoverycar
    * @author luolinqiang
    */
    Integer saveDiscoveryCar(DiscoveryCar discoverycar);

    /**
     * 修改
     * @param discoverycar
     */
    void modifyDiscoveryCar(DiscoveryCar discoverycar);

    /**
     * 删除
     * @param discoverycar
     */
    void removeDiscoveryCar(DiscoveryCar discoverycar);
}