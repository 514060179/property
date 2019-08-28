package com.simon.dal.vo;

import io.swagger.annotations.ApiModelProperty;

public class BaseClaims extends BaseQueryParam {
	
	@ApiModelProperty(hidden=true)
	private String userId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
