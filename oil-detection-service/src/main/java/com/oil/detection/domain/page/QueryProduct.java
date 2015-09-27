package com.oil.detection.domain.page;

import java.io.Serializable;
import java.util.Date;

public class QueryProduct extends BasePagingInfo implements Serializable {

    private Long id;
    private Integer type;
    private Long inventory;
    private String name;
    private java.math.BigDecimal price;
    private Long qualityStandard;
    private Long productModel;
    private Long productAddr;
    private Double density;
    private java.math.BigDecimal flashPoint;
    private java.math.BigDecimal cetaneNumber;
    private java.math.BigDecimal cfpp;
    private Long color;
    private String boilingRange;
    private Integer detailsType;
    private String details;
    private java.math.BigDecimal sulphurContent;
    private Date createTime;
    private Date updateTime;
    private Integer state;
    private String remark;
    private Long supplierId;

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

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.math.BigDecimal getPrice() {
        return price;
    }

    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }

    public Long getQualityStandard() {
        return qualityStandard;
    }

    public void setQualityStandard(Long qualityStandard) {
        this.qualityStandard = qualityStandard;
    }

    public Long getProductModel() {
        return productModel;
    }

    public void setProductModel(Long productModel) {
        this.productModel = productModel;
    }

    public Long getProductAddr() {
        return productAddr;
    }

    public void setProductAddr(Long productAddr) {
        this.productAddr = productAddr;
    }

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    public java.math.BigDecimal getFlashPoint() {
        return flashPoint;
    }

    public void setFlashPoint(java.math.BigDecimal flashPoint) {
        this.flashPoint = flashPoint;
    }

    public java.math.BigDecimal getCetaneNumber() {
        return cetaneNumber;
    }

    public void setCetaneNumber(java.math.BigDecimal cetaneNumber) {
        this.cetaneNumber = cetaneNumber;
    }

    public java.math.BigDecimal getCfpp() {
        return cfpp;
    }

    public void setCfpp(java.math.BigDecimal cfpp) {
        this.cfpp = cfpp;
    }

    public Long getColor() {
        return color;
    }

    public void setColor(Long color) {
        this.color = color;
    }

    public String getBoilingRange() {
        return boilingRange;
    }

    public void setBoilingRange(String boilingRange) {
        this.boilingRange = boilingRange;
    }

    public Integer getDetailsType() {
        return detailsType;
    }

    public void setDetailsType(Integer detailsType) {
        this.detailsType = detailsType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public java.math.BigDecimal getSulphurContent() {
        return sulphurContent;
    }

    public void setSulphurContent(java.math.BigDecimal sulphurContent) {
        this.sulphurContent = sulphurContent;
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}