package com.oil.detection.web.controller;

import com.oil.detection.common.CommonConstants;
import com.oil.detection.domain.HomeSetting;
import com.oil.detection.domain.Pic;
import com.oil.detection.domain.Product;
import com.oil.detection.domain.Supplier;
import com.oil.detection.domain.result.RsProductDetail;
import com.oil.detection.service.HomeSettingService;
import com.oil.detection.service.PicService;
import com.oil.detection.service.ProductService;
import com.oil.detection.service.SupplierService;
import com.oil.detection.web.base.BaseControllor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: guowenjuan
 * Date: 15-9-29
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class FrontController extends BaseControllor {

    @Resource
    private PicService picService;
    @Resource
    private ProductService productService;
    @Resource
    private SupplierService supplierService;

    @RequestMapping(value = "/search-diesel")
    public String searchdiesel(Model model, HttpServletRequest request) throws Exception {


        return "search/diesel";
    }

    @RequestMapping(value = "/search-petrol")
    public String searchpetrol(Model model, HttpServletRequest request) throws Exception {


        return "search/petrol";
    }

    @RequestMapping(value = "/shop-{shopId}")
    public String shopDetail(@PathVariable("shopId") Long shopId, Model model, HttpServletRequest request) throws Exception {
        Supplier supplier = new Supplier();
        supplier.setState(CommonConstants.Common.STATE_2);
        supplier.setId(shopId);
        supplier = supplierService.getSupplier(supplier);
        model.addAttribute("supplier", supplier);
        return "shop";
    }

    @RequestMapping(value = "/product-{pid}")
    public String productDetail(@PathVariable("pid") Long pid, Model model, HttpServletRequest request) throws Exception {
        Product product = new Product();
        product.setId(pid);
        RsProductDetail rsProductDetail = new RsProductDetail();
        Product productDb = productService.getProduct(product);
        BeanUtils.copyProperties(productDb, rsProductDetail);

        Supplier supplier = new Supplier();
        supplier.setId(productDb.getSupplierId());
        Supplier supplierDb = supplierService.getSupplier(supplier);
        rsProductDetail.setSupplier(supplierDb);

        Pic pic = new Pic();
        pic.setType(CommonConstants.Common.TYPE_2);
        pic.setOwnerId(pid);
        List<Pic> pics = picService.listPicNoContent(pic);
        rsProductDetail.setPics(pics);

        model.addAttribute("product", rsProductDetail);
        return "product";
    }

    @RequestMapping(value = "/zhaoche")
    public String findTruck(Model model, HttpServletRequest request) throws Exception {


        return "publish/zhaoche";
    }
}
