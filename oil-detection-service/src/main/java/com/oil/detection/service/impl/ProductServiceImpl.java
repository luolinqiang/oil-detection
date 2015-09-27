package com.oil.detection.service.impl;

import com.oil.detection.dao.ProductMapper;
import com.oil.detection.domain.Product;
import com.oil.detection.domain.page.QueryProduct;
import com.oil.detection.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> listProduct(Product product) {
        return productMapper.listProduct(product);
    }

    @Override
    public int countProduct(Product product) {
        return productMapper.countProduct(product);
    }

    @Override
    public List<Product> pageListProduct(QueryProduct product) {
        return productMapper.pageListProduct(product);
    }

    @Override
    public Product getProduct(Product product) {
        return productMapper.getProduct(product);
    }

    @Override
    public Integer saveProduct(Product product) {
        return productMapper.saveProduct(product);
    }

    @Override
    public void modifyProduct(Product product) {
        productMapper.modifyProduct(product);
    }

    @Override
    public void removeProduct(Product product) {
        productMapper.removeProduct(product);
    }
}
