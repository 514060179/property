package com.simon.backstage.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class Unit {
    private String unitId;

    private String buildingId;

    private String unitName;

    private String unitNo;

    private BigDecimal unitCoveredArea;

    private BigDecimal unitRelativeProportion;

    private BigDecimal unitChildRelativeProportion;

    private String unitPurpose;

    private String unitPosition;

    private Integer unitType;

    private String unitFullAddress;

    private Integer unitStatus;

    private Date createTime;

    private Date updateTime;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId == null ? null : buildingId.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo == null ? null : unitNo.trim();
    }

    public BigDecimal getUnitCoveredArea() {
        return unitCoveredArea;
    }

    public void setUnitCoveredArea(BigDecimal unitCoveredArea) {
        this.unitCoveredArea = unitCoveredArea;
    }

    public BigDecimal getUnitRelativeProportion() {
        return unitRelativeProportion;
    }

    public void setUnitRelativeProportion(BigDecimal unitRelativeProportion) {
        this.unitRelativeProportion = unitRelativeProportion;
    }

    public BigDecimal getUnitChildRelativeProportion() {
        return unitChildRelativeProportion;
    }

    public void setUnitChildRelativeProportion(BigDecimal unitChildRelativeProportion) {
        this.unitChildRelativeProportion = unitChildRelativeProportion;
    }

    public String getUnitPurpose() {
        return unitPurpose;
    }

    public void setUnitPurpose(String unitPurpose) {
        this.unitPurpose = unitPurpose == null ? null : unitPurpose.trim();
    }

    public String getUnitPosition() {
        return unitPosition;
    }

    public void setUnitPosition(String unitPosition) {
        this.unitPosition = unitPosition == null ? null : unitPosition.trim();
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public String getUnitFullAddress() {
        return unitFullAddress;
    }

    public void setUnitFullAddress(String unitFullAddress) {
        this.unitFullAddress = unitFullAddress == null ? null : unitFullAddress.trim();
    }

    public Integer getUnitStatus() {
        return unitStatus;
    }

    public void setUnitStatus(Integer unitStatus) {
        this.unitStatus = unitStatus;
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