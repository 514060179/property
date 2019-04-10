package com.simon.backstage.domain.model;

import com.simon.backstage.domain.vo.Community;
import com.simon.dal.model.Images;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "Visitor", description = "访客记录")
public class Visitor {

    @ApiModelProperty(value ="id")
    private String visitorId;

    @ApiModelProperty(value = "社区id", readOnly=true)
    private String communityId;

    @ApiModelProperty(value = "楼宇id", readOnly=true)
    private String buildingId;

    @ApiModelProperty(value = "访客名字",example = "simon")
    private String visitorName;

    @ApiModelProperty(value = "性别0女1男",example = "0")
    private Integer visitorSex;

    @ApiModelProperty(value = "联系号码",example = "13421256541")
    private String visitorPhone;

    @ApiModelProperty(value = "访问事由",example = "逛逛")
    private String visitorCause;

    @ApiModelProperty(value ="创建时间", readOnly=true)
    private Date createTime;

    @ApiModelProperty(value ="更新时间", readOnly=true)
    private Date updateTime;
    @ApiModelProperty(hidden = true)
    private Community community;
    @ApiModelProperty(hidden = true)
    private Building building;

    private List<Images> visitorImage;

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId == null ? null : visitorId.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName == null ? null : visitorName.trim();
    }

    public Integer getVisitorSex() {
        return visitorSex;
    }

    public void setVisitorSex(Integer visitorSex) {
        this.visitorSex = visitorSex;
    }

    public String getVisitorPhone() {
        return visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone == null ? null : visitorPhone.trim();
    }

    public String getVisitorCause() {
        return visitorCause;
    }

    public void setVisitorCause(String visitorCause) {
        this.visitorCause = visitorCause == null ? null : visitorCause.trim();
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

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<Images> getVisitorImage() {
        return visitorImage;
    }

    public void setVisitorImage(List<Images> visitorImage) {
        this.visitorImage = visitorImage;
    }
}