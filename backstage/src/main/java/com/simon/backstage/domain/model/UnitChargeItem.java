package com.simon.backstage.domain.model;

import java.util.Date;

public class UnitChargeItem {
    private String unitItemId;

    private String itemId;

    private String unitId;

    private Date createTime;

    private Date updateTime;

    public String getUnitItemId() {
        return unitItemId;
    }

    public void setUnitItemId(String unitItemId) {
        this.unitItemId = unitItemId == null ? null : unitItemId.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
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