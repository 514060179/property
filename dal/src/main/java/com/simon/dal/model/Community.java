package com.simon.dal.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class Community {

    @ApiModelProperty("社区id")
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

    @ApiModelProperty("管理类型(0普通管理1综合管理)")
    private String communityManagementType;

    private Date createTime;

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