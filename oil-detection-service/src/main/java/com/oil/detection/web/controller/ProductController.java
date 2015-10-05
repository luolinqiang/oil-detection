package com.oil.detection.web.controller;

import com.oil.detection.common.CommonConstants;
import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.Pic;
import com.oil.detection.domain.Product;
import com.oil.detection.domain.Supplier;
import com.oil.detection.domain.UserAttention;
import com.oil.detection.domain.result.RsProductDetail;
import com.oil.detection.service.PicService;
import com.oil.detection.service.ProductService;
import com.oil.detection.service.SupplierService;
import com.oil.detection.service.UserAttentionService;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商品相关接口
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(ProductController.class);
    @Resource
    private ProductService productService;
    @Resource
    private SupplierService supplierService;
    @Resource
    private PicService picService;
    @Resource
    private UserAttentionService userAttentionService;

    /**
     * 商品详情接口
     *
     * @param product
     * @return
     */
    @RequestMapping(value = "/detail", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO detail(HttpServletRequest request, Product product) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        RsProductDetail rsProductDetail = new RsProductDetail();

        Supplier supplier = new Supplier();
        supplier.setId(product.getSupplierId());
        Supplier supplierDb = supplierService.getSupplier(supplier);

        Product productDb = productService.getProduct(product);

        Pic pic = new Pic();
        pic.setType(CommonConstants.Common.TYPE_2);
        pic.setOwnerId(product.getId());
        List<Pic> pics = picService.listPic(pic);

        UserAttention attention = new UserAttention();
        attention.setUserId(super.getUserInfo(request).getId());
        attention.setSupplierId(product.getSupplierId());
        attention.setProductId(product.getId());
        int count = userAttentionService.countUserAttention(attention);
        if (count == 0) {
            rsProductDetail.setAttention(false);
        } else {
            rsProductDetail.setAttention(true);
        }

        BeanUtils.copyProperties(productDb, rsProductDetail);
        rsProductDetail.setSupplier(supplierDb);
        rsProductDetail.setPics(pics);

        responsesDTO.setData(rsProductDetail);
        return responsesDTO;
    }

    /**
     * 保存商品接口
     *
     * @param product
     * @return
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponsesDTO saveProduct(Product product) {
        ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ACTIVE_SUCCESS);
        productService.saveProduct(product);
        return responsesDTO;
    }
}
