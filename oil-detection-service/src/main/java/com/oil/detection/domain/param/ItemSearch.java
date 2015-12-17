package com.oil.detection.domain.param;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: guowenjuan
 * Date: 15-12-16
 * Time: 下午5:42
 * To change this template use File | Settings | File Templates.
 */
public class ItemSearch implements Serializable {

    private Long type;
    private Long standard;
    private Long model;
    private String shopName;
    private Long position;
    private String sort;
    private String sortType;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getStandard() {
        return standard;
    }

    public void setStandard(Long standard) {
        this.standard = standard;
    }

    public Long getModel() {
        return model;
    }

    public void setModel(Long model) {
        this.model = model;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
