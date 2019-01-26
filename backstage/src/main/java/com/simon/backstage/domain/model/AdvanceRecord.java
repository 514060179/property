package com.simon.backstage.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "AdvanceRecord", description = "预付账户记录")
public class AdvanceRecord {
    private String advanceRecordId;
    @ApiModelProperty(value = "社区id",example = "c123456")
    private String advanceId;
    @ApiModelProperty(value = "金额",example = "11.2")
    private BigDecimal advanceAmount;
    @ApiModelProperty(value = "出入账类型0出账1入账",example = "0")
    private Integer advanceType;
    @ApiModelProperty(value = "描述",example = "11月的物业费支出")
    private String advanceDescribe;
    @ApiModelProperty(hidden = true)
    private Date createTime;
    @ApiModelProperty(hidden = true)
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