package com.oil.detection.web.controller;

import com.oil.detection.common.CommonConstants;
import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.*;
import com.oil.detection.domain.page.QueryUserAttention;
import com.oil.detection.domain.result.RsOfferProduct;
import com.oil.detection.service.PicService;
import com.oil.detection.service.ProductService;
import com.oil.detection.service.SupplierService;
import com.oil.detection.service.UserAttentionService;
import com.oil.detection.util.DateUtils;
import com.oil.detection.util.Precondition;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 关注相关接口
 */
@Controller
public class UserAttentionController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(UserAttentionController.class);
    @Resource
    private UserAttentionService userattentionService;
    @Resource
    private SupplierService supplierService;
    @Resource
    private ProductService productService;
    @Resource
    private PicService picService;

    @RequestMapping(value = "/user-watch")
    public String userWatch(Model model, HttpServletRequest request) throws Exception {

        return "user/watch";
    }

    @RequestMapping(value = "/watch/ajax/getlist", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO pageListUserAttention(QueryUserAttention userattention, HttpServletRequest request) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        userattention.setUserId(super.getUserInfo(request).getId());
        List<RsOfferProduct> rsList = new ArrayList<RsOfferProduct>();
        List<UserAttention> userattentions = userattentionService.pageListUserAttention(userattention);
        for (UserAttention userAttention : userattentions) {
            Product product = new Product();
            product.setId(userAttention.getProductId());
            Product productDb = productService.getProduct(product);

            RsOfferProduct rsOfferProduct = new RsOfferProduct();
            BeanUtils.copyProperties(productDb, rsOfferProduct);

            Supplier supplier = new Supplier();
            supplier.setId(userAttention.getSupplierId());
            rsOfferProduct.setCompanyName(supplierService.getSupplier(supplier).getCompanyName());
            rsOfferProduct.setTimeDesc(DateUtils.getTimeDesc(userAttention.getUpdateTime()));

            Pic pic = new Pic();
            pic.setType(CommonConstants.Common.TYPE_2);
            pic.setOwnerId(userAttention.getProductId());
            List<Pic> pics = picService.listPic(pic);
            if(pics!=null && !pics.isEmpty()){
                rsOfferProduct.setPicUrl(pics.get(0).getId()+"");
            }

            rsList.add(rsOfferProduct);
        }

        UserAttention userAttentionPage = new UserAttention();
        BeanUtils.copyProperties(userattention, userAttentionPage);
        userattention.setTotalRows(userattentionService.countUserAttention(userAttentionPage));

        responsesDTO.setData(rsList);
        return responsesDTO;
    }

    @RequestMapping(value = "/watch/ajax/get", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO getUserAttention(HttpServletRequest request, UserAttention userattentionQuery) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        userattentionQuery.setUserId(super.getUserInfo(request).getId());
        UserAttention userattention = userattentionService.getUserAttention(userattentionQuery);
        responsesDTO.setData(userattention);
        return responsesDTO;
    }

    @RequestMapping(value = "/watch/ajax/follow", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO follow(HttpServletRequest request, UserAttention userattention) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        Precondition.assertionNotBlank(userattention, "supplierId");
        Precondition.assertionNotBlank(userattention, "productId");
        userattention.setUserId(super.getUserInfo(request).getId());
        userattention.setCreateTime(new Date());
        Integer aid = userattentionService.saveUserAttention(userattention);
        responsesDTO.setData(aid);
        return responsesDTO;
    }


    @RequestMapping(value = "/watch/ajax/unFollow", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO unFollow(HttpServletRequest request, UserAttention userattention) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        Precondition.assertionNotBlank(userattention, "id");
        userattentionService.removeUserAttention(userattention);
        return responsesDTO;
    }
}
