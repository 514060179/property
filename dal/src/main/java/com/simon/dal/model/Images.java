package com.simon.dal.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Image", description="图片")
public class Images {
	
	@ApiModelProperty(value="图片id",readOnly=true)
	private String imageId;
	
	@ApiModelProperty(value="图片url",example = "/images/swimming/123.png,/images/swimming/456.png")
	private String imageUrl;

	@ApiModelProperty(value="压缩图",example = "/images/swimming/123-thumbnail.png,/images/swimming/456-thumbnail.png")
	private String imageThumbnail;
	
	@ApiModelProperty(value="对象id(对应的业务id，如：placeId)",readOnly=true)
	private String objectId;
	
	@ApiModelProperty(value="图片类型0投诉/保修1场所2公告3其他",readOnly=true)
	private Integer imageType;
	
	@ApiModelProperty(value="创建时间",readOnly=true)
    private Date createTime;

    @ApiModelProperty(value="更新时间",readOnly=true)
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

	public String getImageThumbnail() {
		return imageThumbnail;
	}

	public void setImageThumbnail(String imageThumbnail) {
		this.imageThumbnail = imageThumbnail;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Integer getImageType() {
		return imageType;
	}

	public void setImageType(Integer imageType) {
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
