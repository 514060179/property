package com.simon.backstage.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Building", description = "楼宇信息")
public class Building {

    @ApiModelProperty("id")
    private String buildingId;

    @ApiModelProperty(value = "社区id",example = "erre1231dasda")
    private String communityId;

    @ApiModelProperty(value = "楼宇名字",example = "生产楼")
    private String buildingName;

    @ApiModelProperty(value = "楼宇编号",example = "TC001")
    private String buildingNo;

    @ApiModelProperty(value = "楼宇全址",example = "珠海太川110号")
    private String fullAddress;

    @ApiModelProperty(value = "楼宇结构",example = "四面环绕")
    private String buildingStruct;

    @ApiModelProperty(value = "楼宇方向",example = "坐北向东")
    private String buildingDirection;

    @ApiModelProperty(value = "楼上几层",example = "30")
    private Integer floorUpNum;

    @ApiModelProperty(value = "楼下几层",example = "4")
    private Integer floorLowNum;

    @ApiModelProperty(value = "是否注销")
    private Boolean deleted;

    @ApiModelProperty(hidden = true)
    private Date createTime;

    @ApiModelProperty(hidden = true)
    private Date updateTime;

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId == null ? null : buildingId.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo == null ? null : buildingNo.trim();
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress == null ? null : fullAddress.trim();
    }

    public String getBuildingStruct() {
        return buildingStruct;
    }

    public void setBuildingStruct(String buildingStruct) {
        this.buildingStruct = buildingStruct == null ? null : buildingStruct.trim();
    }

    public String getBuildingDirection() {
        return buildingDirection;
    }

    public void setBuildingDirection(String buildingDirection) {
        this.buildingDirection = buildingDirection == null ? null : buildingDirection.trim();
    }

    public Integer getFloorUpNum() {
        return floorUpNum;
    }

    public void setFloorUpNum(Integer floorUpNum) {
        this.floorUpNum = floorUpNum;
    }

    public Integer getFloorLowNum() {
        return floorLowNum;
    }

    public void setFloorLowNum(Integer floorLowNum) {
        this.floorLowNum = floorLowNum;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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