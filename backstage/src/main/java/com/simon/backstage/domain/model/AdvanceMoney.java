package com.simon.backstage.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "AdvanceMoney", description = "预付账户")
public class AdvanceMoney {
    private String advanceId;
    @ApiModelProperty(value = "用户id",example = "0e722e5735b743d98826b5824fade372")
    private String userId;
    @ApiModelProperty(value = "账户总额",example = "112.5")
    private BigDecimal advanceAmount;
    @ApiModelProperty(hidden = true)
    private Date createTime;
    @ApiModelProperty(hidden = true)
    private Date updateTime;

    public String getAdvanceId() {
        return advanceId;
    }

    public void setAdvanceId(String advanceId) {
        this.advanceId = advanceId == null ? null : advanceId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public BigDecimal getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(BigDecimal advanceAmount) {
        this.advanceAmount = advanceAmount;
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