package com.oil.detection.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.oil.detection.common.CommonConstants;
import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.Dictionary;
import com.oil.detection.domain.Purchase;
import com.oil.detection.domain.PurchaseSupplierRel;
import com.oil.detection.domain.page.QueryPurchase;
import com.oil.detection.domain.result.RsOwnPurchase;
import com.oil.detection.service.DictionaryService;
import com.oil.detection.service.PurchaseService;
import com.oil.detection.service.PurchaseSupplierRelService;
import com.oil.detection.util.ConstantTransferUtil;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 采购信息相关接口
 */
@Controller
public class PurchaseController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(PurchaseController.class);
    @Resource
    private PurchaseService purchaseService;
    @Resource
    private PurchaseSupplierRelService relService;

    @Resource
    private DictionaryService dictionaryService;

    @RequestMapping(value = "/req-{type}")
    public String req(@PathVariable("type") String type, Model model, HttpServletRequest request) throws Exception {
        Dictionary dictionary = new Dictionary();
        dictionary.setGroupCode("item_class");
        dictionary.setCode(type);
        dictionary = dictionaryService.getDictionary(dictionary);
        model.addAttribute("dic", dictionary);
        return "req/req";
    }

    /**
     * 采购及免费代采提交
     *
     * @param purchase
     * @return
     */
    @RequestMapping(value = "/req/ajax/submit", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO savePurchase(Purchase purchase, HttpServletRequest request, HttpServletResponse response) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        purchase.setCreateTime(new Date());
        purchase.setUserId(super.getUserInfo(request).getId());
        purchaseService.savePurchase(purchase);
        return responsesDTO;
    }

    @RequestMapping(value = "/user-reqs")
    public String userReqs(Model model, HttpServletRequest request) throws Exception {
        Dictionary dictionary = new Dictionary();
        dictionary.setGroupName("质量标准");
        List<Dictionary> dictionarys = dictionaryService.listDictionary(dictionary);
        Map<Long, String> standardMap = new HashMap<Long, String>();
        for(Dictionary dic : dictionarys){
            standardMap.put(dic.getId(), dic.getName());
        }
        model.addAttribute("standardMap", JSONObject.toJSONString(standardMap));
        dictionary.setGroupName("产品型号");
        dictionarys = dictionaryService.listDictionary(dictionary);
        Map<Long, String> modelMap = new HashMap<Long, String>();
        for(Dictionary dic : dictionarys){
            modelMap.put(dic.getId(), dic.getName());
        }
        model.addAttribute("modelMap", JSONObject.toJSONString(modelMap));
        return  "req/user-reqs";
    }


    /**
     * 我的采购
     *
     * @param purchase
     * @return
     */
    @RequestMapping(value = "/req/ajax/own", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO own(QueryPurchase purchase, HttpServletRequest request, HttpServletResponse response) {
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
     * 采购信息分页列表
     *
     * @param purchase
     * @return
     */
    @RequestMapping(value = "/purchase/all", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO pageListPurchase(QueryPurchase purchase) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        List<Purchase> purchases = purchaseService.pageListPurchase(purchase);
        responsesDTO.setData(purchases);
        return responsesDTO;
    }

    /**
     * 我要供货
     *
     * @param purchasesupplierrel
     * @return
     */
    @RequestMapping(value = "/purchase/supply", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
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
}
