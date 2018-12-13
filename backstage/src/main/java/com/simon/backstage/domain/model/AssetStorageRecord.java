package com.simon.backstage.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "AssetStorageRecord", description = "资源出入库记录")
public class AssetStorageRecord {

    @ApiModelProperty("id")
    private String storageRecordId;

    @ApiModelProperty("资源id")
    private String assetId;

    @ApiModelProperty("出入类型 1入库2出库")
    private Integer storageType;

    @ApiModelProperty("数量")
    private Integer storageAmount;

    @ApiModelProperty("单价")
    private BigDecimal storageUnitprice;

    @ApiModelProperty("备注")
    private String storageRemark;

    private Date createTime;

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