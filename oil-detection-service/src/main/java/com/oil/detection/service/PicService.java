package com.oil.detection.service;

import com.oil.detection.domain.Pic;
import com.oil.detection.domain.page.QueryPic;

import java.util.List;

public interface PicService {
    List<Pic> listPic(Pic pic);

    List<Pic> listPicNoContent(Pic pic);

    int countPic(Pic pic);

    List<Pic> pageListPic(QueryPic pic);

    Pic getPic(Pic pic);

    Integer savePic(Pic pic);

    void modifyPic(Pic pic);

    void removePic(Pic pic);
}
