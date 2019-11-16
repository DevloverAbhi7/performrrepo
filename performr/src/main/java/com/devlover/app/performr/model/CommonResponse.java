package com.devlover.app.performr.model;

import java.io.Serializable;

public class CommonResponse implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String code;
	String message;
	Object data;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CommonResponse(String code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	
	

}
