package com.oil.detection.web.controller;

import com.oil.detection.common.CommonConstants;
import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.Purchase;
import com.oil.detection.domain.PurchaseSupplierRel;
import com.oil.detection.domain.page.QueryPurchase;
import com.oil.detection.domain.result.RsOwnPurchase;
import com.oil.detection.service.PurchaseService;
import com.oil.detection.service.PurchaseSupplierRelService;
import com.oil.detection.util.ConstantTransferUtil;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 采购信息相关接口
 */
@Controller
@RequestMapping(value = "/purchase")
public class PurchaseController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(PurchaseController.class);
    @Resource
    private PurchaseService purchaseService;
    @Resource
    private PurchaseSupplierRelService relService;

    /**
     * 采购信息分页列表
     *
     * @param purchase
     * @return
     */
    @RequestMapping(value = "/all", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO pageListPurchase(QueryPurchase purchase) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        List<Purchase> purchases = purchaseService.pageListPurchase(purchase);
        responsesDTO.setData(purchases);
        return responsesDTO;
    }

    /**
     * 我的采购
     *
     * @param purchase
     * @return
     */
    @RequestMapping(value = "/own", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO own(HttpServletRequest request, HttpServletResponse response, QueryPurchase purchase) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        purchase.setUserId(super.getUserInfo(request).getId());
        List<Purchase> purchases = purchaseService.pageListPurchase(purchase);

        List<RsOwnPurchase> rsList = new ArrayList<RsOwnPurchase>();
        for (Purchase purchaseDb : purchases) {
            RsOwnPurchase rsOwnPurchase = new RsOwnPurchase();
            BeanUtils.copyProperties(purchaseDb, rsOwnPurchase);
            rsOwnPurchase.setPurchaseTypeDesc(ConstantTransferUtil.purchaseType(purchaseDb.getPurchaseType()));
            rsOwnPurchase.setStateDesc(ConstantTransferUtil.purchaseState(purchaseDb.getState()));
            rsList.add(rsOwnPurchase);
        }
        responsesDTO.setData(rsList);
        return responsesDTO;
    }

    /**
     * 我要供货
     *
     * @param purchasesupplierrel
     * @return
     */
    @RequestMapping(value = "/supply", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO supply(PurchaseSupplierRel purchasesupplierrel) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        PurchaseSupplierRel rel = new PurchaseSupplierRel();
        rel.setType(CommonConstants.Common.TYPE_1);
        rel.setPurchaseId(purchasesupplierrel.getPurchaseId());
        rel.setSupplierId(purchasesupplierrel.getSupplierId());
        int count = relService.countPurchaseSupplierRel(purchasesupplierrel);
        if (count == 0) {
            purchasesupplierrel.setCreateTime(new Date());
            relService.savePurchaseSupplierRel(purchasesupplierrel);
        }
        return responsesDTO;
    }

    /**
     * 采购及免费代采提交
     *
     * @param purchase
     * @return
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO savePurchase(Purchase purchase) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        purchaseService.savePurchase(purchase);
        return responsesDTO;
    }
}
