package com.simon.dal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "Community", description = "社区")
public class Community {

    @ApiModelProperty(value="社区id",readOnly=true)
    private String communityId;

    @ApiModelProperty("社区编号")
    private String communityNo;

    @ApiModelProperty("社区名字")
    private String communityName;

    @ApiModelProperty("社区纬度")
    private BigDecimal communityLocX;

    @ApiModelProperty("社区经度")
    private BigDecimal communityLocY;

    @ApiModelProperty("社区全址")
    private String communityAddress;

    @ApiModelProperty("总面积(平方米)")
    private BigDecimal communityArea;

    @ApiModelProperty("建筑面积")
    private BigDecimal communityBuildingArea;

    @ApiModelProperty("道路面积")
    private BigDecimal communityRoadArea;

    @ApiModelProperty("绿化面积")
    private BigDecimal communityGreenarea;

    @ApiModelProperty("公用面积")
    private BigDecimal communityCommonArea;

    @ApiModelProperty("车库面积")
    private BigDecimal communityGarageArea;

    @ApiModelProperty("车位数量")
    private Integer communityGarageCount;

    @ApiModelProperty("房间总数")
    private Integer communityRoomCount;

    @ApiModelProperty("联系人")
    private String communityContacts;

    @ApiModelProperty("联系人号码")
    private String communityContactsPhone;

    @ApiModelProperty("备注")
    private String communityRemark;

    @ApiModelProperty(value="是否删除",readOnly=true)
    private Boolean communityDeleted;

    @ApiModelProperty("管理类型(0普通管理1综合管理)")
    private String communityManagementType;

    @ApiModelProperty(value="创建时间",readOnly=true)
    private Date createTime;

    @ApiModelProperty(value="更新时间",readOnly=true)
    private Date updateTime;

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    public String getCommunityNo() {
        return communityNo;
    }

    public void setCommunityNo(String communityNo) {
        this.communityNo = communityNo == null ? null : communityNo.trim();
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName == null ? null : communityName.trim();
    }

    public BigDecimal getCommunityLocX() {
        return communityLocX;
    }

    public void setCommunityLocX(BigDecimal communityLocX) {
        this.communityLocX = communityLocX;
    }

    public BigDecimal getCommunityLocY() {
        return communityLocY;
    }

    public void setCommunityLocY(BigDecimal communityLocY) {
        this.communityLocY = communityLocY;
    }

    public String getCommunityAddress() {
        return communityAddress;
    }

    public void setCommunityAddress(String communityAddress) {
        this.communityAddress = communityAddress == null ? null : communityAddress.trim();
    }

    public BigDecimal getCommunityArea() {
        return communityArea;
    }

    public void setCommunityArea(BigDecimal communityArea) {
        this.communityArea = communityArea;
    }

    public BigDecimal getCommunityBuildingArea() {
        return communityBuildingArea;
    }

    public void setCommunityBuildingArea(BigDecimal communityBuildingArea) {
        this.communityBuildingArea = communityBuildingArea;
    }

    public BigDecimal getCommunityRoadArea() {
        return communityRoadArea;
    }

    public void setCommunityRoadArea(BigDecimal communityRoadArea) {
        this.communityRoadArea = communityRoadArea;
    }

    public BigDecimal getCommunityGreenarea() {
        return communityGreenarea;
    }

    public void setCommunityGreenarea(BigDecimal communityGreenarea) {
        this.communityGreenarea = communityGreenarea;
    }

    public BigDecimal getCommunityCommonArea() {
        return communityCommonArea;
    }

    public void setCommunityCommonArea(BigDecimal communityCommonArea) {
        this.communityCommonArea = communityCommonArea;
    }

    public BigDecimal getCommunityGarageArea() {
        return communityGarageArea;
    }

    public void setCommunityGarageArea(BigDecimal communityGarageArea) {
        this.communityGarageArea = communityGarageArea;
    }

    public Integer getCommunityGarageCount() {
        return communityGarageCount;
    }

    public void setCommunityGarageCount(Integer communityGarageCount) {
        this.communityGarageCount = communityGarageCount;
    }

    public Integer getCommunityRoomCount() {
        return communityRoomCount;
    }

    public void setCommunityRoomCount(Integer communityRoomCount) {
        this.communityRoomCount = communityRoomCount;
    }

    public String getCommunityContacts() {
        return communityContacts;
    }

    public void setCommunityContacts(String communityContacts) {
        this.communityContacts = communityContacts;
    }

    public String getCommunityContactsPhone() {
        return communityContactsPhone;
    }

    public void setCommunityContactsPhone(String communityContactsPhone) {
        this.communityContactsPhone = communityContactsPhone;
    }

    public String getCommunityRemark() {
        return communityRemark;
    }

    public void setCommunityRemark(String communityRemark) {
        this.communityRemark = communityRemark;
    }

    public Boolean getCommunityDeleted() {
        return communityDeleted;
    }

    public void setCommunityDeleted(Boolean communityDeleted) {
        this.communityDeleted = communityDeleted;
    }

    public String getCommunityManagementType() {
        return communityManagementType;
    }

    public void setCommunityManagementType(String communityManagementType) {
        this.communityManagementType = communityManagementType == null ? null : communityManagementType.trim();
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