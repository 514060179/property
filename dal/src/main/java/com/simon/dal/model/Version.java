package com.simon.dal.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Version",description="版本控制")
public class Version {
	
	@ApiModelProperty(value="id",readOnly=true)
	private String versionId;
	
	@ApiModelProperty(value="版本号")
	private String versionNumber;
	
	@ApiModelProperty(value="版本信息")
	private String versionDescribe;
	
	@ApiModelProperty(value="是否强制更新")
	private Boolean forcedUpdate;
	
	@ApiModelProperty(value="设备类型（1.ios，2.android）")
	private String deviceType;
	
	@ApiModelProperty(value="创建时间",readOnly=true)
	private Date createTime;
	
	@ApiModelProperty(value="更新时间",readOnly=true)
	private Date updateTime;

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getVersionDescribe() {
		return versionDescribe;
	}

	public void setVersionDescribe(String versionDescribe) {
		this.versionDescribe = versionDescribe;
	}

	public Boolean getForcedUpdate() {
		return forcedUpdate;
	}

	public void setForcedUpdate(Boolean forcedUpdate) {
		this.forcedUpdate = forcedUpdate;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
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
}
