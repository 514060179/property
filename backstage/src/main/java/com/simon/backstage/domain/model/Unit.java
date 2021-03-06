package com.simon.backstage.domain.model;

import com.simon.backstage.domain.vo.Community;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "Unit", description = "单元")
public class Unit {

    @ApiModelProperty("id")
    private String unitId;

    @ApiModelProperty(value = "社区id",example="c123456")
    private String communityId;

    @ApiModelProperty(value = "建筑id",example = "1231das12")
    private String buildingId;

    @ApiModelProperty(value = "楼宇子部份id",example = "1231das12")
    private String childId;

    @ApiModelProperty(value = "单元名字",example = "A001")
    private String unitName;

    @ApiModelProperty(value = "单元编号",example = "A001")
    private String unitNo;

    @ApiModelProperty(value = "覆盖面积大小(单位平方米)",example = "101.05")
    private BigDecimal unitCoveredArea;

    @ApiModelProperty(value = "分层建筑物相对比(千分之一)",example = "0.002")
    private BigDecimal unitRelativeProportion;

    @ApiModelProperty(value = "分层建筑物之子部分相对比(千分之一)",example = "0.05")
    private BigDecimal unitChildRelativeProportion;

    @ApiModelProperty(value = "用途",example = "居住")
    private String unitPurpose;

    @ApiModelProperty(value = "位置(地下,一楼,二楼,三楼,户外.)",example = "三楼")
    private String unitPosition;

    @ApiModelProperty(value = "单位用途1商业2轻型汽车/电单车3住宅",example = "1")
    private Integer unitType;

    @ApiModelProperty(value = "全址",example = "三楼A001")
    private String unitFullAddress;

    @ApiModelProperty(value = "单元拥有者id",example = "12474sas")
    private String ownerId;

    @ApiModelProperty(value = "单元状态0空置1租赁2装修中3入住",example = "3")
    private Integer unitStatus;

    @ApiModelProperty(value = "单位业权",example = "10")
    private Integer unitTitle;

    @ApiModelProperty(value = "是否为业主",example = "true")
    private boolean owner;

    @ApiModelProperty(hidden = true)
    private Date createTime;

    @ApiModelProperty(hidden = true)
    private Date updateTime;

    @ApiModelProperty(hidden = true)
    private Building building;

    @ApiModelProperty(hidden = true)
    private Community community;

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId == null ? null : buildingId.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo == null ? null : unitNo.trim();
    }

    public BigDecimal getUnitCoveredArea() {
        return unitCoveredArea;
    }

    public void setUnitCoveredArea(BigDecimal unitCoveredArea) {
        this.unitCoveredArea = unitCoveredArea;
    }

    public BigDecimal getUnitRelativeProportion() {
        return unitRelativeProportion;
    }

    public void setUnitRelativeProportion(BigDecimal unitRelativeProportion) {
        this.unitRelativeProportion = unitRelativeProportion;
    }

    public BigDecimal getUnitChildRelativeProportion() {
        return unitChildRelativeProportion;
    }

    public void setUnitChildRelativeProportion(BigDecimal unitChildRelativeProportion) {
        this.unitChildRelativeProportion = unitChildRelativeProportion;
    }

    public String getUnitPurpose() {
        return unitPurpose;
    }

    public void setUnitPurpose(String unitPurpose) {
        this.unitPurpose = unitPurpose == null ? null : unitPurpose.trim();
    }

    public String getUnitPosition() {
        return unitPosition;
    }

    public void setUnitPosition(String unitPosition) {
        this.unitPosition = unitPosition == null ? null : unitPosition.trim();
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public String getUnitFullAddress() {
        return unitFullAddress;
    }

    public void setUnitFullAddress(String unitFullAddress) {
        this.unitFullAddress = unitFullAddress == null ? null : unitFullAddress.trim();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getUnitStatus() {
        return unitStatus;
    }

    public void setUnitStatus(Integer unitStatus) {
        this.unitStatus = unitStatus;
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

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Integer getUnitTitle() {
        return unitTitle;
    }

    public void setUnitTitle(Integer unitTitle) {
        this.unitTitle = unitTitle;
    }

    public boolean getOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }
}