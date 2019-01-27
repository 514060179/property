package com.simon.backstage.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "AssetStorageRecord", description = "资源出入库记录")
public class AssetStorageRecord {

    @ApiModelProperty("id")
    private String storageRecordId;

    @ApiModelProperty(value = "资产id",example = "qe213sg97w5asda")
    private String assetId;

    @ApiModelProperty(value = "出入类型 0入库1出库",example = "0")
    private Integer storageType;

    @ApiModelProperty(value = "数量",example = "2")
    private Integer storageAmount;

    @ApiModelProperty(value = "单价",example = "10.5")
    private BigDecimal storageUnitprice;

    @ApiModelProperty(value = "备注",example = "备注")
    private String storageRemark;

    @ApiModelProperty(hidden = true)
    private Date createTime;

    @ApiModelProperty(hidden = true)
    private Date updateTime;

    public String getStorageRecordId() {
        return storageRecordId;
    }

    public void setStorageRecordId(String storageRecordId) {
        this.storageRecordId = storageRecordId == null ? null : storageRecordId.trim();
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    public Integer getStorageType() {
        return storageType;
    }

    public void setStorageType(Integer storageType) {
        this.storageType = storageType;
    }

    public Integer getStorageAmount() {
        return storageAmount;
    }

    public void setStorageAmount(Integer storageAmount) {
        this.storageAmount = storageAmount;
    }

    public BigDecimal getStorageUnitprice() {
        return storageUnitprice;
    }

    public void setStorageUnitprice(BigDecimal storageUnitprice) {
        this.storageUnitprice = storageUnitprice;
    }

    public String getStorageRemark() {
        return storageRemark;
    }

    public void setStorageRemark(String storageRemark) {
        this.storageRemark = storageRemark == null ? null : storageRemark.trim();
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