package com.simon.dal.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Image", description="图片")
public class Image {
	
	@ApiModelProperty(name="图片id",readOnly=true)
	private String imageId;
	
	@ApiModelProperty("图片url")
	private String imageUrl;
	
	@ApiModelProperty("对象id(对应的业务id，如：placeId)")
	private String objectId;
	
	@ApiModelProperty("图片类型0投诉/保修1场所2公告3其他")
	private String imageType;
	
	@ApiModelProperty(name="创建时间",readOnly=true)
    private Date createTime;

    @ApiModelProperty(name="更新时间",readOnly=true)
    private Date updateTime;

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
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
