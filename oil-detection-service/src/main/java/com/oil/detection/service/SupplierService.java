package com.oil.detection.service;

import com.oil.detection.domain.Supplier;
import com.oil.detection.domain.page.QuerySupplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> listSupplier(Supplier supplier);

    int countSupplier(Supplier supplier);

    List<Supplier> pageListSupplier(QuerySupplier supplier);

    Supplier getSupplier(Supplier supplier);

    Integer saveSupplier(Supplier supplier);

    void modifySupplier(Supplier supplier);

    void removeSupplier(Supplier supplier);
}
