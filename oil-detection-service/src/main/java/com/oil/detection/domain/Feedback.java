package com.oil.detection.domain;

import java.io.Serializable;
import java.util.Date;

public class Feedback implements Serializable {

private Long id;
private Long userId;
private String content;
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
public Long getUserId() {
return userId;
}

public void setUserId(Long userId) {
this.userId = userId;
}
public String getContent() {
return content;
}

public void setContent(String content) {
this.content = content;
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