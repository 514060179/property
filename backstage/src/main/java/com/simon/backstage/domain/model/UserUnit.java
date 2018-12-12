package com.simon.backstage.domain.model;

import java.util.Date;

public class UserUnit {
    private Long userUnitId;

    private String userId;

    private String unitId;

    private Date createTime;

    private Date updateTime;

    public Long getUserUnitId() {
        return userUnitId;
    }

    public void setUserUnitId(Long userUnitId) {
        this.userUnitId = userUnitId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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