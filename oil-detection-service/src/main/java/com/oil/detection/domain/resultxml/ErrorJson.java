package com.oil.detection.domain.resultxml;

import com.oil.detection.enums.ErrorEnum;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "error")
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ErrorJson extends Writeable {
    @XmlElement
    public String code = "";
    @XmlElement
    public String message = "";
    @XmlElement
    public String resource = "";
    @XmlElement
    public String requestId = "";
    @XmlElement
    public Object data;

    public ErrorJson(ErrorEnum errorEnum, String requestId, String resource) {
        this.code = errorEnum.getCode() + "";
        this.message = errorEnum.getMsg();
        this.resource = resource;
        this.requestId = requestId;
    }

    public ErrorJson(ErrorEnum errorEnum, String requestId) {
        this.code = errorEnum.getCode() + "";
        this.message = errorEnum.getMsg();
        this.requestId = requestId;
    }

    public ErrorJson() {
    }

}
