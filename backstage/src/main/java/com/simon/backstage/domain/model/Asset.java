package com.simon.backstage.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Asset", description = "资产信息")
public class Asset {

    @ApiModelProperty("id")
    private String assetId;

    @ApiModelProperty("社区id")
    private String communityId;

    @ApiModelProperty("资产类型")
    private String assetType;

    @ApiModelProperty("资产名字")
    private String assetName;

    @ApiModelProperty("资产名字(繁体)")
    private String assetTraditionalName;

    @ApiModelProperty("资产名字(英文)")
    private String assetEnglishName;

    @ApiModelProperty("位置信息")
    private String assetPosition;

    @ApiModelProperty("位置信息(繁体)")
    private String assetTraditionalPosition;

    @ApiModelProperty("位置信息(英文)")
    private String assetEnglishPosition;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty("描述")
	private String assetDescribe;

    @ApiModelProperty("描述(繁体)")
    private String assetTraditionalDescribe;

    @ApiModelProperty("描述(英文)")
    private String assetEnglishDescribe;

    public String getAssetDescribe() {
        return assetDescribe;
    }

    public void setAssetDescribe(String assetDescribe) {
        this.assetDescribe = assetDescribe == null ? null : assetDescribe.trim();
    }

    public String getAssetTraditionalDescribe() {
        return assetTraditionalDescribe;
    }

    public void setAssetTraditionalDescribe(String assetTraditionalDescribe) {
        this.assetTraditionalDescribe = assetTraditionalDescribe == null ? null : assetTraditionalDescribe.trim();
    }

    public String getAssetEnglishDescribe() {
        return assetEnglishDescribe;
    }

    public void setAssetEnglishDescribe(String assetEnglishDescribe) {
        this.assetEnglishDescribe = assetEnglishDescribe == null ? null : assetEnglishDescribe.trim();
    }
	
    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName == null ? null : assetName.trim();
    }

    public String getAssetTraditionalName() {
        return assetTraditionalName;
    }

    public void setAssetTraditionalName(String assetTraditionalName) {
        this.assetTraditionalName = assetTraditionalName == null ? null : assetTraditionalName.trim();
    }

    public String getAssetEnglishName() {
        return assetEnglishName;
    }

    public void setAssetEnglishName(String assetEnglishName) {
        this.assetEnglishName = assetEnglishName == null ? null : assetEnglishName.trim();
    }

    public String getAssetPosition() {
        return assetPosition;
    }

    public void setAssetPosition(String assetPosition) {
        this.assetPosition = assetPosition == null ? null : assetPosition.trim();
    }

    public String getAssetTraditionalPosition() {
        return assetTraditionalPosition;
    }

    public void setAssetTraditionalPosition(String assetTraditionalPosition) {
        this.assetTraditionalPosition = assetTraditionalPosition == null ? null : assetTraditionalPosition.trim();
    }

    public String getAssetEnglishPosition() {
        return assetEnglishPosition;
    }

    public void setAssetEnglishPosition(String assetEnglishPosition) {
        this.assetEnglishPosition = assetEnglishPosition == null ? null : assetEnglishPosition.trim();
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