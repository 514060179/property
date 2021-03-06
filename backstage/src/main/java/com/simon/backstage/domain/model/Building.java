package com.simon.backstage.domain.model;

import com.simon.backstage.domain.vo.Community;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;


@ApiModel(value = "Building", description = "楼宇信息")
public class Building {

    @ApiModelProperty("id")
    private String buildingId;

    @ApiModelProperty(value = "社区id",example = "c123456")
    private String communityId;

    @ApiModelProperty(value = "社区子部分id",example = "c123456")
    private String communityChildId;

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

    @ApiModelProperty(value = "住宅数量",example = "30")
    private Integer houseNum;

    @ApiModelProperty(value = "车场数量",example = "4")
    private Integer parkingNum;

    @ApiModelProperty("管理类型(0简单管理1综合管理)")
    private Integer managementType;

    @ApiModelProperty(value = "是否注销")
    private Boolean deleted;

    @ApiModelProperty(hidden = true)
    private Date createTime;

    @ApiModelProperty(hidden = true)
    private Date updateTime;

    @ApiModelProperty(hidden = true)
    private Community community;

    @ApiModelProperty(value = "楼宇子部分")
    private List<BuildingChild> buildingChildList;

    @ApiModelProperty(value = "楼宇楼层")
    private List<Floor> floorList;

    @ApiModelProperty(value = "楼宇之共同部分")
    private List<String> commonPdf;

    @ApiModelProperty(value = "业主花名册文件")
    private List<String> rosterPdf;

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

    public String getCommunityChildId() {
        return communityChildId;
    }

    public void setCommunityChildId(String communityChildId) {
        this.communityChildId = communityChildId;
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

    public Integer getManagementType() {
        return managementType;
    }

    public void setManagementType(Integer managementType) {
        this.managementType = managementType;
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

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public List<BuildingChild> getBuildingChildList() {
        return buildingChildList;
    }

    public void setBuildingChildList(List<BuildingChild> buildingChildList) {
        this.buildingChildList = buildingChildList;
    }

    public List<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }

    public List<String> getCommonPdf() {
        return commonPdf;
    }

    public void setCommonPdf(List<String> commonPdf) {
        this.commonPdf = commonPdf;
    }

    public List<String> getRosterPdf() {
        return rosterPdf;
    }

    public void setRosterPdf(List<String> rosterPdf) {
        this.rosterPdf = rosterPdf;
    }

    public Integer getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }

    public Integer getParkingNum() {
        return parkingNum;
    }

    public void setParkingNum(Integer parkingNum) {
        this.parkingNum = parkingNum;
    }
}