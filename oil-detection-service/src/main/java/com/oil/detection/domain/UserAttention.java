package com.oil.detection.domain;

import java.io.Serializable;
import java.util.Date;

public class UserAttention implements Serializable {

    private Long id;
    private Long userId;
    private Long supplierId;
    private Long productId;
    private Date createTime;
    private Date updateTime;
    private Integer state;
    private String remark;
    private String supplierIdDesc;
    private String timeDesc;

    public String getSupplierIdDesc() {
        return supplierIdDesc;
    }

    public void setSupplierIdDesc(String supplierIdDesc) {
        this.supplierIdDesc = supplierIdDesc;
    }

    public String getTimeDesc() {
        return timeDesc;
    }

    public void setTimeDesc(String timeDesc) {
        this.timeDesc = timeDesc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}