package com.oil.detection.domain;

import java.io.Serializable;
import java.util.Date;

public class DiscoveryCar implements Serializable {

    private Long id;
    private Long userId;
    private String goods;
    private Double weight;
    private Double price;
    private Long needTank;
    private Date deliveryTime;
    private String remark;
    private String senderName;
    private String senderPhone;
    private Long origin;
    private Long destination;
    private Integer downloadContract;
    private Date createTime;
    private Date updateTime;
    private Integer state;
    private String downEmail;

    public String getDownEmail() {
        return downEmail;
    }

    public void setDownEmail(String downEmail) {
        this.downEmail = downEmail;
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

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getNeedTank() {
        return needTank;
    }

    public void setNeedTank(Long needTank) {
        this.needTank = needTank;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public Long getOrigin() {
        return origin;
    }

    public void setOrigin(Long origin) {
        this.origin = origin;
    }

    public Long getDestination() {
        return destination;
    }

    public void setDestination(Long destination) {
        this.destination = destination;
    }

    public Integer getDownloadContract() {
        return downloadContract;
    }

    public void setDownloadContract(Integer downloadContract) {
        this.downloadContract = downloadContract;
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
}