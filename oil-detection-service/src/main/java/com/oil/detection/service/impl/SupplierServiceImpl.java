package com.oil.detection.service.impl;

import com.oil.detection.dao.SupplierMapper;
import com.oil.detection.domain.Supplier;
import com.oil.detection.domain.page.QuerySupplier;
import com.oil.detection.service.SupplierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Resource
    private SupplierMapper supplierMapper;

    @Override
    public List<Supplier> listSupplier(Supplier supplier) {
        return supplierMapper.listSupplier(supplier);
    }

    @Override
    public int countSupplier(Supplier supplier) {
        return supplierMapper.countSupplier(supplier);
    }

    @Override
    public List<Supplier> pageListSupplier(QuerySupplier supplier) {
        return supplierMapper.pageListSupplier(supplier);
    }

    @Override
    public Supplier getSupplier(Supplier supplier) {
        return supplierMapper.getSupplier(supplier);
    }

    @Override
    public Integer saveSupplier(Supplier supplier) {
        return supplierMapper.saveSupplier(supplier);
    }

    @Override
    public void modifySupplier(Supplier supplier) {
        supplierMapper.modifySupplier(supplier);
    }

    @Override
    public void removeSupplier(Supplier supplier) {
        supplierMapper.removeSupplier(supplier);
    }
}
