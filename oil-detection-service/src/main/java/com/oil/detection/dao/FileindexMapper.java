package com.oil.detection.dao;

import com.oil.detection.domain.Fileindex;

import java.util.List;

public interface FileindexMapper {

    /**
     * 查询列表
     *
     * @param fileindex
     * @return
     */
    List<Fileindex> listFileindex(Fileindex fileindex);
}