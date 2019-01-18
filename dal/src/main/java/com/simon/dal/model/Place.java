package com.simon.dal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

@ApiModel(value = "Place", description = "场所信息")
public class Place {

    @ApiModelProperty(value="id",readOnly=true)
    private String placeId;

    @ApiModelProperty(value = "社区id",readOnly=true)
    private String communityId;

    @ApiModelProperty(value = "场所名字(简体)",example = "游泳池")
    private String placeName;

    @ApiModelProperty(value = "场所名字(繁体)",example = "游泳池")
    private String placeTraditionalName;

    @ApiModelProperty(value = "场所名字(英文)",example = "Swimming Pool")
    private String placeEnglishName;
    
    @ApiModelProperty(value="场所状态(0未开放1开放)",example="1")
    private Integer placeStatus;

    @ApiModelProperty(value="起始时间",example="2018-08-08 08:08:08")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date placeStartTime;

    @ApiModelProperty(value="结束时间",example="2018-08-08 08:08:08")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date placeEndTime;

    @ApiModelProperty(value = "需要预定(0否1是)",example = "0")
    private Integer placeNeedOrder;

    @ApiModelProperty(value = "预约时间上限",example = "10")
    private Integer placeUpperLimit;

    @ApiModelProperty(value = "提前天数",example = "1")
    private Integer placeAdvanceOrderDay;

    @ApiModelProperty(value = "最大天数",example = "30")
    private Integer placeFarthestOrderDay;

    @ApiModelProperty(value="创建时间",readOnly=true)
    private Date createTime;

    @ApiModelProperty(value="更新时间",readOnly=true)
    private Date updateTime;

    @ApiModelProperty(value = "简介(中文)",example = "世界上最深的游泳")
    private String placeIntroduction;

    @ApiModelProperty(value = "简介(简体)",example = "世界上最深的游泳池")
    private String placeTraditionalIntroduction;

    @ApiModelProperty(value = "简介(英文)",example = "The deepest swimming in the world")
    private String placeEnglishIntroduction;
    
    private List<Images> images;

    public String getPlaceIntroduction() {
        return placeIntroduction;
    }

    public void setPlaceIntroduction(String placeIntroduction) {
        this.placeIntroduction = placeIntroduction == null ? null : placeIntroduction.trim();
    }

    public String getPlaceTraditionalIntroduction() {
        return placeTraditionalIntroduction;
    }

    public void setPlaceTraditionalIntroduction(String placeTraditionalIntroduction) {
        this.placeTraditionalIntroduction = placeTraditionalIntroduction == null ? null : placeTraditionalIntroduction.trim();
    }

    public String getPlaceEnglishIntroduction() {
        return placeEnglishIntroduction;
    }

    public void setPlaceEnglishIntroduction(String placeEnglishIntroduction) {
        this.placeEnglishIntroduction = placeEnglishIntroduction == null ? null : placeEnglishIntroduction.trim();
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId == null ? null : placeId.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    public Integer getPlaceStatus() {
		return placeStatus;
	}

	public void setPlaceStatus(Integer placeStatus) {
		this.placeStatus = placeStatus;
	}

	public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }

    public String getPlaceTraditionalName() {
        return placeTraditionalName;
    }

    public void setPlaceTraditionalName(String placeTraditionalName) {
        this.placeTraditionalName = placeTraditionalName == null ? null : placeTraditionalName.trim();
    }

    public String getPlaceEnglishName() {
        return placeEnglishName;
    }

    public void setPlaceEnglishName(String placeEnglishName) {
        this.placeEnglishName = placeEnglishName == null ? null : placeEnglishName.trim();
    }

    public Date getPlaceStartTime() {
        return placeStartTime;
    }

    public void setPlaceStartTime(Date placeStartTime) {
        this.placeStartTime = placeStartTime;
    }

    public Date getPlaceEndTime() {
        return placeEndTime;
    }

    public void setPlaceEndTime(Date placeEndTime) {
        this.placeEndTime = placeEndTime;
    }

    public Integer getPlaceNeedOrder() {
        return placeNeedOrder;
    }

    public void setPlaceNeedOrder(Integer placeNeedOrder) {
        this.placeNeedOrder = placeNeedOrder;
    }

    public Integer getPlaceUpperLimit() {
        return placeUpperLimit;
    }

    public void setPlaceUpperLimit(Integer placeUpperLimit) {
        this.placeUpperLimit = placeUpperLimit;
    }

    public Integer getPlaceAdvanceOrderDay() {
        return placeAdvanceOrderDay;
    }

    public void setPlaceAdvanceOrderDay(Integer placeAdvanceOrderDay) {
        this.placeAdvanceOrderDay = placeAdvanceOrderDay;
    }

    public Integer getPlaceFarthestOrderDay() {
        return placeFarthestOrderDay;
    }

    public void setPlaceFarthestOrderDay(Integer placeFarthestOrderDay) {
        this.placeFarthestOrderDay = placeFarthestOrderDay;
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

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}
}