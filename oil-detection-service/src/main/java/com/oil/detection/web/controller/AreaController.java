package com.oil.detection.web.controller;

import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.Area;
import com.oil.detection.domain.page.QueryArea;
import com.oil.detection.service.AreaService;
import com.oil.detection.solr.SolrAreaUtils;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 地域相关接口
 */
@Controller
@RequestMapping(value = "/area")
public class AreaController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(AreaController.class);
    @Resource
    private AreaService areaService;

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO listArea(Area area) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        List<Area> areas = areaService.listArea(area);
        responsesDTO.setData(areas);
        return responsesDTO;
    }

    @RequestMapping(value = "/get", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO getArea(Area areaQuery) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        Area area = areaService.getArea(areaQuery);
        responsesDTO.setData(area);
        return responsesDTO;
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO saveArea(Area area) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        area.setCreateTime(new Date());
        areaService.saveArea(area);
        return responsesDTO;
    }


    @RequestMapping(value = "/modify", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO modifyArea(Area area) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        areaService.modifyArea(area);
        return responsesDTO;
    }

    @RequestMapping(value = "/search", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO searchArea(QueryArea queryArea) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        List<Area> areas = SolrAreaUtils.searchIndexs(queryArea);
        responsesDTO.setData(areas);
        return responsesDTO;
    }

    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO indexArea() {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        areaService.indexArea();
        return responsesDTO;
    }
}
