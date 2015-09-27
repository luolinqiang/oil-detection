/**
 * 
 */
package com.oil.detection.common;

import java.io.Serializable;

//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponsesDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int code = ReturnCode.ACTIVE_FAILURE.code();
	private Object data;

	public ResponsesDTO(ReturnCode returnCode) {
		this.code = returnCode.code();
		this.data = returnCode.msg();
	}

	public void setReturnCode(ReturnCode returnCode) {
		this.code = returnCode.code();
		this.data = returnCode.msg();
	}

	public ReturnCode nowReturnCode() {// 此处不能写getxx,会被spring 识别然后返回出去
		return ReturnCode.codeToEnum(code);
	}

	public ResponsesDTO(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
