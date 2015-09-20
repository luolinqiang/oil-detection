package com.jd.jss.index.dao;

import com.jd.jss.index.domain.Fileindex;

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