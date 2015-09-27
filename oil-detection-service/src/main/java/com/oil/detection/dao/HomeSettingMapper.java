package com.oil.detection.dao;

import com.oil.detection.domain.HomeSetting;
import com.oil.detection.domain.page.QueryHomeSetting;

import java.util.List;

public interface HomeSettingMapper {

    /**
     * 查询列表
     *
     * @param homesetting
     * @return
     */
    List<HomeSetting> listHomeSetting(HomeSetting homesetting);

    /**
     * 查询总数
     *
     * @param homesetting
     * @return
     */
    int countHomeSetting(HomeSetting homesetting);

    /**
     * 分页列表
     *
     * @param homesetting
     * @return
     */
    List<HomeSetting> pageListHomeSetting(QueryHomeSetting homesetting);

    /**
     * 查询
     *
     * @param homesetting
     * @return
     */
    HomeSetting getHomeSetting(HomeSetting homesetting);

    /**
     * 新增
     *
     * @param homesetting
     * @author luolinqiang
     */
    Integer saveHomeSetting(HomeSetting homesetting);

    /**
     * 修改
     *
     * @param homesetting
     */
    void modifyHomeSetting(HomeSetting homesetting);

    /**
     * 删除
     *
     * @param homesetting
     */
    void removeHomeSetting(HomeSetting homesetting);
}