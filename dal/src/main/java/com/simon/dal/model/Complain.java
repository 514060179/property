package com.simon.dal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Complain", description = "投诉/保修")
public class Complain {

    @ApiModelProperty("id")
    private String complainId;

    @ApiModelProperty(name="用户id",hidden=true)
    private String userId;

    @ApiModelProperty("位置(XX大厦)")
    private String complainPosition;

    @ApiModelProperty("具体位置(停车场,商铺...)")
    private String complainSpecificPosition;

    @ApiModelProperty("类型(土木工程，清洁，保安...)")
    private String complainType;

    @ApiModelProperty("类型分类（供电系统，发电机...）")
    private String complainClassType;

    @ApiModelProperty(name="图片",hidden=true)
    private String complainImages;

    @ApiModelProperty("联络人")
    private String complainLiaisonsName;

    @ApiModelProperty("联络人性别")
    private String complainLiaisonsSex;

    @ApiModelProperty("联络人电邮")
    private String complainLiaisonsEmail;

    @ApiModelProperty(name="状态:0发起1收到2处理中3处理完成",hidden=true)
    private String complainStatus;

    @ApiModelProperty(name="完成时间",hidden=true)
    private Date complainFinishTime;

    @ApiModelProperty(name="处理人",hidden=true)
    private String complainHandler;

    @ApiModelProperty("描述")
    private String complainDescribe;

    @ApiModelProperty(name="声音",hidden=true)
    private String complainVoice;

    @ApiModelProperty(name="创建时间",hidden=true)
    private Date createTime;

    @ApiModelProperty(name="更新时间",hidden=true)
    private Date updateTime;

    public String getComplainId() {
        return complainId;
    }

    public void setComplainId(String complainId) {
        this.complainId = complainId == null ? null : complainId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getComplainPosition() {
        return complainPosition;
    }

    public void setComplainPosition(String complainPosition) {
        this.complainPosition = complainPosition == null ? null : complainPosition.trim();
    }

    public String getComplainSpecificPosition() {
        return complainSpecificPosition;
    }

    public void setComplainSpecificPosition(String complainSpecificPosition) {
        this.complainSpecificPosition = complainSpecificPosition == null ? null : complainSpecificPosition.trim();
    }

    public String getComplainType() {
        return complainType;
    }

    public void setComplainType(String complainType) {
        this.complainType = complainType == null ? null : complainType.trim();
    }

    public String getComplainClassType() {
        return complainClassType;
    }

    public void setComplainClassType(String complainClassType) {
        this.complainClassType = complainClassType == null ? null : complainClassType.trim();
    }

    public String getComplainImages() {
        return complainImages;
    }

    public void setComplainImages(String complainImages) {
        this.complainImages = complainImages == null ? null : complainImages.trim();
    }

    public String getComplainLiaisonsName() {
        return complainLiaisonsName;
    }

    public void setComplainLiaisonsName(String complainLiaisonsName) {
        this.complainLiaisonsName = complainLiaisonsName == null ? null : complainLiaisonsName.trim();
    }

    public String getComplainLiaisonsSex() {
        return complainLiaisonsSex;
    }

    public void setComplainLiaisonsSex(String complainLiaisonsSex) {
        this.complainLiaisonsSex = complainLiaisonsSex == null ? null : complainLiaisonsSex.trim();
    }

    public String getComplainLiaisonsEmail() {
        return complainLiaisonsEmail;
    }

    public void setComplainLiaisonsEmail(String complainLiaisonsEmail) {
        this.complainLiaisonsEmail = complainLiaisonsEmail == null ? null : complainLiaisonsEmail.trim();
    }

    public String getComplainStatus() {
        return complainStatus;
    }

    public void setComplainStatus(String complainStatus) {
        this.complainStatus = complainStatus == null ? null : complainStatus.trim();
    }

    public Date getComplainFinishTime() {
        return complainFinishTime;
    }

    public void setComplainFinishTime(Date complainFinishTime) {
        this.complainFinishTime = complainFinishTime;
    }

    public String getComplainHandler() {
        return complainHandler;
    }

    public void setComplainHandler(String complainHandler) {
        this.complainHandler = complainHandler == null ? null : complainHandler.trim();
    }

    public String getComplainDescribe() {
        return complainDescribe;
    }

    public void setComplainDescribe(String complainDescribe) {
        this.complainDescribe = complainDescribe == null ? null : complainDescribe.trim();
    }

    public String getComplainVoice() {
        return complainVoice;
    }

    public void setComplainVoice(String complainVoice) {
        this.complainVoice = complainVoice;
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