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
	
	@ApiModelProperty("投诉/报修id")
	private String complainId;
	
	@ApiModelProperty("公告id")
	private String noticeId;
	
	@ApiModelProperty("场所id")
	private String placeId;
	
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

	public String getComplainId() {
		return complainId;
	}

	public void setComplainId(String complainId) {
		this.complainId = complainId;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
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
