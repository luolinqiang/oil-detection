package com.oil.detection.service;

import com.oil.detection.domain.Product;
import com.oil.detection.domain.page.QueryProduct;

import java.util.List;

public interface ProductService {
    List<Product> listProduct(Product product);

    int countProduct(Product product);

    List<Product> pageListProduct(QueryProduct product);

    Product getProduct(Product product);

    Integer saveProduct(Product product);

    void modifyProduct(Product product);

    void removeProduct(Product product);
}
