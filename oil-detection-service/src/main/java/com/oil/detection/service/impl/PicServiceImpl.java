package com.oil.detection.service.impl;

import com.oil.detection.dao.PicMapper;
import com.oil.detection.domain.Pic;
import com.oil.detection.domain.page.QueryPic;
import com.oil.detection.service.PicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PicServiceImpl implements PicService {

    @Resource
    private PicMapper picMapper;

    @Override
    public List<Pic> listPic(Pic pic) {
        return picMapper.listPic(pic);
    }

    @Override
    public List<Pic> listPicNoContent(Pic pic) {
        return picMapper.listPicNoContent(pic);
    }

    @Override
    public int countPic(Pic pic) {
        return picMapper.countPic(pic);
    }

    @Override
    public List<Pic> pageListPic(QueryPic pic) {
        return picMapper.pageListPic(pic);
    }

    @Override
    public Pic getPic(Pic pic) {
        return picMapper.getPic(pic);
    }

    @Override
    public Integer savePic(Pic pic) {
        return picMapper.savePic(pic);
    }

    @Override
    public void modifyPic(Pic pic) {
        picMapper.modifyPic(pic);
    }

    @Override
    public void removePic(Pic pic) {
        picMapper.removePic(pic);
    }
}
