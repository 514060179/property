package com.simon.dal.vo;

import com.simon.dal.model.Visitor;

import io.swagger.annotations.ApiModelProperty;

public class VisitorWithToken extends Visitor {
	
	@ApiModelProperty(readOnly=true)
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
