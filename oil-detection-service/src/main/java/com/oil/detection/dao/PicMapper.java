package com.oil.detection.dao;

import com.oil.detection.domain.Pic;
import com.oil.detection.domain.page.QueryPic;

import java.util.List;

public interface PicMapper {

    /**
     * 查询列表
     *
     * @param pic
     * @return
     */
    List<Pic> listPic(Pic pic);

    /**
     * 查询总数
     *
     * @param pic
     * @return
     */
    int countPic(Pic pic);

    /**
     * 分页列表
     *
     * @param pic
     * @return
     */
    List<Pic> pageListPic(QueryPic pic);

    /**
     * 查询
     *
     * @param pic
     * @return
     */
    Pic getPic(Pic pic);

    /**
     * 新增
     *
     * @param pic
     * @author luolinqiang
     */
    Integer savePic(Pic pic);

    /**
     * 修改
     *
     * @param pic
     */
    void modifyPic(Pic pic);

    /**
     * 删除
     *
     * @param pic
     */
    void removePic(Pic pic);
}