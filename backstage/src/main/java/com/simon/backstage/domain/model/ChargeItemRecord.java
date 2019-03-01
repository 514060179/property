package com.simon.backstage.domain.model;

import com.simon.backstage.domain.vo.Community;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
@ApiModel(value = "ChargeItemRecord", description = "收费项目记录")
public class ChargeItemRecord {

    private String recordId;
    @ApiModelProperty(value = "住户id",example = "0e722e5735b743d98826b5824fade372")
    private String userId;
    @ApiModelProperty(value = "单位收费项目id",example = "er7212e5735b3743d2126b5324fadese2")
    private String unitItemId;
    @ApiModelProperty(value = "收费记录年月份(2019-01)",example = "2019-01")
    private String recordDate;
    @ApiModelProperty(value = "状态0欠费1已付2预支付",example = "0")
    private Integer recordStatus;
    @ApiModelProperty(value = "收费时间",example = "2019-01-11 12:12:00")
    private Date recordTime;
    @ApiModelProperty(value = "实际收取金额",example = "45.2")
    private BigDecimal recordActualAmount;
    @ApiModelProperty(value = "收费金额",example = "112.5")
    private BigDecimal recordAmount;
    @ApiModelProperty(value = "滞纳金额",example = "22.5")
    private Long recordLateFee;
    @ApiModelProperty(value = "滞纳金天数",example = "5")
    private Integer recordLateDate;
    @ApiModelProperty(value = "备注",example = "周期性收款")
    private String recordRemark;
    @ApiModelProperty(hidden = true)
    private Date createTime;
    @ApiModelProperty(hidden = true)
    private Date updateTime;
    @ApiModelProperty(hidden = true)
    private Community community;

    @ApiModelProperty(hidden = true)
    private ChargeItem chargeItem;

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

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public ChargeItem getChargeItem() {
        return chargeItem;
    }

    public void setChargeItem(ChargeItem chargeItem) {
        this.chargeItem = chargeItem;
    }
}