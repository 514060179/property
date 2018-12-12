package com.simon.backstage.domain.model;

import java.util.Date;

public class AssetStorageRecord {
    private String storageRecordId;

    private String assetId;

    private Integer storageType;

    private Integer storageAmount;

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