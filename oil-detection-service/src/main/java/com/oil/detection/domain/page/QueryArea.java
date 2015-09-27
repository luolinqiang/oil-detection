package com.oil.detection.domain.page;

import java.io.Serializable;
import java.util.Date;

public class QueryArea extends BasePagingInfo implements Serializable {

private Long id;
private Long pid;
private Integer areaLevel;
private String areaCode;
private String areaName;
private Integer areaSort;
private String longitude;
private String latitude;
private Date createTime;
private Date updateTime;
private Long state;
private String remark;

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}
public Long getPid() {
return pid;
}

public void setPid(Long pid) {
this.pid = pid;
}
public Integer getAreaLevel() {
return areaLevel;
}

public void setAreaLevel(Integer areaLevel) {
this.areaLevel = areaLevel;
}
public String getAreaCode() {
return areaCode;
}

public void setAreaCode(String areaCode) {
this.areaCode = areaCode;
}
public String getAreaName() {
return areaName;
}

public void setAreaName(String areaName) {
this.areaName = areaName;
}
public Integer getAreaSort() {
return areaSort;
}

public void setAreaSort(Integer areaSort) {
this.areaSort = areaSort;
}
public String getLongitude() {
return longitude;
}

public void setLongitude(String longitude) {
this.longitude = longitude;
}
public String getLatitude() {
return latitude;
}

public void setLatitude(String latitude) {
this.latitude = latitude;
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
public Long getState() {
return state;
}

public void setState(Long state) {
this.state = state;
}
public String getRemark() {
return remark;
}

public void setRemark(String remark) {
this.remark = remark;
}
}