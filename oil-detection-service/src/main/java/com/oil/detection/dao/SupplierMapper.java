package com.oil.detection.dao;

import com.oil.detection.domain.Supplier;
import com.oil.detection.domain.page.QuerySupplier;

import java.util.List;

public interface SupplierMapper {

    /**
     * 查询列表
     *
     * @param supplier
     * @return
     */
    List<Supplier> listSupplier(Supplier supplier);

    /**
     * 查询总数
     *
     * @param supplier
     * @return
     */
    int countSupplier(Supplier supplier);

    /**
     * 分页列表
     *
     * @param supplier
     * @return
     */
    List<Supplier> pageListSupplier(QuerySupplier supplier);

    /**
     * 查询
     *
     * @param supplier
     * @return
     */
    Supplier getSupplier(Supplier supplier);

    /**
     * 新增
     *
     * @param supplier
     * @author luolinqiang
     */
    Integer saveSupplier(Supplier supplier);

    /**
     * 修改
     *
     * @param supplier
     */
    void modifySupplier(Supplier supplier);

    /**
     * 删除
     *
     * @param supplier
     */
    void removeSupplier(Supplier supplier);
}