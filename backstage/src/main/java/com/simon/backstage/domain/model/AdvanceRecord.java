package com.simon.backstage.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class AdvanceRecord {
    private String advanceRecordId;

    private String advanceId;

    private BigDecimal advanceAmount;

    private Integer advanceType;

    private String advanceDescribe;

    private Date createTime;

    private Date updateTime;

    public String getAdvanceRecordId() {
        return advanceRecordId;
    }

    public void setAdvanceRecordId(String advanceRecordId) {
        this.advanceRecordId = advanceRecordId == null ? null : advanceRecordId.trim();
    }

    public String getAdvanceId() {
        return advanceId;
    }

    public void setAdvanceId(String advanceId) {
        this.advanceId = advanceId == null ? null : advanceId.trim();
    }

    public BigDecimal getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(BigDecimal advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public Integer getAdvanceType() {
        return advanceType;
    }

    public void setAdvanceType(Integer advanceType) {
        this.advanceType = advanceType;
    }

    public String getAdvanceDescribe() {
        return advanceDescribe;
    }

    public void setAdvanceDescribe(String advanceDescribe) {
        this.advanceDescribe = advanceDescribe == null ? null : advanceDescribe.trim();
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