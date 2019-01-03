package com.simon.dal.vo;

import io.swagger.annotations.ApiModelProperty;

public class BaseClaims extends BaseQueryParam {
	
	@ApiModelProperty(hidden=true)
	private String userId;
	
	@ApiModelProperty(hidden=true)
	private String communityId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
}
