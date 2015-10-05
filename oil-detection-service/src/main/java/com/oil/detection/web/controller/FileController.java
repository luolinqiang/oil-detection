package com.oil.detection.web.controller;

import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.Pic;
import com.oil.detection.service.PicService;
import com.oil.detection.web.base.BaseControllor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping(value = "/pic")
public class FileController extends BaseControllor {
    @Resource
    private PicService picService;

    @RequestMapping(value = "/upload")
    @ResponseBody
    public ResponsesDTO uplaod(HttpServletRequest request, HttpServletResponse response, Pic pic)
            throws Exception {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得文件：
        MultipartFile file = multipartRequest.getFile("file");
        if (file != null) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            if (!file.isEmpty()) {
                String picUrl = UUID.randomUUID().toString();
                byte[] bytes = file.getBytes();
                pic.setContent(bytes);
                pic.setPicUrl(picUrl);
                picService.savePic(pic);
                responsesDTO.setData(picUrl);
            }
        }
        return responsesDTO;
    }

//    @RequestMapping(value = "/show/{picUrl}")
//    public void show(HttpServletRequest request, HttpServletResponse response, @PathVariable("picUrl") String picUrl)
//            throws Exception {
//        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Content-Type", "image/png");
//        Pic pic = new Pic();
//        pic.setPicUrl(picUrl);
//        Pic picDb = picService.getPic(pic);
//        if (picDb != null && picDb.getContent() != null) {
//            ServletOutputStream os = null;
//            try {
//                os = response.getOutputStream();
//                os.write(picDb.getContent());
//                os.flush();
//            } finally {
//                if (os != null) {
//                    os.close();
//                }
//            }
//        }
//    }

    @RequestMapping(value = "/show/{id}")
    public void showId(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id)
            throws Exception {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Content-Type", "image/png");
        Pic pic = new Pic();
        pic.setId(id);
        Pic picDb = picService.getPic(pic);
        if (picDb != null && picDb.getContent() != null) {
            ServletOutputStream os = null;
            try {
                os = response.getOutputStream();
                os.write(picDb.getContent());
                os.flush();
            } finally {
                if (os != null) {
                    os.close();
                }
            }
        }
    }
}
