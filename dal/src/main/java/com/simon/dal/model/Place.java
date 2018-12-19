package com.simon.dal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@ApiModel(value = "Place", description = "场所信息")
public class Place {

    @ApiModelProperty(value="id",readOnly=true)
    private String placeId;

    @ApiModelProperty("社区id")
    private String communityId;

    @ApiModelProperty("场所名字(简体)")
    private String placeName;

    @ApiModelProperty("场所名字(繁体)")
    private String placeTraditionalName;

    @ApiModelProperty("场所名字(英文)")
    private String placeEnglishName;

    @ApiModelProperty(value="图片url",readOnly=true)
    private String placeImage;

    @ApiModelProperty(value="起始时间",example="2018-08-08 08:08:08")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date placeStartTime;

    @ApiModelProperty(value="结束时间",example="2018-08-08 08:08:08")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date placeEndTime;

    @ApiModelProperty("需要预定(0否1是)")
    private Integer placeNeedOrder;

    @ApiModelProperty("预约时间上限")
    private Integer placeUpperLimit;

    @ApiModelProperty("提前天数")
    private Integer placeAdvanceOrderDay;

    @ApiModelProperty("最大天数")
    private Integer placeFarthestOrderDay;

    @ApiModelProperty(value="创建时间",readOnly=true)
    private Date createTime;

    @ApiModelProperty(value="更新时间",readOnly=true)
    private Date updateTime;

    @ApiModelProperty("简介(中文)")
    private String placeIntroduction;

    @ApiModelProperty("简介(简体)")
    private String placeTraditionalIntroduction;

    @ApiModelProperty("简介(英文)")
    private String placeEnglishIntroduction;

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

    public String getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(String placeImage) {
        this.placeImage = placeImage == null ? null : placeImage.trim();
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
}