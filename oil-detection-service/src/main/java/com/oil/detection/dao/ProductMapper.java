package com.oil.detection.dao;

import com.oil.detection.domain.Product;
import com.oil.detection.domain.page.QueryProduct;

import java.util.List;

public interface ProductMapper {

    /**
     * 查询列表
     *
     * @param product
     * @return
     */
    List<Product> listProduct(Product product);

    /**
     * 查询总数
     *
     * @param product
     * @return
     */
    int countProduct(Product product);

    /**
     * 分页列表
     *
     * @param product
     * @return
     */
    List<Product> pageListProduct(QueryProduct product);

    /**
     * 查询
     *
     * @param product
     * @return
     */
    Product getProduct(Product product);

    /**
     * 新增
     *
     * @param product
     * @author luolinqiang
     */
    Integer saveProduct(Product product);

    /**
     * 修改
     *
     * @param product
     */
    void modifyProduct(Product product);

    /**
     * 删除
     *
     * @param product
     */
    void removeProduct(Product product);
}