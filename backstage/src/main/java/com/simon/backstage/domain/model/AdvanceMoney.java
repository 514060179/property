package com.simon.backstage.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class AdvanceMoney {
    private String advanceId;

    private String userId;

    private BigDecimal advanceAmount;

    private Date createTime;

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