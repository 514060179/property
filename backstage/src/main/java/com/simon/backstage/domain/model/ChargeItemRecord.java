package com.simon.backstage.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeItemRecord {
    private String recordId;

    private String userId;

    private String unitItemId;

    private String recordDate;

    private Integer recordStatus;

    private Date recordTime;

    private BigDecimal recordActualAmount;

    private BigDecimal recordAmount;

    private Long recordLateFee;

    private Integer recordLateDate;

    private String recordRemark;

    private Date createTime;

    private Date updateTime;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUnitItemId() {
        return unitItemId;
    }

    public void setUnitItemId(String unitItemId) {
        this.unitItemId = unitItemId == null ? null : unitItemId.trim();
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate == null ? null : recordDate.trim();
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public BigDecimal getRecordActualAmount() {
        return recordActualAmount;
    }

    public void setRecordActualAmount(BigDecimal recordActualAmount) {
        this.recordActualAmount = recordActualAmount;
    }

    public BigDecimal getRecordAmount() {
        return recordAmount;
    }

    public void setRecordAmount(BigDecimal recordAmount) {
        this.recordAmount = recordAmount;
    }

    public Long getRecordLateFee() {
        return recordLateFee;
    }

    public void setRecordLateFee(Long recordLateFee) {
        this.recordLateFee = recordLateFee;
    }

    public Integer getRecordLateDate() {
        return recordLateDate;
    }

    public void setRecordLateDate(Integer recordLateDate) {
        this.recordLateDate = recordLateDate;
    }

    public String getRecordRemark() {
        return recordRemark;
    }

    public void setRecordRemark(String recordRemark) {
        this.recordRemark = recordRemark == null ? null : recordRemark.trim();
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