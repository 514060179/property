package com.simon.backstage.domain.model;

import com.simon.backstage.domain.vo.Community;
import com.simon.dal.model.Images;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

@ApiModel(value = "Asset", description = "资产信息")
public class Asset {

    @ApiModelProperty("id")
    private String assetId;

    @ApiModelProperty(value = "社区id",example = "c123456")
    @NotEmpty(message = "社区id不能为空")
    private String communityId;

    @ApiModelProperty(value = "资产编号",example = "ZC001")
    private String assetNo;

    @ApiModelProperty(value = "资产类型：电器、公共设备",example = "电器")
    private String assetType;

    @ApiModelProperty(value = "资产名字",example = "路灯")
    private String assetName;

    @ApiModelProperty(value = "资产名字(繁体)",example = "路燈")
    private String assetTraditionalName;

    @ApiModelProperty(value = "资产名字(英文)",example = "street lamp")
    private String assetEnglishName;

    @ApiModelProperty(value = "位置信息",example = "正门")
    private String assetPosition;

    @ApiModelProperty(value = "位置信息(繁体)",example = "正門")
    private String assetTraditionalPosition;

    @ApiModelProperty(value = "位置信息(英文)",example = "Front gate")
    private String assetEnglishPosition;

    @ApiModelProperty(value = "保养？",example = "true")
    private Boolean assetMaintain;

    @ApiModelProperty(value = "保养周期（天数）",example = "15")
    private Integer assetMaintainRemindCycle;

    @ApiModelProperty(value = "购买日期",example = "2018-11-11")
    private Date assetBuyDate;

    @ApiModelProperty(hidden = true)
    private Date createTime;

    @ApiModelProperty(hidden = true)
    private Date updateTime;
    @ApiModelProperty(hidden = true)
    private Community community;

    @ApiModelProperty(value = "描述",example = "描述信息")
	private String assetDescribe;

    @ApiModelProperty(value = "描述(繁体)",example = "苗述信息")
    private String assetTraditionalDescribe;

    @ApiModelProperty(value = "描述(英文)",example = "Descriptive information")
    private String assetEnglishDescribe;

    private List<Images> assetImage;

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

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
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

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public List<Images> getAssetImage() {
        return assetImage;
    }

    public void setAssetImage(List<Images> assetImage) {
        this.assetImage = assetImage;
    }

    public Boolean getAssetMaintain() {
        return assetMaintain;
    }

    public void setAssetMaintain(Boolean assetMaintain) {
        this.assetMaintain = assetMaintain;
    }

    public Integer getAssetMaintainRemindCycle() {
        return assetMaintainRemindCycle;
    }

    public void setAssetMaintainRemindCycle(Integer assetMaintainRemindCycle) {
        this.assetMaintainRemindCycle = assetMaintainRemindCycle;
    }

    public Date getAssetBuyDate() {
        return assetBuyDate;
    }

    public void setAssetBuyDate(Date assetBuyDate) {
        this.assetBuyDate = assetBuyDate;
    }
}