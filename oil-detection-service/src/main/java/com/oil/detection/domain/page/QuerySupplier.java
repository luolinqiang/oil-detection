package com.oil.detection.domain.page;

import java.io.Serializable;
import java.util.Date;

public class QuerySupplier extends BasePagingInfo implements Serializable {

    private Long id;
    private Integer type;
    private String companyName;
    private String companyLogo;
    private String contacts;
    private String phone;
    private Long areaId;
    private String address;
    private String operateProduct;
    private String oilName;
    private String oilAddress;
    private Long companyType;
    private String companyDesc;
    private Date createTime;
    private Date updateTime;
    private Integer state;
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperateProduct() {
        return operateProduct;
    }

    public void setOperateProduct(String operateProduct) {
        this.operateProduct = operateProduct;
    }

    public String getOilName() {
        return oilName;
    }

    public void setOilName(String oilName) {
        this.oilName = oilName;
    }

    public String getOilAddress() {
        return oilAddress;
    }

    public void setOilAddress(String oilAddress) {
        this.oilAddress = oilAddress;
    }

    public Long getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Long companyType) {
        this.companyType = companyType;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
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