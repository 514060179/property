package com.simon.dal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Notice", description = "广告/公告/推送")
public class Notice {
    @ApiModelProperty(name="id",readOnly=true)
    private String noticeId;

    @ApiModelProperty("社区id")
    private String communityId;

    @ApiModelProperty("通知类型")
    private String noticeType;

    @ApiModelProperty("场所标题(简体)")
    private String noticeTitle;

    @ApiModelProperty("场所标题(繁体)")
    private String noticeTraditionalTitle;

    @ApiModelProperty("场所标题(英文)")
    private String noticeEnglishTitle;

    @ApiModelProperty(name="图片",readOnly=true)
    private String noticeImage;

    @ApiModelProperty(name="创建时间",readOnly=true)
    private Date createTime;

    @ApiModelProperty(name="更新时间",readOnly=true)
    private Date updateTime;

    @ApiModelProperty("详情")
    private String noticeDetails;

    @ApiModelProperty("详情（繁体）")
    private String noticeTraditionalDetails;

    @ApiModelProperty("详情（英文）")
    private String noticeEnglishDetails;

    public String getNoticeDetails() {
        return noticeDetails;
    }

    public void setNoticeDetails(String noticeDetails) {
        this.noticeDetails = noticeDetails == null ? null : noticeDetails.trim();
    }

    public String getNoticeTraditionalDetails() {
        return noticeTraditionalDetails;
    }

    public void setNoticeTraditionalDetails(String noticeTraditionalDetails) {
        this.noticeTraditionalDetails = noticeTraditionalDetails == null ? null : noticeTraditionalDetails.trim();
    }

    public String getNoticeEnglishDetails() {
        return noticeEnglishDetails;
    }

    public void setNoticeEnglishDetails(String noticeEnglishDetails) {
        this.noticeEnglishDetails = noticeEnglishDetails == null ? null : noticeEnglishDetails.trim();
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId == null ? null : noticeId.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType == null ? null : noticeType.trim();
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle == null ? null : noticeTitle.trim();
    }

    public String getNoticeTraditionalTitle() {
        return noticeTraditionalTitle;
    }

    public void setNoticeTraditionalTitle(String noticeTraditionalTitle) {
        this.noticeTraditionalTitle = noticeTraditionalTitle == null ? null : noticeTraditionalTitle.trim();
    }

    public String getNoticeEnglishTitle() {
        return noticeEnglishTitle;
    }

    public void setNoticeEnglishTitle(String noticeEnglishTitle) {
        this.noticeEnglishTitle = noticeEnglishTitle == null ? null : noticeEnglishTitle.trim();
    }

    public String getNoticeImage() {
        return noticeImage;
    }

    public void setNoticeImage(String noticeImage) {
        this.noticeImage = noticeImage == null ? null : noticeImage.trim();
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